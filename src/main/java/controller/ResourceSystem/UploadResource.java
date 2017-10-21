package controller.ResourceSystem;

import cst.Constants;
import framework.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.resourceSystem.uploadFile.IUploadFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;

/**
 * Created by Karn on 2017/1/29.
 */
@Controller
public class UploadResource {
    private static final Logger logger = LoggerFactory.getLogger(UploadResource.class);

    @Autowired
    private HttpServletRequest request;

    @Resource
    private IUploadFileService uploadFileService;

    private String filePath;
    //文件的相对路径
    private String fileRelativePath;

    @RequestMapping(value = "/jsp/uploadFile")
    public String uploadPage(){
        return "uploadFile";
    }

    /**
     * 跳转到上传成功的页面
     * @param retcode 传递的参数，必须使用@ModelAttribute注解,
     *                否则跳转后的页面无法通过EL表达式获取该参数
     * @return uploadSuccess页面
     */
    @RequestMapping(value = "/jsp/uploadSuccess")
    public String success(@ModelAttribute("retcode") String retcode){
        return "uploadSuccess";
    }

    @RequestMapping(value = "/jsp/uploadError")
    public String error(@ModelAttribute("retcode") String retcode){
        return "uploadError";
    }

    /**
     * 处理上传文件逻辑
     * @param file 上传的文件
     * @param upload_doc_name 上传的文件名
     * @param doc_tag_name 文件标签
     * @param doc_description 文件描述
     * @param attr 用于重定向页面的传参
     * @return 重定向的页面
     */
    @RequestMapping(value = "/jsp/uploadFile/file_upload")
    public String fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("upload_doc_name") String upload_doc_name,
                             @RequestParam("doc_tag_name") String doc_tag_name,
                             @RequestParam("doc_description") String doc_description, RedirectAttributes attr) {

        Integer user_id =Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        int ret = copyFileToServerDir(file,user_id);
        if(ret != 1) {
            //上传失败的页面, addFlashAttribute不会在url中暴露参数
            attr.addFlashAttribute("retcode",String.valueOf(ret));
            return "redirect:/jsp/uploadError";
        }else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            try {
                long fileSize = file.getSize();
                String fileSizeStr;
                if(fileSize<1024)
                    fileSizeStr = String.valueOf(fileSize)+"B";
                else if(fileSize>1024*1024)
                    fileSizeStr = String.valueOf(fileSize/(long)(1024*1024)) + "M";
                else
                    fileSizeStr = String.valueOf(fileSize/(long)(1024)) + "K";
                int rst = uploadFileService.saveFileToDB(user_id,upload_doc_name,
                        doc_description, fileSizeStr,fileRelativePath,timestamp,
                        doc_tag_name.split(";"));
                //上传成功的页面
                attr.addFlashAttribute("retcode",String.valueOf(rst));
                return "redirect:/jsp/uploadSuccess";
            } catch (ServiceException e) {
                attr.addFlashAttribute("retcode","-1");
                return "redirect:/jsp/uploadError";
            }
        }
    }

    /**
     * 将文件流上传到服务器
     * @param file
     * @return 0   文件为空
     *         1   上传成功
     *         -1  上传失败
     */
    public int copyFileToServerDir(MultipartFile file, int user_id){
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //System.out.println(file.getOriginalFilename());
                //相对路径
                fileRelativePath = "/fileResources/" + String.valueOf(user_id);
                // 文件保存路径
                filePath = request.getSession().getServletContext().getRealPath(fileRelativePath);
                File f = new File(filePath);
                //若目录不存在，则创建
                if(!f.exists()){
                    f.mkdirs();
                }
                //System.out.println(filePath);
                // 转存文件
                file.transferTo(new File(filePath,file.getOriginalFilename()));
                fileRelativePath +="/" + file.getOriginalFilename();
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
            return 1;
        }
        return 0;
    }
}
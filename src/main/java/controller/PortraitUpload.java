package controller;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cst.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.PortraitUpload.IPortraitUploadService;

/**
 * Created by joy12 on 2017/1/31.
 * 上传头像
 */
@Controller
public class PortraitUpload {
    @Autowired
    HttpServletRequest request;

    @Resource
    private IPortraitUploadService IPortraitUploadService;

//    @RequestMapping(value = "/jsp/myPage")
//    public String myPage(){
//        return "myPage";
//    }

    @RequestMapping(value="/jsp/PortraitUpload/PortraitUploadAction",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView imgToUpload(@RequestParam("imgUtil") MultipartFile file,
                            @RequestParam("x") String x,
                            @RequestParam("y") String y,
                            @RequestParam("h") String h,
                            @RequestParam("w") String w) {
        //System.out.println("找ID");
        Integer user_id =Integer.parseInt(request.getSession().getAttribute(Constants.USER_Id).toString());
        //System.out.println("找到了");
        //System.out.println("ID:"+user_id);

        //保存头像至服务器
        if (!file.isEmpty()) {
            try {
                System.out.println("头像原始图片文件名：" + file.getOriginalFilename());
                //相对路径
                String fileRelativePath = "/userPortraits/" + String.valueOf(user_id);
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath(fileRelativePath);
                File f = new File(filePath);
                //若目录不存在，则创建
                if(!f.exists()){
                    f.mkdirs();
                }
                // 转存文件
                String src_name = file.getOriginalFilename();
                int tmp = src_name.lastIndexOf('.');
                String ext = src_name.substring(tmp,src_name.length());
                src_name = src_name.substring(0,tmp) + "_src" + ext;

                file.transferTo(new File(filePath,src_name));
                fileRelativePath +="/" + src_name;
            //    System.out.println("头像原文件保存相对路径："+fileRelativePath);
                //图片剪裁
                int imageX = (int)Math.round(Double.parseDouble(x));
                int imageY = (int)Math.round(Double.parseDouble(y));
                int imageH = (int)Math.round(Double.parseDouble(h));
                int imageW = (int)Math.round(Double.parseDouble(w));
                imgCut(filePath+"/"+src_name,imageX,imageY,imageW,imageH);
                //更新数据库
                IPortraitUploadService.savePortraitToDB(user_id,fileRelativePath.replace("_src.","_cut."));
                //更新头像src
                HttpSession session = request.getSession();
                session.setAttribute(Constants.USER_HEADPORTRAIT,fileRelativePath.replace("_src.","_cut."));
            //    System.out.println("头像剪裁后的文件保存相对路径：" + fileRelativePath.replace("_src.","_cut."));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("文件为空");
        }

        //重新返回当前页面（即自动刷新显示头像）
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/jsp/myPage");
        return mv;
    }

    /**
     * 截取图片
     * @param srcImageFile  原图片地址
     * @param x    截取时的x坐标
     * @param y    截取时的y坐标
     * @param desWidth   截取的宽度
     * @param desHeight   截取的高度
     */
    public static void imgCut(String srcImageFile, int x, int y, int desWidth,
                              int desHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            if (srcWidth >= desWidth && srcHeight >= desHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();
                String newImgName = srcImageFile.replace("_src.","_cut.");
                //输出文件
                ImageIO.write(tag, "JPEG", new File(newImgName));
                System.out.println("剪裁后输出文件：" + newImgName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
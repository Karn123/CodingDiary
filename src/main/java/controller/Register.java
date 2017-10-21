package controller;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.register.RegisterService;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Karn on 2016/11/29.
 */
@Controller("Register")
public class Register {
    @Resource
    private RegisterService registerService;

    @RequestMapping(value = "/jsp/register")
    public String toRegister() {
        return "register";
    }

    @RequestMapping(value = "/jsp/user/register/getVerificationByEmail", method = RequestMethod.POST)
    @ResponseBody
    public Map getVerificationByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("email");
        String register_account = request.getParameter("register_account");
        Random random = new Random();
        String verificationCode = "";
        for (int i = 0; i < 6; i++)
            verificationCode = verificationCode + (random.nextInt(10)) + "";
        try {
            new Register().createMail(register_account, verificationCode);
        } catch (Exception x) {
            System.out.println(x);
        }
        System.out.println(verificationCode);
        Map result = new HashMap();
        result.put("verification", verificationCode);
        return result;
    }

    @RequestMapping(value = "/jsp/user/register/getVerificationByPhone", method = RequestMethod.POST)
    @ResponseBody
    public Map getVerificationByPhone(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("phone");
        String register_account = request.getParameter("register_account");
        Random random = new Random();
        String verificationCode = "";
        for (int i = 0; i < 6; i++)
            verificationCode = verificationCode + (random.nextInt(10)) + "";
        try {
            new Register().createPhone(register_account, verificationCode);
        } catch (Exception x) {
            System.out.println(x);
        }
        System.out.println(verificationCode);
        Map result = new HashMap();
        result.put("verification", verificationCode);
        return result;
    }

    public void createMail(String goal, String code) throws MessagingException {
        System.out.println(goal);
        System.out.println(code);
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.163.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);  //停在了这里
        session.setDebug(true);
        Transport ts = session.getTransport();
        ts.connect("smtp.163.com", "m15900613826@163.com", "linjiale70101");
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("m15900613826@163.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(goal));
        //邮件的标题
        MimeBodyPart text = new MimeBodyPart();
        message.setSubject("验证码");
        //邮件的文本内容
        text.setContent(code, "text/html;charset=UTF-8");
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.setSubType("mixed");
        message.setContent(mp);
        message.saveChanges();
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public void createPhone(String goal, String code) throws Exception {
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data = {new NameValuePair("Uid", "codingDiary"), new NameValuePair("Key", "59c26748da69a4a19111"), new NameValuePair("smsMob", goal), new NameValuePair("smsText", "验证码：" + code)};
        post.setRequestBody(data);
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:" + statusCode);
        for (Header h : headers) {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态


        post.releaseConnection();
    }

    @RequestMapping(value = "/jsp/user/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerNewUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        boolean registerResult = true;
        String register_account = request.getParameter("register_account");
        String register_password = request.getParameter("register_password");
        if (register_account.contains("@"))
            registerResult = registerService.registerByEmail(register_account, register_password);
        else
            registerResult = registerService.registerByPhone(register_account, register_password);
        if (registerResult)
            result.put("msg", "注册成功");
        else
            result.put("msg", "该用户已被注册");
        return result;
    }
}
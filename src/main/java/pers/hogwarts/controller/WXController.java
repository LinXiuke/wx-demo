package pers.hogwarts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pers.hogwarts.common.util.CheckoutUtil;
import pers.hogwarts.manager.MessageManager;
import pers.hogwarts.form.MessageForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:  部署在tomcat下需要为80端口，微信公众号后台验证token的远程服务器http请求只能是80端口
 * @Author: Hogwarts
 * @Date: 2018/10/29
 */
@Controller
public class WXController {

    @Value("${wx.token}")
    private String token;

    @Autowired
    private MessageManager messageManager;


    //get方法为微信后台服务器验证token方法
    @GetMapping("/wx")
    public void verification(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter print;
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
            try {
                print = response.getWriter();
                print.write(echostr);
                print.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //post方法为微信后台回复消息方法
    @PostMapping(value = "/wx", produces = { "application/xml;charset=UTF-8" })
    public Object message(@RequestBody MessageForm form) {
        try {
            return messageManager.getMessage(form);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
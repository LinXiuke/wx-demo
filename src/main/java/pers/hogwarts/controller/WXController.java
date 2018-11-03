package pers.hogwarts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pers.hogwarts.util.CheckoutUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/10/29
 */
@Controller
public class WXController {

    @Value("${wx.token}")
    private String token;

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

}
package pers.hogwarts.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.hogwarts.manager.AccessTokenManager;
import pers.hogwarts.manager.ImageManager;
import pers.hogwarts.manager.MessageManager;
import pers.hogwarts.form.MessageForm;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/11/3
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AccessTokenManager accessTokenManager;

    @Autowired
    private MessageManager messageManager;


    @GetMapping("accessToken")
    public String getAccessToken() {
        return accessTokenManager.getAccessToken().getAccessToken();
    }

    //返回为xml格式
    @GetMapping(value = "message", produces = { "application/xml;charset=UTF-8" })
    public Object getMessage(@RequestBody MessageForm form) {
        return messageManager.getMessage(form);
    }

    @PostMapping(value = "upload", produces = { "application/xml;charset=UTF-8" })
    public JSONObject upload() {
        return ImageManager.upload("classpath:image/1.jpg", accessTokenManager.getAccessToken().getAccessToken());
    }
}

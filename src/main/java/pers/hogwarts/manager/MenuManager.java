package pers.hogwarts.manager;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.hogwarts.common.HttpClient;

/**
 * @Description:
 * @Author: Hogwarts
 * @Date: 2018/11/18
 */
public class MenuManager {

    private String ADD_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";


    /**
     * 创建菜单
     * @param accessToken
     * @return
     */
    public JSONObject create(String accessToken, JSONObject params) {

        String url = ADD_MENU_URL.replace("ACCESS_TOKEN", accessToken);

//        JSONObject params = new JSONObject();
//        JSONArray button = new JSONArray();
//        JSONObject button1 = new JSONObject();
//        button1.put("type", "click");
//        button1.put("name", "菜单");
//        button1.put("key", "key");
//        button.add(button1);
//        params.put("button", button);

        return HttpClient.doPostReturnJSON(url, params);
    }

    public JSONObject delete(String accessToken) {

        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken);

        return HttpClient.doGetReturnJSON(url);
    }
}

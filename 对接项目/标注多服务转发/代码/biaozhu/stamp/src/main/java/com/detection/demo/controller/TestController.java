package com.detection.demo.controller;

import com.alibaba.fastjson.JSON;
import com.detection.demo.service.TestService;
import com.detection.demo.util.HttpURLClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhang
 */
@RestController
@RequestMapping("/test")
public class TestController {

    //印章检测服务
    private final static String QSJC = "PRX00";
    private final static String XZJC = "PRX01";
    private final static String YSJC = "PRX02";
    private final static String MHJC = "PRX03";
    private final static String DXJC = "PRX04";
    private final static String ZYZBB = "PRX05";
    private final static String NRJC = "PRX06";

    //OCR
    private final static String OCR = "PRX07";

    //0：印章，1: OCR

    @Autowired
    private TestService testService;
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/test")
    public String test(@RequestBody String image) throws Exception {
        String url = "http://20.26.39.111:50009/seal_exist";
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        map2.put("image", image);
        map1.put("requestParms", map2);
        String param = JSON.toJSONString(map2);
        HttpURLClient httpURLClient = new HttpURLClient();
        String result = httpURLClient.doPost(url, param, null, null, null, null);
        return result;

    }

    @PostMapping("/test2")
    public String test1(@RequestBody Map<String,String> map){
        ExecutorService exec = Executors.newCachedThreadPool();

        String callType = map.get("callType");
        String url;
        if (Objects.equals(callType, TestController.QSJC)){
            url = "http://20.26.39.111:50009/seal_exist";
        }else if (Objects.equals(callType,TestController.XZJC)){
            url = "http://20.26.39.111:50009/seal_shape";
        }else if (Objects.equals(callType,TestController.YSJC)){
            url = "http://20.26.39.111:50009/seal_color";
        }else if (Objects.equals(callType,TestController.MHJC)){
            url = "http://20.26.39.111:50009/seal_blur";
        }else if (Objects.equals(callType,TestController.DXJC)){
            url = "http://20.26.39.111:50009/seal_size";
        }else if (Objects.equals(callType,TestController.ZYZBB)){
            url = "http://20.26.39.111:50009/seal_special";
        }else if (Objects.equals(callType,TestController.NRJC)){
            url = "http://20.26.39.111:50009/seal_content";
        }else {
            return null;
        }


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("format","http+json");

        Map<String, String> mapTring=new HashMap<>();
        mapTring.put("image",map.get("image"));
//        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(mapTring));
//        String string=JSONObject.toJSONString(jsonObject);
        HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(mapTring),httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,entity,String.class);
        String body = responseEntity.getBody();
        return body;
    }


}

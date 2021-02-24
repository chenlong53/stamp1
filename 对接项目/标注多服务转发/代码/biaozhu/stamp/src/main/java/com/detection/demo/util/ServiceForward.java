package com.detection.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.detection.demo.controller.AppController;
import com.detection.demo.entity.InterfaceConverBo;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by 26387 on 2019/3/27.
 */
public class ServiceForward {
//    private final static String DRLJC = "PRX000";
//    private final static String RXWGJC = "PRX001";
//    private final static String TDXBQSJC = "PRX002";
//    private final static String DMJC = "PRX003";
//    private final static String XPZJC = "PRX004";
//    private final static String WGBQJC = "PRX010";
//    private final static String RLTPZLJC = "PRX011";
//
//
//    public  List main(String args) throws Exception {
//        Date today = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String fileName = "dfwfz-"+System.getProperty("HOSTNAME")+"-"+sdf.format(today)+"-info.log";
//        org.apache.log4j.Logger logger = LogUtil.setFileName(AppController.class,"E:\\logs",fileName);
//        long aa = System.currentTimeMillis();
//        try {
//            Map paramsMap = JSONObject.parseObject(args, Map.class);
//            String callType = (String) paramsMap.get("callType");
//            String comments = (String) paramsMap.get("comments");
//            String images = (String) paramsMap.get("images");
//            String[] callTypes = callType.split(",");
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String requestTime = df.format(new Date());
//            String requestId = new Date().getTime()+"";
//            String token = "";
//            ExecutorService exec = Executors.newCachedThreadPool();
//            List<Object> list = new ArrayList<>();
//            List<Future<String>> futureList = new ArrayList< Future<String>>();
//            try {
//                for (int i = callTypes.length - 1; i >= 0; i--) {
//                    if (Objects.equals(callTypes[i], ServiceForward.DRLJC)){
//                        futureList.add(exec.submit(drlDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//                    } else if (Objects.equals(callTypes[i], ServiceForward.RXWGJC)) {
//                        futureList.add(exec.submit(rxwgDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//                    } else if (Objects.equals(callTypes[i], ServiceForward.TDXBQSJC)) {
//                        futureList.add(exec.submit(tdxbqsDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//                    } else if (Objects.equals(callTypes[i], ServiceForward.DMJC)) {
//                        futureList.add(exec.submit(dmDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//                    } else if (Objects.equals(callTypes[i], ServiceForward.XPZJC)) {
//                        futureList.add(exec.submit(xpzjcDetection(images.split(",")[1], token, requestId, requestTime, comments)));
//                    } else if (Objects.equals(callTypes[i], ServiceForward.WGBQJC)) {
//                        futureList.add(exec.submit(wgbqDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//                    } else if (Objects.equals(callTypes[i], ServiceForward.RLTPZLJC)) {
//                        futureList.add(exec.submit(rltpzlDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//                    }
//                }
//                for(Future<String> future:futureList){
//                    try{
//                        Object o = future.get(8, TimeUnit.SECONDS);
//                        list.add(JSONObject.parseObject(JSON.toJSONString(o),Map.class));
//                    }catch(Exception e){
//
//                    }
//                }
//
//                logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s");
//                return list;
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//            logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
//        }
//        return null;
//    }
//    public static Callable rxwgDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return  () -> {
//            InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
//            try {
//                String appId = Constants.RXWG_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.RXWG_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.RXWG_PORT + Constants.RXWG_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.RXWG_SERVICE_NAME,"image",null);
//                interfaceConverBo = parseJsonParamStr(result);
//            }catch (Exception e){
//                interfaceConverBo.setRespMsg("service_processing_exception");
//                interfaceConverBo.setRespCode("0");
//                interfaceConverBo.setRespId("");
//                interfaceConverBo.setRespInfo(new ArrayList<>());
//            }
//            Map<String, Object> map = buildHttpAcceptJsons(interfaceConverBo, Constants.RXWG_SERVICE_NAME,"rxwg");
//            String respInfo = JSON.toJSONString(map);
//            return JSONObject.parseObject(respInfo);
//        };
//
//    }
//    public static Callable drlDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
//            try {
//                String appId = Constants.DRL_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.DRL_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.DRL_PORT + Constants.DRL_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.DRL_SERVICE_NAME,"imageData",null);
//                interfaceConverBo = parseJsonParamStr(result);
//            }catch (Exception e){
//                interfaceConverBo.setRespMsg("service_processing_exception");
//                interfaceConverBo.setRespCode("0");
//                interfaceConverBo.setRespId("");
//                interfaceConverBo.setRespInfo(new ArrayList<>());
//            }
//            Map<String, Object> map = buildHttpAcceptJsons(interfaceConverBo, Constants.DRL_SERVICE_NAME,"drl");
//            String respInfo = JSON.toJSONString(map);
//            return JSONObject.parseObject(respInfo);
//        };
//
//
//    }
//    public static Callable dmDetection(String scenePic,  String token, String requestId, String requestTime, String comments) {
//        return  () -> {
//            try {
//                String appId = Constants.DMJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.DMJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.DMJC_PORT + Constants.DMJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.DMJC_SERVICE_NAME,"image",null);
//                Map map = JSONObject.parseObject(result, Map.class);
//                String resultCode = (String) map.get("result");
//                if(Objects.equals(resultCode,"105")){
//                    return JSONObject.parseObject(Constants.DMJC_ERROR);
//                }else{
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    String errorcode = (String) frMap.get("errorcode");
//                    frMap.put("servicename",Constants.DMJC_SERVICE_NAME);
//                    frMap.put("errorCode",errorcode);
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.DMJC_ERROR);
//            }
//        };
//
//    }
//
//    private static String getResultFuture(String url,String scenePic, String token, String requestId, String requestTime, String comments,String appId,String serviceName,String imgKey,String type) throws Exception {
//
//        Map map1 = new HashMap();
//        Map map2 = new HashMap();
//        map2.put(imgKey, scenePic);
//        map2.put("comments", comments);
//        map1.put("requestParms", map2);
//        map1.put("serviceName", serviceName);
//        map1.put("interfaceName", serviceName);
//        String param = JSON.toJSONString(map1);
//        HttpURLClient httpURLClient = new HttpURLClient();
//        String result = httpURLClient.doPost(url, param, appId, token, requestId, requestTime);
//        return result;
//    }
//    public static Callable tdxbqsDetection(String scenePic,  String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.TDXBQS_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.TDXBQS_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.TDXBQS_PORT + Constants.TDXBQS_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.TDXBQS_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                String resultCode = (String) map.get("result");
//                if(Objects.equals(resultCode,"106")){
//                    return JSONObject.parseObject(Constants.TDXBQS_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.TDXBQS_SERVICE_NAME);
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.TDXBQS_ERROR);
//            }
//        };
//    }
//
//    public static Callable xpzjcDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.XPZJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.XPZJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.XPZJC_PORT + Constants.XPZJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.XPZJC_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = (String) respInfoMap.get("result");
//                if(Objects.equals(resultCode,"108")){
//                    return JSONObject.parseObject(Constants.XPZJC_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.XPZJC_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.XPZJC_ERROR);
//            }
//        };
//    }
//
//    public static Callable wgbqDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.WGBQJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.WGBQJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.WGBQJC_PORT + Constants.WGBQJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.WGBQJC_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = (String) respInfoMap.get("result");
//                if(Objects.equals(resultCode,"102")){
//                    return JSONObject.parseObject(Constants.WGBQJC_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.WGBQJC_SERVICE_NAME);
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.WGBQJC_ERROR);
//            }
//        };
//    }
//
//    public static Callable rltpzlDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.RLTPZLJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.RLTPZLJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.RLTPZLJC_PORT + Constants.RLTPZLJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.RLTPZLJC_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = String.valueOf(respInfoMap.get("result")) ;
//                if(Objects.equals(resultCode,"107")){
//                    return JSONObject.parseObject(Constants.RLTPZLJC_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.RLTPZLJC_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.RLTPZLJC_ERROR);
//            }
//        };
//    }
//
//    public static Callable ptDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.PTJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.PTJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.PTJC_PORT + Constants.PTJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.PTJC_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = String.valueOf(respInfoMap.get("result")) ;
//                if(Objects.equals(resultCode,"101")){
//                    return JSONObject.parseObject(Constants.PTJC_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.PTJC_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.PTJC_ERROR);
//            }
//        };
//    }
//
//    public static Callable fpDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.FPJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.FPJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.FPJC_PORT + Constants.FPJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.FPJC_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = String.valueOf(respInfoMap.get("result")) ;
//                if(Objects.equals(resultCode,"101")){
//                    return JSONObject.parseObject(Constants.FPJC_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.FPJC_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.FPJC_ERROR);
//            }
//        };
//    }
//
//    public static Callable rzbdDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.RZBD_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.RZBD_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.RZBD_PORT + Constants.RZBD_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.RZBD_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = String.valueOf(respInfoMap.get("result")) ;
//                if(Objects.equals(resultCode,"101")){
//                    return JSONObject.parseObject(Constants.RZBD_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.RZBD_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.RZBD_ERROR);
//            }
//        };
//    }
//
//    public static Callable dckhDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.DCKHJC_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.DCKHJC_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.DCKHJC_PORT + Constants.DCKHJC_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.DCKHJC_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = String.valueOf(respInfoMap.get("result")) ;
//                if(Objects.equals(resultCode,"101")){
//                    return JSONObject.parseObject(Constants.DCKHJC_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.DCKHJC_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.DCKHJC_ERROR);
//            }
//        };
//    }
//
//    public static Callable qmsbDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
//        return () -> {
//            try {
//                String appId = Constants.QMSB_APP_ID;
//                String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.QMSB_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.QMSB_PORT + Constants.QMSB_ROUTE;
//                String result = getResultFuture(url,scenePic, token, requestId, requestTime, comments,appId,Constants.QMSB_SERVICE_NAME,"image","1");
//                Map map = JSONObject.parseObject(result, Map.class);
//                Map respInfoMap = JSONObject.parseObject(JSON.toJSONString( map.get("respInfo")), Map.class);
//                String resultCode = String.valueOf(respInfoMap.get("result")) ;
//                if(Objects.equals(resultCode,"101")){
//                    return JSONObject.parseObject(Constants.QMSB_ERROR,Map.class);
//                }else {
//                    String fr = JSONObject.parseObject(result, Map.class).get("respInfo").toString();
//                    Map frMap = JSONObject.parseObject(fr, Map.class);
//                    frMap.put("servicename",Constants.QMSB_SERVICE_NAME);
//                    frMap.put("errorCode", frMap.get("errorcode"));
//                    frMap.remove("errorcode");
//                    return  JSONObject.parseObject(JSON.toJSONString(frMap));
//                }
//            }catch (Exception e){
//                return JSONObject.parseObject(Constants.QMSB_ERROR);
//            }
//        };
//    }
//
//    private static InterfaceConverBo parseJsonParamStr(String paramStr) throws Exception {
//        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(paramStr);
//        InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
//        String respId = jsonObject.getString("respId");
//        String respCode = jsonObject.getString("respCode");
//        String respMsg = jsonObject.getString("respMsg");
//        String respInfoStr = jsonObject.getString("respInfo");
//        Map map = JSONObject.parseObject(respInfoStr, Map.class);
//        interfaceConverBo.setRespCode(respCode);
//        interfaceConverBo.setRespId(respId);
//        List list = new ArrayList();
//        list.add(map);
//        interfaceConverBo.setRespInfo(list);
//        interfaceConverBo.setRespMsg(respMsg);
//        return interfaceConverBo;
//
//    }
//    private static Map<String, Object> buildHttpAcceptJsons(InterfaceConverBo interfaceConverBo,String serviceName,String type) {
//        String errCode = "";
//        if(Objects.equals(type,"rxwg")){
//            errCode = "errorcode";
//        }else if(Objects.equals(type,"drl")){
//            errCode = "errorCode";
//        }
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        int respCode = Integer.parseInt(interfaceConverBo.getRespCode());
//        String respMsg = interfaceConverBo.getRespMsg();
////		String respInfo = JSON.toJSONString(interfaceConverBo.getRespInfo());
////		JSONArray jsonArray = JSONArray.parseArray(respInfo);
////		String str = jsonArray.getString(0);
//        if (respCode != 200) {
//            resultMap.put("errorCode", ""+respCode);
//            resultMap.put("result", "");
//            resultMap.put("servicename", serviceName);
//            resultMap.put("msg", respMsg);
//        } else {
//            Map<String, String> map;
//            map = interfaceConverBo.getRespInfo().get(0);
//            int errorCode = Integer.parseInt(map.get(errCode));
//            String result = map.get("result");
//            String msg = map.get("msg");
//            resultMap.put("errorCode", ""+errorCode);
//            resultMap.put("result", result);
//            resultMap.put("msg", msg);
//            resultMap.put("servicename", serviceName);
//        }
//        return resultMap;
//    }
}

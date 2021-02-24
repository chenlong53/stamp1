package com.detection.demo.service.impl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.detection.demo.entity.InterfaceConverBo;
import com.detection.demo.service.InterConverService;
import com.detection.demo.util.Constants;
import com.detection.demo.util.HttpURLClient;
import com.detection.demo.util.PropertiesFileReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class InterConverServiceImpl implements InterConverService {

	@Override
	public Callable psDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
//				String url = "http://20.26.39.111:50018/tampered_detecte";
				String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.YZ_IP2 + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.YZ_PORT2 +Constants.YZ_ROUTE2;
				String result = getResultFutureForPs(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.EXIST_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStrYz(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.EXIST_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};

	}

	/**
	 * OCR
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @param angle
	 * @param callType
	 * @return
	 */
	@Override
	public Callable ocrDetection(String scenePic, String token, String requestId, String requestTime, String comments,String angle, String callType) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {

				String appId = Constants.OCR_APP_ID;
				String url = Constants.HttpConstants.HTTP_URL_HEAD + Constants.OCR_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.OCR_PORT +Constants.OCR_ROUTE;
//				String url ="http://20.26.39.111:52000/service";
				String result = getResultFutureOcr(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.OCR_SERVICE_NAME,"image",null, angle, callType);
				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsons(interfaceConverBo, Constants.OCR_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};

	}
	//callType=006
	@Override
	public Callable Detection(String callType, String images) {
		return () -> {
			String url = PropertiesFileReader.getProperty(callType + "_url");
			String serviceName = PropertiesFileReader.getProperty(callType + "_service_name");
			Map map = new HashMap();
			map.put("image", images);
			String params = JSON.toJSONString(map);
			StringEntity entity = new StringEntity(params, "utf-8");
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "*/*");
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			Map result = new HashMap();
			try {
				response = httpclient.execute(httpPost);
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				result = JSONObject.parseObject(jsonString,Map.class);
				result.put("serviceName",serviceName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		};
	}

	/**
	 * 识别图片中是否存在有效的非集团章，签章缺失检测
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable qsjcDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
//				String url2 = Constants.HttpConstants.HTTP_URL_HEAD + Constants.OCR_IP + Constants.HttpConstants.HTTP_URL_SEMICOLON + Constants.YZ_PORT2 +Constants.OCR_ROUTE;
				String url = Constants.QZQSJC_URL1;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.EXIST_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				try {
					String appId = Constants.OCR_APP_ID;
					String url2 = Constants.QZQSJC_URL;
					String result = getResultFutureYz(url2,scenePic, token, requestId, requestTime, comments,appId,
							Constants.EXIST_SERVICE_NAME,"image",null);
					interfaceConverBo = parseJsonParamStr(result);
				}catch (Exception exception){
					interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
					interfaceConverBo.setRespCode("0");
					interfaceConverBo.setRespId("");
					interfaceConverBo.setRespInfo(new ArrayList<>());
				}
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.EXIST_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};

	}


	/**
	 * 	//签章形状检测
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable xzjcDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
				String url = Constants.QZXZJC_URL;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.SHAPE_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.SHAPE_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};

	}


	/**
	 * 	//签章模糊检测
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable mhjcDetection(String scenePic,  String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
				String url = Constants.QZMHJC_URL;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.BLUR_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.BLUR_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};

	}




	/**
	 * 	//颜色不合规检测
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable ysjcDetection(String scenePic,  String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
				String url = Constants.YZYSSB_URL;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.COLOR_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.COLOR_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};

	}



	/**
	 * 	//大小不合规检测
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable wgbqDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
				String url = Constants.YZCCSB_URL;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.SIZE_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.SIZE_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};
	}


	/**
	 * 	//专用章判别
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable dxjcDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
				String url = Constants.ZYZPB_URL;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.SPECIAL_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.SPECIAL_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};
	}


	/**
	 * 	//印章内容识别
	 * @param scenePic
	 * @param token
	 * @param requestId
	 * @param requestTime
	 * @param comments
	 * @return
	 */
	@Override
	public Callable nzjcDetection(String scenePic, String token, String requestId, String requestTime, String comments) {
		return  () -> {
			InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
			try {
				String appId = Constants.OCR_APP_ID;
				String url = Constants.YZNRSB_URL;
				String result = getResultFutureYz(url,scenePic, token, requestId, requestTime, comments,appId,
						Constants.CONTENT_SERVICE_NAME,"image",null);

				interfaceConverBo = parseJsonParamStr(result);
			}catch (Exception e){
				interfaceConverBo.setRespMsg("service_processing_exception:"+ e.getMessage());
				interfaceConverBo.setRespCode("0");
				interfaceConverBo.setRespId("");
				interfaceConverBo.setRespInfo(new ArrayList<>());
			}
			Map<String, Object> map = buildHttpAcceptJsonsYz(interfaceConverBo, Constants.CONTENT_SERVICE_NAME,"rxwg");
			String respInfo = JSON.toJSONString(map);
			return JSONObject.parseObject(respInfo);
		};
	}

	private String getResultFutureForPs(String url,String scenePic, String token, String requestId, String requestTime,
								   String comments,String appId,String serviceName,String imgKey,String type) throws Exception {


		Map map = new HashMap();
		map.put(imgKey, scenePic);

		String param = JSON.toJSONString(map);
		HttpURLClient httpURLClient = new HttpURLClient();
		String result = httpURLClient.doPost(url, param, appId, token, requestId, requestTime);
		return result;
	}

	private String getResultFuture(String url,String scenePic, String token, String requestId, String requestTime,
								   String comments,String appId,String serviceName,String imgKey,String type) throws Exception {

		Map map1 = new HashMap();
		Map map2 = new HashMap();
		map2.put(imgKey, scenePic);
		map2.put("comments", comments);
		map1.put("requestParms", map2);
		map1.put("serviceName", serviceName);
		map1.put("interfaceName", serviceName);
		String param = JSON.toJSONString(map1);
		HttpURLClient httpURLClient = new HttpURLClient();
		String result = httpURLClient.doPost(url, param, appId, token, requestId, requestTime);
		return result;
	}
	private String getResultFutureOcr(String url,String scenePic, String token, String requestId, String requestTime,
									  String comments,String appId,String serviceName,String imgKey,String type,String angle,String callType) throws Exception {

		Map map1 = new HashMap();
		Map map2 = new HashMap();
		map2.put(imgKey, scenePic);
		map2.put("comments", comments);
		map2.put("angle", angle);
		map2.put("plugin",callType);
		map1.put("requestParms", map2);
		map1.put("serviceName", serviceName);
		map1.put("interfaceName", serviceName);
		String param = JSON.toJSONString(map1);
		HttpURLClient httpURLClient = new HttpURLClient();
		String result = httpURLClient.doPost(url, param, appId, token, requestId, requestTime);
		return result;
	}

	private String getResultFutureYz(String url,String scenePic, String token, String requestId, String requestTime,
									 String comments,String appId,String serviceName,String imgKey,String type) throws Exception {

		Map map1 = new HashMap();
		Map map2 = new HashMap();
		map2.put(imgKey, scenePic);
		map2.put("comments", comments);
		map1.put("requestParms", map2);
		map1.put("serviceName", serviceName);
		map1.put("interfaceName", serviceName);
		String param = JSON.toJSONString(map1);
		HttpURLClient httpURLClient = new HttpURLClient();
		String result = httpURLClient.doPost(url, param, appId, token, requestId, requestTime);
		return result;
	}

	private String getResultFuture1(String url,String scenePic, String token, String requestId, String requestTime, String comments,String appId,String serviceName,String imgKey,String imgKey1,String type) throws Exception {

		String scenePic1 = scenePic.split(",")[1];
		String scenePic2 = scenePic.split(",")[0];
		Map map1 = new HashMap();
		Map map2 = new HashMap();
		map2.put(imgKey, scenePic1);
		map2.put(imgKey1,scenePic2);
		map2.put("comments", comments);
		map1.put("requestParms", map2);
		map1.put("serviceName", serviceName);
		map1.put("interfaceName", serviceName);
		String param = JSON.toJSONString(map1);
		HttpURLClient httpURLClient = new HttpURLClient();
		String result = httpURLClient.doPost(url, param, appId, token, requestId, requestTime);
		return result;
	}


	private InterfaceConverBo parseJsonParamStr(String paramStr) throws Exception {
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(paramStr);
		InterfaceConverBo interfaceConverBo = new InterfaceConverBo();
		String respId = jsonObject.getString("respId");
		String respCode = jsonObject.getString("respCode");
		String respMsg = jsonObject.getString("respMsg");
		String respInfoStr = jsonObject.getString("respInfo");
		Map map = JSONObject.parseObject(respInfoStr, Map.class);
		interfaceConverBo.setRespCode(respCode);
		interfaceConverBo.setRespId(respId);
		List list = new ArrayList();
		list.add(map);
		interfaceConverBo.setRespInfo(list);
		interfaceConverBo.setRespMsg(respMsg);
		return interfaceConverBo;

	}
	private InterfaceConverBo parseJsonParamStrYz(String paramStr) throws Exception {
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(paramStr);
		InterfaceConverBo interfaceConverBo = new InterfaceConverBo();


//		String respMsg = jsonObject.getString("msg");
		List list = new ArrayList();
		list.add(jsonObject);
		interfaceConverBo.setRespInfo(list);
//		interfaceConverBo.setRespMsg(respMsg);
		return interfaceConverBo;

	}
	private Map<String, Object> buildHttpAcceptJsons(InterfaceConverBo interfaceConverBo,String serviceName,String type) {
		Map<String, Object> resultMap = new HashMap<>();
		int respCode = Integer.parseInt(interfaceConverBo.getRespCode());
		String respMsg = interfaceConverBo.getRespMsg();

		if (respCode != 200) {
			resultMap.put("errorCode", ""+respCode);
			resultMap.put("result", "");
			resultMap.put("servicename", serviceName);
			resultMap.put("msg", respMsg);
		} else {
			Map<String, String> map;
			map = interfaceConverBo.getRespInfo().get(0);
			resultMap.put("result", map);
			resultMap.put("msg", ""+respMsg);
			resultMap.put("servicename", serviceName);
		}
		return resultMap;
	}
	private Map<String, Object> buildHttpAcceptJsonsYz(InterfaceConverBo interfaceConverBo,String serviceName,String type) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, String> map = interfaceConverBo.getRespInfo().get(0);
		resultMap.put("msg",map.get("msg"));
		resultMap.put("ret",map.get("msg"));
		resultMap.put("zy",map.get("zy"));
		resultMap.put("servicename", serviceName);

//		Map<String, String> map;
//		map = interfaceConverBo.getRespInfo().get(0);
//		String msg = map.get("msg");
//		resultMap.put("result", map);
//		resultMap.put("msg", msg);
//		resultMap.put("servicename", serviceName);

		return resultMap;
	}
}

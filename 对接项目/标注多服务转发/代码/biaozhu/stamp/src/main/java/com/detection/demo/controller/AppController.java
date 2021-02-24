package com.detection.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.detection.demo.entity.Stamp;
import com.detection.demo.service.InterConverService;
import com.detection.demo.service.OConverService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author zhang
 */
@Controller
@RequestMapping("/server")
@Slf4j
public class AppController {

//
//	//印章检测服务 0								//容器
//	private final static String QSJC = "PRX000";  //1,3 签章缺失检测
//	private final static String XZJC = "PRX001";  //1 签章形状检测
//	private final static String YSJC = "PRX002";  //1 印章颜色识别
//	private final static String MHJC = "PRX003";  //3 签章模糊检测
//	private final static String DXJC = "PRX004";  //1 大小不合规检测
//	private final static String ZYZPB = "PRX005"; //3 专用章判别
//	private final static String NRJC = "PRX006";  //3 印章内容识别
//	private final static String PSJC = "PRX007";  //2 印章PS识别

//	//OCR
//	//营业执照 1
//	private final static String YYZZ = "PRX008";
//	//代码证 2
//	private final static String XYDMZ = "PRX009";
//	//电子发票 3
//	private final static String DZFP = "PRX010";

	@Resource
	private InterConverService interConverService;
//	@Autowired
//	private OConverService oConverService;


	@RequestMapping(value = "/conversion")
	@ResponseBody
	public Object conversion(@RequestBody String str){
		Stamp stamp = JSON.parseObject(str, Stamp.class);
		long startTime = System.currentTimeMillis();
		String []callTypes = stamp.getCallType().split(",");
		String images = stamp.getImages();
		ExecutorService exec = Executors.newCachedThreadPool();
		List< Future<String>> futureList = new ArrayList<>();

		List<Object> result = new ArrayList<>();
		for(String callType:callTypes){
			futureList.add(exec.submit(interConverService.Detection(callType,images)));
		}
		for(Future<String> future : futureList) {
			Object o = null;
			try {
				o = future.get(8, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			result.add(o);
		}

		log.info("程序调用时长："+(double)(System.currentTimeMillis()-startTime)/1000+"s");
		// logger.info();
		return result;
	}

	//0：印章，1: OCR
//	@RequestMapping(value = "/common")
//	@ResponseBody
//	public Object sealShape(@RequestBody String paramStr, HttpServletRequest request) throws IOException {
//
//		org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);
//		long aa = System.currentTimeMillis();
//		try {
//			Map paramsMap = JSONObject.parseObject(paramStr, Map.class);
//			String callType = (String) paramsMap.get("callType");
//			String comments = (String) paramsMap.get("comments");
//			String images = (String) paramsMap.get("images");
//			String angles = (String) paramsMap.get("angle");
//
//			String[] callTypes = callType.split(",");
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String requestTime = df.format(new Date());
//			String requestId = request.getHeader("requestId");
//			String token = request.getHeader("token");
//			ExecutorService exec = Executors.newCachedThreadPool();
//			List<Object> list = new ArrayList<>();
//			List< Future<String>> futureList = new ArrayList<>();
//			try {
//				for (int i = callTypes.length - 1; i >= 0; i--) {
//					if (Objects.equals(callTypes[i], AppController.QSJC)){
//						futureList.add(exec.submit(interConverService.qsjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.XZJC)) {
//						futureList.add(exec.submit(interConverService.xzjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.YSJC)) {
//						futureList.add(exec.submit(interConverService.ysjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.MHJC)) {
//						futureList.add(exec.submit(interConverService.mhjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.DXJC)) {
//						futureList.add(exec.submit(interConverService.wgbqDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.ZYZPB)) {
//						futureList.add(exec.submit(interConverService.dxjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.NRJC)) {
//						futureList.add(exec.submit(interConverService.nzjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.DZFP)) {
//						futureList.add(exec.submit(interConverService.ocrDetection(images.split(",")[3], token, requestId, requestTime, comments,angles.split(",")[2],"einvoice")));
//					} else if (Objects.equals(callTypes[i], AppController.XYDMZ)) {
//						futureList.add(exec.submit(interConverService.ocrDetection(images.split(",")[2], token, requestId, requestTime, comments,angles.split(",")[1],"codecard")));
//					} else if (Objects.equals(callTypes[i], AppController.YYZZ)) {
//						futureList.add(exec.submit(interConverService.ocrDetection(images.split(",")[1], token, requestId, requestTime, comments,angles.split(",")[0],"businesslicense")));
//					}
//				}
//
//				for(Future<String> future : futureList){
//					try{
//						Object o = future.get(8,TimeUnit.SECONDS);
//						list.add(JSONObject.parseObject(JSON.toJSONString(o),Map.class));
//					}catch(Exception e){
//
//					}
//				}
//				logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s");
//				return list;
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
//			}
//
//		}catch (Exception e){
//			e.printStackTrace();
//			logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
//		}
//		return null;
//	}

//	@RequestMapping(value = "/conversion")
//	@ResponseBody
//	public Object server(@RequestBody String paramStr, HttpServletRequest request) throws IOException {
//
//		org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);
//		long aa = System.currentTimeMillis();
//		try {
//			Map paramsMap = JSONObject.parseObject(paramStr, Map.class);
//			String callType = (String) paramsMap.get("callType");
//			String comments = (String) paramsMap.get("comments");
//			String images = (String) paramsMap.get("images");
//
//			String[] callTypes = callType.split(",");
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String requestTime = df.format(new Date());
//			String requestId = request.getHeader("requestId");
//			String token = request.getHeader("token");
//			ExecutorService exec = Executors.newCachedThreadPool();
//			List<Object> list = new ArrayList<>();
//			List< Future<String>> futureList = new ArrayList<>();
//			try {
//				for (int i = callTypes.length - 1; i >= 0; i--) {
//					if (Objects.equals(callTypes[i], AppController.QSJC)){
//						futureList.add(exec.submit(interConverService.qsjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.XZJC)) {
//						futureList.add(exec.submit(interConverService.xzjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.YSJC)) {
//						futureList.add(exec.submit(interConverService.ysjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.MHJC)) {
//						futureList.add(exec.submit(interConverService.mhjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.DXJC)) {
//						futureList.add(exec.submit(interConverService.wgbqDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.ZYZPB)) {
//						futureList.add(exec.submit(interConverService.dxjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.NRJC)) {
//						futureList.add(exec.submit(interConverService.nzjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					} else if (Objects.equals(callTypes[i], AppController.PSJC)) {
//						futureList.add(exec.submit(interConverService.psDetection(images.split(",")[0], token, requestId, requestTime, comments)));
//					}
//				}
//
//				for(Future<String> future : futureList){
//					try{
//						Object o = future.get(8,TimeUnit.SECONDS);
//						list.add(JSONObject.parseObject(JSON.toJSONString(o),Map.class));
//					}catch(Exception e){
//
//					}
//				}
//				logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s");
//				return list;
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
//			}
//
//		}catch (Exception e){
//			e.printStackTrace();
//			logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
//		}
//		return null;
//	}

	/*@RequestMapping(value = "/services")
	@ResponseBody
	public Object o(@RequestBody String paramStr, HttpServletRequest request) throws IOException {

		org.slf4j.Logger logger = LoggerFactory.getLogger(AppController.class);
		long aa = System.currentTimeMillis();
		try {
			Map paramsMap = JSONObject.parseObject(paramStr, Map.class);
			String callType = (String) paramsMap.get("callType");
			String comments = (String) paramsMap.get("comments");
			String images = (String) paramsMap.get("images");

			String[] callTypes = callType.split(",");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String requestTime = df.format(new Date());
			String requestId = request.getHeader("requestId");
			String token = request.getHeader("token");
			ExecutorService exec = Executors.newCachedThreadPool();
			List<Object> list = new ArrayList<>();
			List< Future<String>> futureList = new ArrayList<>();
			try {
				for (int i = callTypes.length - 1; i >= 0; i--) {
					if (Objects.equals(callTypes[i], AppController.QSJC)){
						futureList.add(exec.submit(oConverService.qsjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.XZJC)) {
						futureList.add(exec.submit(oConverService.xzjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.YSJC)) {
						futureList.add(exec.submit(oConverService.ysjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.MHJC)) {
						futureList.add(exec.submit(oConverService.mhjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.DXJC)) {
						futureList.add(exec.submit(oConverService.wgbqDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.ZYZPB)) {
						futureList.add(exec.submit(oConverService.dxjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.NRJC)) {
						futureList.add(exec.submit(oConverService.nzjcDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					} else if (Objects.equals(callTypes[i], AppController.PSJC)) {
						futureList.add(exec.submit(oConverService.psDetection(images.split(",")[0], token, requestId, requestTime, comments)));
					}
				}

				for(Future<String> future : futureList){
					try{
						Object o = future.get(8,TimeUnit.SECONDS);
						list.add(JSONObject.parseObject(JSON.toJSONString(o),Map.class));
					}catch(Exception e){

					}
				}
				logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s");
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
			}

		}catch (Exception e){
			e.printStackTrace();
			logger.info("程序调用时长："+(double)(System.currentTimeMillis()-aa)/1000+"s"+"错误信息:"+e.getMessage());
		}
		return null;
	}*/



}

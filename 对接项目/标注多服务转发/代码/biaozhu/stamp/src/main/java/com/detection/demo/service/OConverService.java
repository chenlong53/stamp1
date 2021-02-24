package com.detection.demo.service;

import java.util.concurrent.Callable;

/**
 * @author zhang
 */
public interface OConverService {


	//签章缺失检测 1,3
	Callable qsjcDetection(String scenePic, String token, String requestId, String requestTime, String comments);
	//签章形状检测 1
	Callable xzjcDetection(String scenePic, String token, String requestId, String requestTime, String comments);

	//颜色不合规检测 1
	Callable ysjcDetection(String scenePic, String token, String requestId, String requestTime, String comments);
	//签章模糊检测 3
	Callable mhjcDetection(String scenePic, String token, String requestId, String requestTime, String comments);

	//大小不合规检测 1
	Callable wgbqDetection(String scenePic, String token, String requestId, String requestTime, String comments);
	//专用章判别 3
	Callable dxjcDetection(String scenePic, String token, String requestId, String requestTime, String comments);

	//印章内容识别 3
	Callable nzjcDetection(String scenePic, String token, String requestId, String requestTime, String comments);

	//印章PS识别 2
	Callable psDetection(String scenePic, String token, String requestId, String requestTime, String comments);



}

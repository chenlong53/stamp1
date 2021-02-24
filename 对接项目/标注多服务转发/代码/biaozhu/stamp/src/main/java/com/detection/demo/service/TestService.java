package com.detection.demo.service;

import java.util.concurrent.Callable;

/**
 * @author zhang
 */
public interface TestService {

    //签章缺失检测
//    Callable qsjcDetection(String scenePic , String token , String requestId, String requestTime , String comments);
    //签章test
    Callable response(String callType);
}

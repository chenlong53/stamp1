package com.detection.demo.service.impl;

import com.detection.demo.service.TestService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * @author zhang
 */
@Service
public class TestServiceImpl implements TestService {

    //印章检测服务 0
    private final static String QSJC = "PRX00";
    private final static String XZJC = "PRX01";
    private final static String YSJC = "PRX02";
    private final static String MHJC = "PRX03";
    private final static String DXJC = "PRX04";
    private final static String ZYZBB = "PRX05";
    private final static String NRJC = "PRX06";

    //营业执照 1
    private final static String YYZZ = "PRX07";
    //代码证 2
    private final static String DMZ = "PRX08";
    //电子发票 3
    private final static String DZFP = "PRX09";
    @Override
    public Callable response(String callType) {
        String url;
        if (Objects.equals(callType, TestServiceImpl.QSJC)){
            url = "http://20.26.39.111:50009/seal_exist";
        }else if (Objects.equals(callType,TestServiceImpl.XZJC)){
            url = "http://20.26.39.111:50009/seal_shape";
        }else if (Objects.equals(callType,TestServiceImpl.YSJC)){
            url = "http://20.26.39.111:50009/seal_color";
        }else if (Objects.equals(callType,TestServiceImpl.MHJC)){
            url = "http://20.26.39.111:50009/seal_blur";
        }else if (Objects.equals(callType,TestServiceImpl.DXJC)){
            url = "http://20.26.39.111:50009/seal_size";
        }else if (Objects.equals(callType,TestServiceImpl.ZYZBB)){
            url = "http://20.26.39.111:50009/seal_special";
        }else if (Objects.equals(callType,TestServiceImpl.NRJC)){
            url = "http://20.26.39.111:50009/seal_content";
        }else {
            return null;
        }
        return null;
    }
}

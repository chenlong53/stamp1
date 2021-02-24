package com.detection.demo.util;

/**
 * Created by 26387 on 2018/12/27.
 */
public class HttpErrorException extends Exception {
    // 提供无参数的构造方法
    public HttpErrorException() {
    }

    // 提供一个有参数的构造方法，可自动生成
    public HttpErrorException(String message) {
        super(message);// 把参数传递给Throwable的带String参数的构造方法
    }

}

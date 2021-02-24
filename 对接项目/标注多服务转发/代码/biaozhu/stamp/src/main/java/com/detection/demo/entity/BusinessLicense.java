package com.detection.demo.entity;

/**
 * @author zhang
 */
public class BusinessLicense {
    //图片编码
    private String image;

    //识别类型
    private String plugin;

    //翻转角度
    //进行图片翻转动作，值范围
    //0：表示服务端不做图片倒置检测
    //-1：默认值，表示服务端做图片倒置检测和扶正工作
    //90：图片顺时针旋转90度
    //180：图片顺时针旋转180度
    //270：图片顺时针旋转270度
    private int angle;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }
}

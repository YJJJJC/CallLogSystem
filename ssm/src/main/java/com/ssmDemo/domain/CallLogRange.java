package com.ssmDemo.domain;

/**
 * @author:yjc
 * @Date: 2019/7/3 10:29
 * @Description:
 */
public class CallLogRange {

    private String startPoint;
    private String endPoint;

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String toString() {
        return startPoint + "-" + endPoint;
    }
}

package com.ssmDemo.domain;

/**
 * @author:yjc
 * @Date: 2019/7/7 12:39
 * @Description:通话记录统计结果
 */
public class CallLogStat {

    private Integer count;
    private String yearMonth;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}

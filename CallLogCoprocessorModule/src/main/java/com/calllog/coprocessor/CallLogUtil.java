package com.calllog.coprocessor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @author:yjc
 * @Date: 2019/7/3 9:57
 * @Description:
 */
public class CallLogUtil {

    //时间格式化
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdfFriend = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //格式化对象
    private static DecimalFormat df = new DecimalFormat();

    /**
     * 获取哈希值,默认分区100
     * @param caller
     * @param callTime
     * @return
     */
    public static String getHashcode(String caller,String callTime,int partitions){
        df.applyPattern("00");
        int len = caller.length();
        //取出后4位的电话号码
        String last4code = caller.substring(len-4);
        //取出时间单位，年份，月份
        String mon = callTime.substring(0,6);
        //计算hash值
        int hashcode = (Integer.parseInt(mon) ^ Integer.parseInt(last4code)) % partitions;
        return df.format(hashcode);
    }

    /**
     * 起始时间
     * @param caller
     * @param callTime
     * @param partitions
     * @return
     */
    public static String getStartRowkey(String caller,String callTime,int partitions){
        String hashcode = getHashcode(caller,callTime,partitions);
        return hashcode + "," + caller + "," + callTime;
    }

    /**
     *结束时间
     * @param caller
     * @param startTime
     * @param endTime
     * @param partitions
     * @return
     */
    public static String getStopRowkey(String caller,String startTime,String endTime,int partitions){
        String hashcode = getHashcode(caller,startTime,partitions);
        return hashcode + "," + caller + "," + endTime;
    }

    /**
     * 对时间进行格式化
     * @param timeString
     * @return
     */
    public static String formatDate(String timeString){
        try {
            return sdfFriend.format(sdf.parse(timeString));

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

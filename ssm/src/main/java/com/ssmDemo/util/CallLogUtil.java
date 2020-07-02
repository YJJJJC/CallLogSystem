package com.ssmDemo.util;

import com.ssmDemo.domain.CallLogRange;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/3 9:57
 * @Description:
 */
public class CallLogUtil {

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
     * 计算查询时间范围
     * @param startStr
     * @param endStr
     * @return
     */
    public static List<CallLogRange> getCallLogRanges(String startStr,String endStr) {

        try {
            SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdfYM = new SimpleDateFormat("yyyyMM");
            DecimalFormat df00 = new DecimalFormat("00");

            //
            List<CallLogRange> list = new ArrayList<CallLogRange>();
            //字符串时间
            String startPrefix = startStr.substring(0,6);

            String endPrefix = endStr.substring(0,6);
            int endDay = Integer.parseInt(endStr.substring(6,8)); ////////////////////
            //结束点
            String endPoint = endPrefix + df00.format(endDay+1);

            //日历对象
            Calendar c = Calendar.getInstance();

            //同年月
            if(startPrefix.equals(endPrefix)){
                CallLogRange range = new CallLogRange();
                range.setStartPoint(startStr);  //设置起始点

                range.setEndPoint(endPoint); //设置结束点
                list.add(range);
            }
            else {
                //起始月
                CallLogRange range = new CallLogRange();
                range.setStartPoint(startStr);

                //
                c.setTime(sdfYMD.parse(startStr));
                c.add(Calendar.MONTH,1);
                range.setEndPoint(sdfYM.format(c.getTime()));
                list.add(range);

                //是否为最后一月
                while (true){
                    if(endStr.startsWith(sdfYM.format(c.getTime()))){
                        range = new CallLogRange();
                        range.setStartPoint(sdfYM.format(c.getTime()));
                        range.setEndPoint(endPoint);
                        list.add(range);
                        break;
                    }
                    else {
                        range = new CallLogRange();
                        //起始时间
                        range.setStartPoint(sdfYM.format(c.getTime()));

                        c.add(Calendar.MONTH,1);
                        range.setEndPoint(sdfYM.format(c.getTime()));
                        list.add(range);
                    }
                }
            }
            return list;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
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

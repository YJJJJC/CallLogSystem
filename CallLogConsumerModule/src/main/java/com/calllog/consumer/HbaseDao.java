package com.calllog.consumer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @author:yjc
 * @Date: 2019/7/2 14:04
 * @Description:
 * Hbase的数据访问对象
 */
public class HbaseDao {
    private DecimalFormat df = new DecimalFormat();

    private Table table = null;

    private int partitions;

    private String flag;
    public HbaseDao(){

        try {
            Configuration conf = HBaseConfiguration.create();
            Connection conn = ConnectionFactory.createConnection(conf);
            TableName name = TableName.valueOf(PropertiesUtil.getProp("table.name"));
            table = conn.getTable(name);
            df.applyPattern(PropertiesUtil.getProp("hashcode.pattern"));
            partitions = Integer.parseInt(PropertiesUtil.getProp("partition.number"));
            flag = PropertiesUtil.getProp("caller.flag");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * put数据到hbase
     * @param log
     */
    public void put(String log){
        // 分区号 号码 时间 标记 对方号码 时长
        if(log==null || log.equals("")){
            return;
        }
        try {
            //解析日志
            String[] arr = log.split(",");
            //数据清洗，比如说少一个字段就不要了
            if(arr != null && arr.length==4){
                String caller = arr[0];
                String callee = arr[1];
                String callTime = arr[2];
                callTime = callTime.replace("/",""); //删除斜线
                callTime = callTime.replace(" ",""); //删除空格
                callTime = callTime.replace(":",""); //删除冒号
                String callDuration = arr[3];
                //计算区域号

                //构造put对象
                String rowkey = genRowKey(getHashcode(caller,callTime),caller,callTime,flag,callee,callDuration);
                Put put = new Put(Bytes.toBytes(rowkey));
                put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("caller"),Bytes.toBytes(caller));
                put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("callee"),Bytes.toBytes(callee));
                put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("callTime"),Bytes.toBytes(callTime));
                put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("callDuration"),Bytes.toBytes(callDuration));
                table.put(put);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getHashcode(String caller,String callTime){
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
     * 生成rowkey
     * @param hash
     * @param caller
     * @param time
     * @param flag
     * @param callee
     * @param duration
     * @return
     */
    public String genRowKey(String hash,String caller,String time,String flag,String callee,String duration ){
        return hash + "," + caller + "," + time + "," + flag + "," + callee + "," + duration;
    }
}

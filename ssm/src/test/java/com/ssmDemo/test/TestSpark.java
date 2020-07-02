package com.ssmDemo.test;

import com.ssmDemo.domain.CallLog;
import com.ssmDemo.domain.CallLogStat;
import com.ssmDemo.service.hive.HiveCallLogService;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/5 9:15
 * @Description:
 */
public class TestSpark {

    //还能通过thriftServer2,让sparkSQL作为分布式查询引擎
    //通知jdbc协议直接访问   hive-jdbc
    @Test
    public void test1(){
        String caller = "15778423030";
        String year="2018";
//        SparkConf conf = new SparkConf();
//        conf.setAppName("SparkHive");
//        conf.setMaster("spark://ubuntu1:7077,ubuntu4:7077");
//        SparkContext sc = new SparkContext();
        SparkSession sess =
                SparkSession.builder().enableHiveSupport().appName("SparkHive").master("spark://ubuntu1:7077").getOrCreate();
        String sql = "select count(*) , substr(callTime,1,6) from myhivedb.ext_calllogs_in_hbase " +
                "where caller = '"+caller+"' and substr(callTime,1,4)=='"+year+"' group by " +
                "substr(callTime,1,6)";
//        String sql = "select count(*),* from myhivedb.ext_calllogs_in_hbase";
        Dataset<Row> df = sess.sql(sql);
        List<Row> rows = df.collectAsList();
        List<CallLogStat> list = new ArrayList<CallLogStat>();
        for (Row row:rows) {
            System.out.println(row.getLong(0));
            CallLogStat logSt = new CallLogStat();
            logSt.setCount((int)row.getLong(0));
            logSt.setYearMonth(row.getString(1));
            list.add(logSt);
        }
    }

}

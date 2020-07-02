package com.ssmDemo.service.hive;

import com.ssmDemo.domain.CallLog;
import com.ssmDemo.domain.CallLogStat;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/4 18:24
 * @Description:
 */
@Service("hiveCallLogService")
public class HiveCallLogService {

    //hiveserver2连接串
    private static String url = "jdbc:hive2://ubuntu1:10000/myhivedb";
    //驱动程序类
    private static String driverClass = "org.apache.hive.jdbc.HiveDriver";

    static {
        try {
            Class.forName(driverClass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *查询最近的通话记录，使用hive进行mr查询
     */
    public CallLog findLatestCallLog(String phoneNum){

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            String sql = "select * from ext_calllogs_in_hbase where id like '%"+phoneNum+"%' order by callTime desc limit 1";
            ResultSet rs = st.executeQuery(sql);
            CallLog log = null;
            if (rs.next()){
                log = new CallLog();
                log.setCaller(rs.getString("caller"));
                log.setCallee(rs.getString("callee"));
                log.setCallTime(rs.getString("callTime"));
                log.setCallDuration(rs.getString("callDuration"));

//                String id = rs.getString("id");
//                String caller = rs.getString("caller");
//                String callee = rs.getString("callee");
//                String callTime = rs.getString("callTime");
//                String callDuration = rs.getString("callDuration");
//                System.out.println(id + ":" + caller);
            }
            rs.close();
            return log;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *查询指定人员指定年份中各个月份的的通话次数，使用hive进行mr查询
     */
    public List<CallLogStat> statCallLogCount(String caller,String year){

        List<CallLogStat> list = new ArrayList<CallLogStat>();
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            String sql = "select count(*) , substr(callTime,1,6) from ext_calllogs_in_hbase " +
                    "where caller = '"+caller+"' and substr(callTime,1,4)=='"+year+"' group by " +
                    "substr(callTime,1,6)";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                CallLogStat logSt = new CallLogStat();
                logSt.setCount(rs.getInt(1));
                logSt.setYearMonth(rs.getString(2));
                list.add(logSt);
            }
            rs.close();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     *查询指定人员指定年份中各个月份的的通话次数 通过spark查询hive外部表
     */
    public List<CallLogStat> statCallLogCount2(String caller,String year){

//        SparkConf conf = new SparkConf();
//        conf.setAppName("SparkHive");
//        conf.setMaster("spark://ubuntu1:7077,ubuntu4:7077");
//        SparkContext sc = new SparkContext();
        SparkSession sess = SparkSession.builder().appName("SparkHive").master("spark://ubuntu1:7077").getOrCreate();
        String sql1 = "select count(*) , substr(callTime,1,6) from myhivedb.ext_calllogs_in_hbase " +
                "where caller = '"+caller+"' and substr(callTime,1,4)=='"+year+"' group by " +
                "substr(callTime,1,6)";
        String sql = "select count(*) , substr(callTime,1,6) from myhivedb.ext_calllogs_in_hbase " +
                "where caller = '"+caller+"' and substr(callTime,1,4)=='"+year+"' group by " +
                "substr(callTime,1,6)";
        Dataset<Row> df = sess.sql(sql);
        List<Row> rows = df.collectAsList();
        List<CallLogStat> list = new ArrayList<CallLogStat>();
        for (Row row:rows) {
            System.out.println(row.getString(1));
            CallLogStat logSt = new CallLogStat();
            logSt.setCount((int)row.getLong(0));
            logSt.setYearMonth(row.getString(1));
            list.add(logSt);
        }
        return list;
    }

}

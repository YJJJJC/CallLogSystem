package com.callloggen;

import com.callloggen.udp.HeartBeatThread;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:yjc
 * @Date: 2019/7/1 21:33
 * @Description:
 * 模拟通信，数据生成模块
 */
public class App {

    static Random r = new Random();
    public static List<String> telNumber = new ArrayList<String>();
    public static Map<String,String> callers = new HashMap<String, String>();
    static {
        callers.put("15810092493","史玉龙");
        callers.put("18000696806","赵贺彪");
        callers.put("15151889601","张倩");
        callers.put("13269361119","王世昌");
        callers.put("15032293356","张涛");
        callers.put("17731088562","张阳");
        callers.put("15338595369","李进全");
        callers.put("15733218050","杜泽文");
        callers.put("15614201525","任宗阳");
        callers.put("15778423030","梁鹏");
        callers.put("18641241020","郭美彤");
        callers.put("15732648446","刘飞飞");
        callers.put("13341109505","段光星");
        callers.put("13560190665","唐会华");
        callers.put("18301589432","杨力谋");
        callers.put("13520404983","温海英");
        callers.put("18332562075","朱尚宽");
        callers.put("18620192711","刘能宗");
        telNumber.addAll(callers.keySet());
    }

    public static void main(String[] args) throws Exception {
        //new HeartBeatThread().start();
        genCallLog();
    }

    public static void genCallLog() throws Exception {

        FileWriter fw = new FileWriter(PropertiesUtil.getString("log.file"),true);
        while (true){
            String caller = telNumber.get(r.nextInt(callers.size()));
            String callerName = callers.get(caller);

            String callee = null;
            String calleeName = null;
            while (true){
                callee = telNumber.get(r.nextInt(callers.size()));
                if(!caller.equals(callee)){
                    break;
                }
            }
            calleeName = callers.get(callee);
            //通话时长
            int duration = r.nextInt(PropertiesUtil.getInt("call.duration.max"))+1;
            DecimalFormat d = new DecimalFormat();
            d.applyPattern(PropertiesUtil.getString("call.duration.format"));
            String durStr = d.format(duration);
            //通话时间
            int year = PropertiesUtil.getInt("call.year");
            int month = r.nextInt(12);
            int day = r.nextInt(28)+1;
            int hour = r.nextInt(24);
            int min = r.nextInt(60);
            int sec = r.nextInt(60);

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,year);
            c.set(Calendar.MONTH,month);
            c.set(Calendar.DAY_OF_MONTH,day);
            c.set(Calendar.HOUR_OF_DAY,hour);
            c.set(Calendar.MINUTE,min);
            c.set(Calendar.SECOND,sec);

            Date date = c.getTime();
            SimpleDateFormat dfs = new SimpleDateFormat();
            dfs.applyPattern(PropertiesUtil.getString("call.time.format"));
            //通话时间
            String dateStr = dfs.format(date);

            String log = caller + "," + callerName + ","+ callee + "," + calleeName + "," + dateStr + "," + durStr;
            //String log = caller + "," + callee + "," + dateStr + "," + durStr;
            fw.write(log+"\r\n");
            fw.flush();
            System.out.println(log);
            Thread.sleep(PropertiesUtil.getInt("gen.data.interval.ms"));
        }

    }
}

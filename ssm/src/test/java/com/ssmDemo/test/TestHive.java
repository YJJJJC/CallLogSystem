package com.ssmDemo.test;

import com.ssmDemo.domain.CallLog;
import com.ssmDemo.service.hive.HiveCallLogService;
import org.junit.Test;

/**
 * @author:yjc
 * @Date: 2019/7/5 9:15
 * @Description:
 */
public class TestHive {

    @Test
    public void test1(){
        HiveCallLogService hcs = new HiveCallLogService();
        CallLog log =  hcs.findLatestCallLog("15732648446");
        System.out.println(log.getCaller()+","+log.getCallTime()+","+log.getCallDuration());
    }

}

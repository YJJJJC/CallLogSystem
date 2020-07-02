package com.ssmDemo.test;

import com.alibaba.fastjson.JSON;
import com.ssmDemo.domain.HeartBeat;
import com.ssmDemo.monitor.MonitorService;
import org.junit.Test;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/7 20:16
 * @Description:
 */
public class TestHeartBeatReceiver {

    @Test
    public void test1() throws Exception{
        MonitorService tt = new MonitorService();
        while (true){
            List<HeartBeat> list = tt.getHeartbeats();
            System.out.println(JSON.toJSONString(list));
            Thread.sleep(5000);
        }
    }

}

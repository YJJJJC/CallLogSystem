package com.calllog.test;

import com.calllog.consumer.HeartBeatThread;
import org.junit.Test;

/**
 * @author:yjc
 * @Date: 2019/7/7 20:37
 * @Description:
 */
public class TestHeartBeatSender {

    @Test
    public void test1() throws InterruptedException {
        new HeartBeatThread().start();
        while (true){
            Thread.sleep(1000);
        }
    }
}

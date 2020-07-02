package com.calllog.test;

import com.calllog.consumer.HbaseDao;
import org.junit.Test;

/**
 * @author:yjc
 * @Date: 2019/7/2 15:16
 * @Description:
 */
public class TestHbaseDao {

    @Test
    public void test1(){
        HbaseDao dao = new HbaseDao();
        dao.put("15032293356,18620192711,2017/03/04 14:02:01,297");
    }

}

package com.ssmDemo.monitor;

import com.ssmDemo.domain.HeartBeat;
import org.springframework.stereotype.Service;

import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/7 19:45
 * @Description:
 */
@Service("monitorService")
public class MonitorService {

    private DatagramSocket sock;
    private ReceiveThread t;

    public MonitorService(){
        t = new ReceiveThread();
        t.start();
    }

    public List<HeartBeat> getHeartbeats(){
        return new ArrayList<HeartBeat>(t.map.values());
    }

}

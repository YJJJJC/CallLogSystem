package com.ssmDemo.monitor;

import com.ssmDemo.domain.HeartBeat;
import com.ssmDemo.util.PropertiesUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:yjc
 * @Date: 2019/7/7 19:53
 * @Description:
 */
public class ReceiveThread extends Thread {
    //ip地址和最后一次收到心跳的时间
    public Map<String, HeartBeat> map = new HashMap<String, HeartBeat>();
    DatagramSocket sock ;
    public ReceiveThread(){
        try {

            //sock = new DatagramSocket(PropertiesUtil.getInt("heartbeat.udp.receive.port"));
            sock = new DatagramSocket(9999);
            //守护线程
            this.setDaemon(true);
            System.out.println("心跳接收开始了");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void run() {
        byte[] buf = new byte[1];
        DatagramPacket pack = new DatagramPacket(buf,1);
        while (true){
            try {
                sock.receive(pack);
                int flag = buf[0];
                InetSocketAddress socketAddr = (InetSocketAddress)pack.getSocketAddress();
                String ip = socketAddr.getAddress().getHostAddress();
                map.put(ip,new HeartBeat(ip,flag,System.currentTimeMillis()));
                System.out.println("收到心跳:"+ip+","+flag+","+System.currentTimeMillis());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}

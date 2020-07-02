package com.calllog.consumer;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:yjc
 * @Date: 2019/7/2 13:32
 * @Description:
 * Hbase消费者，从kafka中提取数据存储到Hbase中
 */
public class HbaseConsumer {

    public static void main(String[] args) throws IOException {

        new HeartBeatThread().start();
        HbaseDao dao = new HbaseDao();

        //创建配置对象
        ConsumerConfig config = new ConsumerConfig(PropertiesUtil.props);
        //获得主题
        String topic = PropertiesUtil.getProp("topic");
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put(topic,new Integer(1));

        Map<String, List<KafkaStream<byte[],byte[]>>> msgs = Consumer.createJavaConsumerConnector(
                new ConsumerConfig(PropertiesUtil.props)).createMessageStreams(map);
        List<KafkaStream<byte[],byte[]>> msgList = msgs.get(topic);

        String msg = null;
        for (KafkaStream<byte[],byte[]> stream : msgList){
            ConsumerIterator<byte[],byte[]> it = stream.iterator();
            while (it.hasNext()){
                byte[] message = it.next().message();
                //取得kafka的消息
                msg = new String(message);


                //写入hbase
                dao.put(msg);
            }
        }
    }
}

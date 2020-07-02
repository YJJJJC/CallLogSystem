package com.ssmDemo.web.controller;

import com.alibaba.fastjson.JSON;
import com.ssmDemo.domain.HeartBeat;
import com.ssmDemo.monitor.MonitorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/7 20:47
 * @Description:
 */
@Controller
public class MonitorController {

    @Resource(name = "monitorService")
    private MonitorService ms;

    @RequestMapping("/monitor/monitorPage")
    public String toMonitorPage(){
        return "monitor/monitorPage";
    }

    @RequestMapping("/json/monitor/getMonitorInfo")
    public String getMonitorInfo(HttpServletResponse response){
        List<HeartBeat> list = ms.getHeartbeats();
        String str = null;
        for (HeartBeat h:list) {
            str = "flag:"+h.getFlag()+",IP:"+h.getIp()+",timestamp:"+h.getTimestamp();
        }

        String json = JSON.toJSONString(str);
        System.out.println(str);
        //内容类型
        response.setContentType("application/json");    //格式
        try {
            OutputStream out = response.getOutputStream();
            out.write(json.getBytes());
            out.flush();
            out.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

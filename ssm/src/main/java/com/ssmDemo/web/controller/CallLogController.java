package com.ssmDemo.web.controller;

import com.alibaba.fastjson.JSON;
import com.ssmDemo.domain.CallLog;
import com.ssmDemo.domain.CallLogRange;
import com.ssmDemo.domain.CallLogStat;
import com.ssmDemo.service.CallLogService;
import com.ssmDemo.service.PersonService;
import com.ssmDemo.service.hive.HiveCallLogService;
import com.ssmDemo.util.CallLogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/2 19:52
 * @Description:
 */
@Controller
public class CallLogController {

    @Resource(name = "callLogService")        //CallLogServiceImpl
    private CallLogService cs;

    @Resource(name = "hiveCallLogService")
    private HiveCallLogService hcs;

    @Resource(name = "personService")        //PersonServiceImpl
    private PersonService ps;


    /**
     * 所有通话记录
     * @param model
     * @return
     */
    @RequestMapping("/callLog/findAll")
    public String findAll(Model model){
        List<CallLog> list = cs.findAll();
        model.addAttribute("callLogs",list);
        return "callLog/allCallLogList";
    }
    /**
     * 所有通话记录//json目录下的
     * @param
     * @return
     */
    @RequestMapping("/callLog/json/findAll")
    public String findJsonAll(HttpServletResponse response){
        List<CallLog> list = cs.findAll();

        String json = JSON.toJSONString(list);
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



    /**
     * 进入查询通话记录页面
     * @return
     */
    @RequestMapping("/callLog/toFindCallLogPage")
    public String toFindCallLogPage(){
        return "callLog/findCallLog";
    }
    //以表单提交的方式过来
    @RequestMapping(value = "/callLog/findCallLog",method = RequestMethod.POST)
    public String findCallLog(Model model,@RequestParam("caller") String caller,
                              @RequestParam("startTime") String startTime,
                              @RequestParam("endTime") String endTime){
        List<CallLogRange> list = CallLogUtil.getCallLogRanges(startTime,endTime);
        List<CallLog> logs = cs.findCallLogs(caller,list);
        model.addAttribute("callLogs",logs);
        return "callLog/callLogList";
    }


    /**
     * 进入查询最近通话记录页面
     * @return
     */
    @RequestMapping("/callLog/toFindLatestCallLogPage")
    public String toFindLatestCallLogPage(){
        return "callLog/findLatestCallLog";
    }
    //以表单提交的方式过来
    @RequestMapping(value = "/callLog/findLatestCallLog",method = RequestMethod.POST)
    public String findLatestCallLog(Model model,@RequestParam("caller") String caller){
        CallLog log = hcs.findLatestCallLog(caller);
        if(log!=null){
            model.addAttribute("log",log);
        }
        return "callLog/latestCallLog";
    }


    /**
     * 查询指定人员指定年份中各个月份的的通话次数
     * @return
     */
    @RequestMapping("/callLog/toStatCallLog")
    public String toStatCallLog(){
        return "callLog/findStatCallLog";
    }
    //以表单提交的方式过来
    @RequestMapping(value = "/callLog/findStatCallLog",method = RequestMethod.POST)
    public String statCallLog(Model model,@RequestParam("caller") String caller,@RequestParam("year") String year){
        List<CallLogStat> list = hcs.statCallLogCount2(caller,year);
        model.addAttribute("stat",list);
        model.addAttribute("title",year+"年度："+"用户"+caller+" 月份通话次数统计");
        return "callLog/statCallLog";
    }
}

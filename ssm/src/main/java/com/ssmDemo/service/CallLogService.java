package com.ssmDemo.service;

import com.ssmDemo.domain.CallLog;
import com.ssmDemo.domain.CallLogRange;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/7/2 19:27
 * @Description:
 */
public interface CallLogService {
    public List<CallLog> findAll();

    /**
     * 按照范围查询通话日志
     * @param list
     * @return
     */
    public List<CallLog> findCallLogs(String call,List<CallLogRange> list);
}

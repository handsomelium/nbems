package com.ake.system.service.impl;

import com.ake.system.api.domain.SysOperLog;
import com.ake.system.mapper.SysOperLogMapper;
import com.ake.system.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lium
 * @Date 2021/12/22
 * @Description
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;


    @Override
    public List<SysOperLog> selectOperLogList(SysOperLog operLog) {
        return null;
    }

    @Override
    public int insertOperlog(SysOperLog operLog) {
        return sysOperLogMapper.insert(operLog);
    }
}

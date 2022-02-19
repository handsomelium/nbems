package com.ake.system.service;

import com.ake.system.api.domain.SysOperLog;

import java.util.List;

/**
 * @Author lium
 * @Date 2021/12/22
 * @Description
 */
public interface ISysOperLogService {
    List<SysOperLog> selectOperLogList(SysOperLog operLog);

    int insertOperlog(SysOperLog operLog);
}

package com.ake.nbems.eaps.service.impl;

import com.ake.nbems.eaps.domain.BillingOwner;
import com.ake.nbems.eaps.mapper.TestMapper;
import com.ake.nbems.eaps.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<BillingOwner> getOwnerInfo() {
        return testMapper.selectList(null);
    }
}

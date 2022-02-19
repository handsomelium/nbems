package com.ake.system.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yezt
 * @description
 * @date 2021/12/23 15:33
 */
@Data
public class User {

    private int id;
    private String username;
    private BigDecimal totalAmount;
    private Date createTime;
}

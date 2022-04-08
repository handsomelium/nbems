package com.ake.nbems.eaps.domain;

import com.ake.nbems.common.core.web.domain.BaseEntityPlus;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lium
 * @Date 2022/3/31
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "billing_owner")
public class BillingOwner extends BaseEntityPlus {

    private static final long serialVersionUID = 1L;

    private Integer ownerId;

    private String  ownerCode;

    private String  ownerName;

    private BigDecimal countArea;

    private BigDecimal  basePrice;

    private BigDecimal  baseMoney;

    private Integer  houseStatus;

    private Integer  areaId;

    private Integer  certificateType;

    private String  certificateNo;

    private Integer  ownerType;

    private String  tel;

    private String  email;

    private String  address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date moveInDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date  moveOutDate;

}

package com.ake.nbems.eaps.domain;

import lombok.Data;

@Data
public class ResultBO {
    private String msg;
    private boolean result;
    private Object data;
}
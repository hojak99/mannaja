package com.depromeet.mannaja.controller.request;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class CreatePlan implements Serializable {
    private Long memberId;

    private String name;

    private String planDate;
}

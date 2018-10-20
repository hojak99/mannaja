package com.depromeet.mannaja.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
public class CreatePlan implements Serializable {
    @NotNull
    private Long memberId;

    @NotNull
    private String name;

    @NotNull
    private String planDate;
}

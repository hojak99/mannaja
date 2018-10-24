package com.depromeet.mannaja.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;

@Getter
public class CreatePlan implements Serializable {
    @NotNull
    private Long memberId;

    @NotNull
    private String name;

    @NotNull
    private String planDate;
}

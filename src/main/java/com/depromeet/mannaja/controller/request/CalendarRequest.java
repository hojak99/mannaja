package com.depromeet.mannaja.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CalendarRequest {

    @NotNull
    private Long memberId;
}

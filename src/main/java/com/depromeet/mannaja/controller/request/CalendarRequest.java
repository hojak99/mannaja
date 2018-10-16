package com.depromeet.mannaja.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CalendarRequest {

    private LocalDate yearMonth;

    @NotNull
    private Long memberId;
}

package com.depromeet.mannaja.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.YearMonth;

@Data
@Builder
public class CalendarRequest {

    @NotNull
    private YearMonth yearMonth;

    @NotNull
    private Long memberId;
}

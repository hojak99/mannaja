package com.depromeet.mannaja.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class CalendarRequest {

    @NotNull
    private String yearMonth;

    @NotNull
    private Long memberId;
}

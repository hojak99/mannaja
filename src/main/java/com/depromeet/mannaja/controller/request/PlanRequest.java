package com.depromeet.mannaja.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PlanRequest {
    Long planId;
    LocalDate settleDate;
}

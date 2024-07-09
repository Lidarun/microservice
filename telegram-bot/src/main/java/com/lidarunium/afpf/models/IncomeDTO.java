package com.lidarunium.afpf.models;

import com.lidarunium.afpf.enums.IncomeType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record IncomeDTO (String userId, IncomeType incomeType, BigDecimal count) {}

package com.sizzle.server.domains.enums;

import com.sizzle.server.enums.IEnumText;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(name = "DayType", description = "목표 요일 타입")
public enum DayType implements IEnumText {

    MON("MON"), TUE("TUE"), WED("WED"), THU("THU"), FRI("FRI"), SAT("SAT"), SUN("SUN");

    private final String text;
}

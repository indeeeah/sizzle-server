package com.sizzle.server.domains.enums;

import com.sizzle.server.enums.IEnumText;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(name = "TargetDateType", description = "목표 날짜 타입")
public enum TargetDateType implements IEnumText {

	DAY("DAY"),
	MONTH("MONTH"),
	YEAR("YEAR");

	private final String text;
}

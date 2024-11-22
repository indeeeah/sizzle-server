package com.sizzle.server.domains.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sizzle.server.domains.dtos.PlanBaseDto;
import com.sizzle.server.domains.entities.Plan;
import com.sizzle.server.domains.services.PlansService;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/plans")
@Tag(name = "Plans", description = "일정 API")
public class PlansController {

    private final PlansService svc;
    private final ModelMapper mapper;

    @PostMapping
    @Operation(operationId = "planCreate", summary = "일정 생성", description = "일정을 생성합니다.")
    public ResponseEntity<?> create(@Validated @RequestBody PlanBaseDto.Post dto)
            throws BadRequestException {
        List<Plan> plans = svc.create(dto);

        return ResponseEntity.ok(mapper.map(plans, Plan.class));
    }
}

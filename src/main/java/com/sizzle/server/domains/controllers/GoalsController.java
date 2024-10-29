package com.sizzle.server.domains.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sizzle.server.domains.dtos.GoalBaseDto;
import com.sizzle.server.domains.services.GoalsService;
import com.sizzle.server.exceptions.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/goals")
@Tag(name = "Goals", description = "목표 API")
public class GoalsController {

    private final GoalsService svc;

    @PostMapping
    @Operation(operationId = "goalCreate", summary = "목표 생성", description = "목표를 생성합니다.")
    public ResponseEntity<?> create(@Validated @RequestBody GoalBaseDto.Post dto)
            throws BadRequestException {
        svc.add(dto);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(operationId = "goalUpdate", summary = "목표 업데이트", description = "목표를 업데이트합니다.")
    public ResponseEntity<?> update(@PathVariable UUID id,
            @Validated @RequestBody GoalBaseDto.Update dto) throws BadRequestException {
        svc.update(id, dto);

        return ResponseEntity.ok().build();
    }
}

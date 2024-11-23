package com.sizzle.server.domains.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sizzle.server.domains.dtos.GoalBaseDto;
import com.sizzle.server.domains.entities.Goal;
import com.sizzle.server.domains.services.GoalsService;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;

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
    private final ModelMapper mapper;

    @PostMapping
    @Operation(operationId = "goalCreate", summary = "목표 생성", description = "목표를 생성합니다.")
    public ResponseEntity<Goal> create(@Validated @RequestBody GoalBaseDto.Post dto)
            throws BadRequestException {
        Goal goal = svc.create(dto);

        return ResponseEntity.ok(mapper.map(goal, Goal.class));
    }

    @GetMapping
    @Operation(operationId = "goalsGet", summary = "목표 목록", description = "목표 목록을 반환합니다.")
    public ResponseEntity<List<Goal>> findByUserId(@RequestParam(value = "userId", required = true) UUID userId)
            throws BadRequestException {
        if (userId == null) {
            throw new BadRequestException("userId가 필요합니다.");
        }

        List<Goal> goals = svc.findByUserId(userId);

        return ResponseEntity.ok(mapper.map(goals, Goal.class));
    }

    @PatchMapping("/{id}")
    @Operation(operationId = "goalUpdate", summary = "목표 업데이트", description = "목표를 업데이트합니다.")
    public ResponseEntity<Goal> update(@PathVariable UUID id,
            @Validated @RequestBody GoalBaseDto.Update dto) throws BadRequestException {
        Goal goal = svc.update(id, dto);

        return ResponseEntity.ok(mapper.map(goal, Goal.class));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "goalDelete", summary = "목표 삭제", description = "목표를 삭제합니다.")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws BadRequestException {
        svc.delete(id);

        return ResponseEntity.ok().build();
    }
}

package com.sizzle.server.domains.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sizzle.server.domains.dtos.GoalBaseDto;
import com.sizzle.server.domains.entities.Goal;
import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.repositories.GoalsRepository;
import com.sizzle.server.domains.repositories.UsersRepository;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;
import com.sizzle.server.utils.UuidV7;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoalsService {

    private final GoalsRepository repo;
    private final UsersRepository userRepo;
    private final ModelMapper mapper;

    public Goal add(GoalBaseDto.Post dto) throws BadRequestException {
        User user = userRepo.findById(dto.getUserId());
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        Goal entity = mapper.map(dto, Goal.class);

        entity.setId(UuidV7.randomUuid());

        return repo.save(entity);
    }

    public Goal update(UUID id, GoalBaseDto.Update dto) throws BadRequestException {
        Goal goal = repo.findById(id);
        if (goal == null) {
            throw new BadRequestException("등록되지 않은 목표입니다.");
        }

        if (dto.getTitle() != null) {
            goal.setTitle(dto.getTitle());
        }
        if (dto.getContent() != null) {
            goal.setContent(dto.getContent());
        }
        if (dto.getTargetDate() != null) {
            goal.setTargetDate(dto.getTargetDate());
        }
        if (dto.getTargetDateType() != null) {
            goal.setTargetDateType(dto.getTargetDateType());
        }
        if (dto.getPhotoPath() != null) {
            goal.setPhotoPath(dto.getPhotoPath());
        }
        if (dto.getStartedAt() != null) {
            goal.setStartedAt(dto.getStartedAt());
        }
        if (dto.getEndedAt() != null) {
            goal.setEndedAt(dto.getEndedAt());
        }
        if (dto.getAchivedAt() != null) {
            goal.setAchivedAt(dto.getAchivedAt());
        }

        return repo.save(goal);
    }

    public Goal delete(UUID id) throws BadRequestException {
        Goal goal = repo.findById(id);
        if (goal == null) {
            throw new BadRequestException("등록되지 않은 목표입니다.");
        }

        goal.setDeletedAt(LocalDateTime.now());

        return repo.save(goal);
    }
}

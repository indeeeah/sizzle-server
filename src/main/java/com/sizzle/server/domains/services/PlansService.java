package com.sizzle.server.domains.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sizzle.server.domains.dtos.PlanBaseDto;
import com.sizzle.server.domains.entities.Plan;
import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.repositories.PlansRepository;
import com.sizzle.server.domains.repositories.UsersRepository;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlansService {

    private final PlansRepository repo;
    private final UsersRepository usersRepo;
    private final ModelMapper mapper;

    public List<Plan> create(PlanBaseDto.Post dto) throws BadRequestException {
        User user = usersRepo.findById(dto.getUserId());
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        return createPlan(dto);
    }

    @Transactional
    private List<Plan> createPlan(PlanBaseDto.Post dto) throws BadRequestException {
        List<Plan> plans = new ArrayList<>();

        dto.getDayTypes().forEach(dayType -> {
            Plan plan = mapper.map(dto, Plan.class);

            plan.setDayType(dayType);

            plans.add(plan);
        });

        return repo.saveAll(plans);
    }

    public List<Plan> findByUserId(UUID userId) throws BadRequestException {
        User user = usersRepo.findById(userId);
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        return repo.findByUserId(userId);
    }
}

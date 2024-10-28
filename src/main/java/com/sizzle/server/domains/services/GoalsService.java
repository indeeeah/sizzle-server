package com.sizzle.server.domains.services;

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

	public Goal add(GoalBaseDto dto) throws BadRequestException {
		User user = userRepo.findById(dto.getUserId());
		if (user == null) {
			throw new BadRequestException("등록되지 않은 사용자입니다.");
		}

		Goal entity = mapper.map(dto, Goal.class);

		entity.setId(UuidV7.randomUuid());

		return repo.save(entity);
	}
}

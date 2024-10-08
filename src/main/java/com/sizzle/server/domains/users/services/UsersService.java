package com.sizzle.server.domains.users.services;

import org.springframework.stereotype.Service;

import com.sizzle.server.domains.users.dtos.UserBaseDto;
import com.sizzle.server.domains.users.entities.User;
import com.sizzle.server.domains.users.repositories.UsersRepository;
import com.sizzle.server.utils.BCryptPassword;
import com.sizzle.server.utils.ModelMapper;
import com.sizzle.server.utils.UuidV7;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

	private final UsersRepository repo;

	private final ModelMapper mapper;

	public User add(UserBaseDto.Post dto) {
		User entity = mapper.map(dto, User.class);

		entity.setId(UuidV7.randomUuid());
		entity.setPassword(BCryptPassword.hash(dto.getPassword()));

		return repo.save(entity);
	}
}

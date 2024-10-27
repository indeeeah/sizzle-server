package com.sizzle.server.domains.users.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sizzle.server.domains.users.dtos.UserBaseDto;
import com.sizzle.server.domains.users.entities.User;
import com.sizzle.server.domains.users.filter.UserFilter;
import com.sizzle.server.domains.users.repositories.UsersRepository;
import com.sizzle.server.exceptions.BadRequestException;
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

	public List<User> search(UserFilter filter) throws BadRequestException {
		return repo.search(filter);
	}

	public User add(UserBaseDto.Post dto) throws BadRequestException {
		String email = dto.getEmail();
		String nickname = dto.getNickname();

		User registeredEmail = repo.findByEmail(email);
		if (registeredEmail != null) {
			throw new BadRequestException("이미 등록된 이메일입니다.\n기존에 등록된 이메일로 사용해주세요.");
		}

		User registeredNickname = repo.findByNickname(nickname);
		if (registeredNickname != null) {
			throw new BadRequestException("이미 등록된 닉네임입니다.\n다른 닉네임을 사용해주세요.");
		}

		User entity = mapper.map(dto, User.class);

		entity.setId(UuidV7.randomUuid());
		entity.setPassword(BCryptPassword.hash(dto.getPassword()));

		return repo.save(entity);
	}
}

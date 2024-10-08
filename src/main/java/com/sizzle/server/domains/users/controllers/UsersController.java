package com.sizzle.server.domains.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sizzle.server.domains.users.dtos.UserBaseDto;
import com.sizzle.server.domains.users.entities.User;
import com.sizzle.server.domains.users.services.UsersService;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Users", description = "사용자 API")
public class UsersController {

	private final UsersService svc;
	private final ModelMapper mapper;

	@PostMapping
	@Operation(operationId = "userAdd", summary = "사용자 생성", description = "사용자를 생성합니다.")
	public ResponseEntity<UserBaseDto> create(@Validated @RequestBody UserBaseDto.Post dto)
			throws BadRequestException {
		User user = svc.add(dto);

		return ResponseEntity.ok(mapper.map(user, UserBaseDto.class));
	}
}

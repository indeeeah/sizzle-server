package com.sizzle.server.domains.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sizzle.server.domains.dtos.UserBaseDto;
import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.filter.UserFilter;
import com.sizzle.server.domains.services.UsersService;
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
    @Operation(operationId = "userCreate", summary = "사용자 생성", description = "사용자를 생성합니다.")
    public ResponseEntity<UserBaseDto.Get> create(@Validated @RequestBody UserBaseDto.Post dto)
            throws BadRequestException {
        User user = svc.add(dto);

        return ResponseEntity.ok(mapper.map(user, UserBaseDto.Get.class));
    }

    @GetMapping("/search")
    @Operation(operationId = "userSearch", summary = "사용자 검색", description = "사용자를 조건에 따라 검색합니다.")
    public ResponseEntity<List<UserBaseDto.Get>> search(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "nickname", required = false) String nickname)
            throws BadRequestException {
        UserFilter filter = new UserFilter.Builder().email(email).nickname(nickname).build();

        if (!filter.filterConditions()) {
            throw new BadRequestException("사용자 검색 조건이 없습니다.");
        }

        List<User> users = svc.search(filter);

        return ResponseEntity.ok(mapper.map(users, UserBaseDto.Get.class));
    }
}

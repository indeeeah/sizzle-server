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
        User user = svc.create(dto);

        return ResponseEntity.ok(mapper.map(user, UserBaseDto.Get.class));
    }

    @GetMapping("/{id}")
    @Operation(operationId = "userGet", summary = "사용자 상세 정보", description = "사용자의 상세 정보를 가져옵니다.")
    public ResponseEntity<UserBaseDto.Detail> detail(@PathVariable UUID id)
            throws BadRequestException {
        UserBaseDto.Detail detail = svc.detail(id);

        return ResponseEntity.ok(detail);
    }

    @PatchMapping("/{id}")
    @Operation(operationId = "userUpdate", summary = "사용자 업데이트", description = "사용자 정보를 업데이트합니다.")
    public ResponseEntity<UserBaseDto.Get> update(@PathVariable UUID id,
            @Validated @RequestBody UserBaseDto.Update dto) throws BadRequestException {
        User user = svc.update(id, dto);

        return ResponseEntity.ok(mapper.map(user, UserBaseDto.Get.class));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "userDelete", summary = "사용자 삭제", description = "사용자를 삭제합니다.")
    public ResponseEntity<?> delete(@PathVariable UUID id) throws BadRequestException {
        svc.delete(id);

        return ResponseEntity.ok().build();
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

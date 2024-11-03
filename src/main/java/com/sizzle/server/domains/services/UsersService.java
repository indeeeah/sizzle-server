package com.sizzle.server.domains.services;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sizzle.server.domains.dtos.UserBaseDto;
import com.sizzle.server.domains.entities.Category;
import com.sizzle.server.domains.entities.Goal;
import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.filter.UserFilter;
import com.sizzle.server.domains.repositories.CategoriesRepository;
import com.sizzle.server.domains.repositories.GoalsRepository;
import com.sizzle.server.domains.repositories.UsersRepository;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.BCryptPassword;
import com.sizzle.server.utils.ModelMapper;
import com.sizzle.server.utils.UuidV7;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository repo;
    private final GoalsRepository goalsRepo;
    private final CategoriesRepository categoriesRepo;
    private final ModelMapper mapper;
    private final ObjectMapper objectMapper;

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

        return create(entity);
    }

    @Transactional
    private User create(User user) throws BadRequestException {
        InputStream inputStream = getClass().getResourceAsStream("/templates/categories.json");

        if (inputStream == null) {
            return repo.save(user);
        }

        User createdUser = repo.save(user);

        try {
            List<Category> categories =
                    objectMapper.readValue(inputStream, new TypeReference<List<Category>>() {

                    });

            categories.forEach(category -> {
                category.setId(UuidV7.randomUuid());
                category.setUserId(createdUser.getId());
            });

            categoriesRepo.saveAll(categories);
        } catch (IOException e) {
            return null;
        }

        return createdUser;
    }

    public UserBaseDto.Detail detail(UUID id) throws BadRequestException {
        User user = repo.findById(id);
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        List<Goal> goals = goalsRepo.findByUserId(id);

        UserBaseDto.Detail detail = mapper.map(user, UserBaseDto.Detail.class);
        detail.setGoals(goals);

        return detail;
    }

    public User update(UUID id, UserBaseDto.Update dto) throws BadRequestException {
        User user = repo.findById(id);
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        if (dto.getNickname() != null) {
            user.setNickname(dto.getNickname());
        }
        if (dto.getPassword() != null) {
            user.setPassword(BCryptPassword.hash(dto.getPassword()));
        }
        if (dto.getIntroduce() != null) {
            user.setIntroduce(dto.getIntroduce());
        }
        if (dto.getThumbnailPath() != null) {
            user.setThumbnailPath(dto.getThumbnailPath());
        }

        return repo.save(user);
    }

    public User delete(UUID id) throws BadRequestException {
        User user = repo.findById(id);
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        user.setDeletedAt(LocalDateTime.now());

        return repo.save(user);
    }

    public List<User> search(UserFilter filter) throws BadRequestException {
        return repo.search(filter);
    }
}

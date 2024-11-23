package com.sizzle.server.domains.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sizzle.server.domains.dtos.CategoryBaseDto;
import com.sizzle.server.domains.entities.Category;
import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.repositories.CategoriesRepository;
import com.sizzle.server.domains.repositories.UsersRepository;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;
import com.sizzle.server.utils.UuidV7;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository repo;
    private final UsersRepository usersRepo;
    private final ModelMapper mapper;

    public Category create(CategoryBaseDto.Post dto) throws BadRequestException {
        User user = usersRepo.findById(dto.getUserId());
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        if (dto.getParentId() != null) {
            Category parentCategory = repo.findById(dto.getParentId());

            if (parentCategory == null || !parentCategory.getUserId().equals(dto.getUserId())
                    || parentCategory.getDeletedAt() != null) {
                throw new BadRequestException("등록되지 않은 상위 카테고리입니다.");
            }
        }

        Category entity = mapper.map(dto, Category.class);

        entity.setId(UuidV7.randomUuid());

        return repo.save(entity);
    }

    public List<Category> findAll(UUID userId) throws BadRequestException {
        User user = usersRepo.findById(userId);
        if (user == null) {
            throw new BadRequestException("등록되지 않은 사용자입니다.");
        }

        List<Category> categories = repo.findByUserId(userId);

        return categories;
    }

    public Category update(UUID id, CategoryBaseDto.Update dto) throws BadRequestException {
        Category category = repo.findById(id);
        if (category == null) {
            throw new BadRequestException("등록되지 않은 카테고리입니다.");
        }

        if (dto.getName() != null) {
            category.setName(dto.getName());
        }
        if (dto.getColor() != null) {
            category.setColor(dto.getColor());
        }
        if (dto.getParentId() != null) {
            category.setParentId(dto.getParentId());
        }

        return repo.save(category);
    }

    public Category delete(UUID id) throws BadRequestException {
        Category category = repo.findById(id);
        if (category == null) {
            throw new BadRequestException("등록되지 않은 카테고리입니다.");
        }

        category.setDeletedAt(LocalDateTime.now());

        return repo.save(category);
    }
}

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

import com.sizzle.server.domains.dtos.CategoryBaseDto;
import com.sizzle.server.domains.entities.Category;
import com.sizzle.server.domains.services.CategoriesService;
import com.sizzle.server.exceptions.BadRequestException;
import com.sizzle.server.utils.ModelMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
@Tag(name = "Categories", description = "카테고리 API")
public class CategoriesController {

    private final CategoriesService svc;
    private final ModelMapper mapper;

    @PostMapping
    @Operation(operationId = "categoryCreate", summary = "카테고리 생성", description = "카테고리를 생성합니다.")
    public ResponseEntity<Category> create(@Validated @RequestBody CategoryBaseDto.Post dto)
            throws BadRequestException {
        Category category = svc.create(dto);

        return ResponseEntity.ok(mapper.map(category, Category.class));
    }

    @GetMapping
    @Operation(operationId = "categoriesGet", summary = "카테고리 목록", description = "카테고리 목록을 반환합니다.")
    public ResponseEntity<List<Category>> findAll(@RequestParam(value = "userId", required = true) UUID userId)
            throws BadRequestException {
        if (userId == null) {
            throw new BadRequestException("userId가 필요합니다.");
        }

        List<Category> categories = svc.findAll(userId);

        return ResponseEntity.ok(mapper.map(categories, Category.class));
    }

    @PatchMapping("/{id}")
    @Operation(operationId = "categoryUpdate", summary = "카테고리 수정", description = "카테고리를 수정합니다.")
    public ResponseEntity<Category> updateCategory(@PathVariable UUID id,
            @Validated @RequestBody CategoryBaseDto.Update dto) throws BadRequestException {
        Category category = svc.update(id, dto);

        return ResponseEntity.ok(mapper.map(category, Category.class));
    }

    @DeleteMapping("/{id}")
    @Operation(operationId = "categoryDelete", summary = "카테고리 삭제", description = "카테고리를 삭제합니다.")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) throws BadRequestException {
        svc.delete(id);

        return ResponseEntity.ok().build();
    }
}

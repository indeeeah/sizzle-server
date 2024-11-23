package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import com.sizzle.server.domains.entities.Category;

public interface CategoriesQueryDslRepository {
    Category findById(UUID id);

    List<Category> findByUserId(UUID userId);
}

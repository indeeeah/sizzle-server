package com.sizzle.server.domains.repositories;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.sizzle.server.domains.entities.Category;

@Repository
class CategoriesQueryDslRepositoryImpl extends QuerydslRepositorySupport
        implements CategoriesQueryDslRepository {

    public CategoriesQueryDslRepositoryImpl() {
        super(Category.class);
    }
}

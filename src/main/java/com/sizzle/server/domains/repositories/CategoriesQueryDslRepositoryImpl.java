package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQuery;
import com.sizzle.server.domains.entities.Category;
import com.sizzle.server.domains.entities.QCategory;

@Repository
class CategoriesQueryDslRepositoryImpl extends QuerydslRepositorySupport
        implements CategoriesQueryDslRepository {

    public CategoriesQueryDslRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public Category findById(UUID id) {
        QCategory entity = QCategory.category;

        Category category = from(entity).where(entity.id.eq(id)).fetchOne();

        return category;
    }

    @Override
    public List<Category> findByUserId(UUID userId) {
        QCategory entity = QCategory.category;

        JPQLQuery<Category> categoryQuery = from(entity).where(entity.userId.eq(userId))
                .where(entity.deletedAt.isNull());

        List<Category> categories = categoryQuery.fetch();

        return categories;
    }
}

package com.sizzle.server.domains.repositories;

import com.sizzle.server.base.BaseRepository;
import com.sizzle.server.domains.entities.Category;

public interface CategoriesRepository
        extends BaseRepository<Category, Long>, CategoriesQueryDslRepository {

}

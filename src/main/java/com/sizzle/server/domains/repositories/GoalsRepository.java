package com.sizzle.server.domains.repositories;

import com.sizzle.server.base.BaseRepository;
import com.sizzle.server.domains.entities.Goal;

public interface GoalsRepository extends BaseRepository<Goal, Long>, GoalsQueryDslRepository {
}

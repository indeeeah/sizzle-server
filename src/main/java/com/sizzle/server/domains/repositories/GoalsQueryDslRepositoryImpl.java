package com.sizzle.server.domains.repositories;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.sizzle.server.domains.entities.Goal;

@Repository
class GoalsQueryDslRepositoryImpl extends QuerydslRepositorySupport implements GoalsQueryDslRepository {

	public GoalsQueryDslRepositoryImpl() {
		super(Goal.class);
	}
}

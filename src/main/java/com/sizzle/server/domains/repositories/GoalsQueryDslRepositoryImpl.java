package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQuery;
import com.sizzle.server.domains.entities.Goal;
import com.sizzle.server.domains.entities.QGoal;

@Repository
class GoalsQueryDslRepositoryImpl extends QuerydslRepositorySupport
        implements GoalsQueryDslRepository {

    public GoalsQueryDslRepositoryImpl() {
        super(Goal.class);
    }

    @Override
    public Goal findById(UUID id) {
        QGoal entity = QGoal.goal;

        Goal goal = from(entity).where(entity.id.eq(id)).fetchOne();

        return goal;
    }

    @Override
    public List<Goal> findByUserId(UUID userId) {
        QGoal entity = QGoal.goal;

        JPQLQuery<Goal> goalQuery = from(entity).where(entity.userId.eq(userId)).where(entity.deletedAt.isNull());

        List<Goal> goals = goalQuery.fetch();

        return goals;
    }
}

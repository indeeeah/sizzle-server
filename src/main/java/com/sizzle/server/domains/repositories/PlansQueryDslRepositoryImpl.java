package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.jpa.JPQLQuery;
import com.sizzle.server.domains.entities.Plan;
import com.sizzle.server.domains.entities.QPlan;

public class PlansQueryDslRepositoryImpl extends QuerydslRepositorySupport
        implements PlansQueryDslRepository {

    public PlansQueryDslRepositoryImpl() {
        super(Plan.class);
    }

    public List<Plan> findByUserId(UUID userId) {
        QPlan entity = QPlan.plan;

        JPQLQuery<Plan> planQuery =
                from(entity).where(entity.userId.eq(userId)).where(entity.deletedAt.isNull());

        List<Plan> plans = planQuery.fetch();

        return plans;
    }
}

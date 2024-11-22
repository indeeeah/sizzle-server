package com.sizzle.server.domains.repositories;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.sizzle.server.domains.entities.Plan;

public class PlansQueryDslRepositoryImpl extends QuerydslRepositorySupport
        implements PlansQueryDslRepository {

    public PlansQueryDslRepositoryImpl() {
        super(Plan.class);
    }
}

package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import com.sizzle.server.domains.entities.Plan;

public interface PlansQueryDslRepository {
    Plan findById(UUID id);

    List<Plan> findByUserId(UUID userId);
}

package com.sizzle.server.domains.repositories;

import java.util.UUID;

import com.sizzle.server.domains.entities.Goal;

public interface GoalsQueryDslRepository {
    Goal findById(UUID id);
}

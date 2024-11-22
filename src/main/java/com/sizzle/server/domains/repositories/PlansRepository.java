package com.sizzle.server.domains.repositories;

import com.sizzle.server.base.BaseRepository;
import com.sizzle.server.domains.entities.Plan;

public interface PlansRepository extends BaseRepository<Plan, Long>, PlansQueryDslRepository {

}

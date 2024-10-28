package com.sizzle.server.domains.repositories;

import com.sizzle.server.base.BaseRepository;
import com.sizzle.server.domains.entities.User;

public interface UsersRepository extends BaseRepository<User, Long>, UsersQueryDslRepository {
}

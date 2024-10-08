package com.sizzle.server.domains.users.repositories;

import com.sizzle.server.base.BaseRepository;
import com.sizzle.server.domains.users.entities.User;

public interface UsersRepository extends BaseRepository<User, Long>, UsersQueryDslRepository {
}

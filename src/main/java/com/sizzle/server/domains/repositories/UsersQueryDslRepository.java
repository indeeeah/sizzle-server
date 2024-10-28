package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.filter.UserFilter;

public interface UsersQueryDslRepository {
	List<User> search(UserFilter filter);

	User findById(UUID id);

	User findByEmail(String email);

	User findByNickname(String nickname);
}

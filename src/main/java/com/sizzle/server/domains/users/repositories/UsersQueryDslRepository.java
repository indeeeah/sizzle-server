package com.sizzle.server.domains.users.repositories;

import java.util.List;

import com.sizzle.server.domains.users.entities.User;
import com.sizzle.server.domains.users.filter.UserFilter;

public interface UsersQueryDslRepository {
	List<User> search(UserFilter filter);

	User findByEmail(String email);

	User findByNickname(String nickname);
}

package com.sizzle.server.domains.users.repositories;

import com.sizzle.server.domains.users.entities.User;

interface UsersQueryDslRepository {
	User findByEmail(String email);

	User findByNickname(String nickname);
}

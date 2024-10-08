package com.sizzle.server.domains.users.repositories;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.sizzle.server.domains.users.entities.QUser;
import com.sizzle.server.domains.users.entities.User;

class UsersDslRepositoryImpl extends QuerydslRepositorySupport implements
		UsersQueryDslRepository {

	public UsersDslRepositoryImpl() {
		super(User.class);
	}

	@Override
	public User findByEmail(String email) {
		QUser entity = QUser.user;

		User user = from(entity).where(entity.email.eq(email)).fetchFirst();

		return user;
	}

	@Override
	public User findByNickname(String nickname) {
		QUser entity = QUser.user;

		User user = from(entity).where(entity.nickname.eq(nickname)).fetchFirst();

		return user;
	}
}

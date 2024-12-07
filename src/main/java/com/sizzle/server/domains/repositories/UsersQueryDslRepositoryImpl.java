package com.sizzle.server.domains.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.sizzle.server.domains.entities.QUser;
import com.sizzle.server.domains.entities.User;
import com.sizzle.server.domains.filter.UserFilter;

@Repository
class UsersQueryDslRepositoryImpl extends QuerydslRepositorySupport
        implements UsersQueryDslRepository {

    public UsersQueryDslRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> search(UserFilter filter) {
        QUser entity = QUser.user;

        BooleanBuilder where = new BooleanBuilder();
        if (filter.filterEmail()) {
            where.and(entity.email.eq(filter.getEmail()));
        }
        if (filter.filterNickname()) {
            where.and(entity.nickname.eq(filter.getNickname()));
        }

        JPQLQuery<User> userQuery = from(entity).where(where);

        List<User> users = userQuery.fetch();

        return users;
    }

    @Override
    public User findById(UUID id) {
        QUser entity = QUser.user;

        User user = from(entity).where(entity.id.eq(id)).fetchOne();

        return user;
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

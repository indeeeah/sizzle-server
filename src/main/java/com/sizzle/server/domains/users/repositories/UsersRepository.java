package com.sizzle.server.domains.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.sizzle.server.domains.users.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
// @Repository
// public interface UsersRepository extends JpaRepository<User, Long>,
// UsersQueryDslRepository {
// }

// interface UsersQueryDslRepository {
// }

// class UsersQueryDslRepositoryImpl extends QuerydslRepositorySupport
// implements UsersQueryDslRepository {
// }

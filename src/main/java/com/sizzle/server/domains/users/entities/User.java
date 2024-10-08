package com.sizzle.server.domains.users.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.sizzle.server.domains.users.enums.SocialType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "USERS", indexes = { @Index(name = "idx_name", columnList = "name"),
		@Index(name = "idx_email", columnList = "email") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	UUID id;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "email", nullable = false)
	String email;

	@Column(name = "password", nullable = false)
	String password;

	@Column(name = "nickname", nullable = false)
	String nickname;

	@Enumerated(EnumType.STRING)
	@Column(name = "social")
	SocialType social;

	@Column(name = "deleted_at")
	LocalDateTime deletedAt;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreationTimestamp
	LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	LocalDateTime updatedAt;
}

package com.sizzle.server.domains.dtos;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import com.sizzle.server.domains.enums.SocialType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(name = "userBaseDto")
public class UserBaseDto implements Serializable {

	@NotNull
	String name;

	@NotNull
	String email;

	@NotNull
	String nickname;

	Optional<String> introduce;

	Optional<String> thumbnailPath;

	@Schema(name = "social")
	Optional<SocialType> social;

	@Data
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = true)
	@FieldDefaults(level = AccessLevel.PRIVATE)
	@Schema(name = "userPostType")
	public static class Post extends UserBaseDto {

		@NotNull
		String password;
	}

	@Data
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = true)
	@FieldDefaults(level = AccessLevel.PRIVATE)
	@Schema(name = "userGetType")
	public static class Get extends UserBaseDto {

		@NotNull
		UUID id;
	}
}

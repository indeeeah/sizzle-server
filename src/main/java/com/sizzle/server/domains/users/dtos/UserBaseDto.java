package com.sizzle.server.domains.users.dtos;

import java.io.Serializable;

import com.sizzle.server.domains.users.enums.SocialType;

import io.micrometer.common.lang.Nullable;
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

	String name;

	String email;

	@Nullable
	@Schema(name = "social")
	SocialType social;

	@Data
	@NoArgsConstructor
	@EqualsAndHashCode(callSuper = true)
	@FieldDefaults(level = AccessLevel.PRIVATE)
	@Schema(name = "userPostType")
	public static class Post extends UserBaseDto {

		@NotNull
		String password;
	}
}

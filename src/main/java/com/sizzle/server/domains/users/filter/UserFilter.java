package com.sizzle.server.domains.users.filter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserFilter {

	String email;

	String nickname;

	private UserFilter(Builder builder) {
		this.email = builder.email;
		this.nickname = builder.nickname;
	}

	public boolean filterEmail() {
		return this.email != null;
	}

	public boolean filterNickname() {
		return this.nickname != null;
	}

	public boolean filterConditions() {
		return this.email != null || this.nickname != null;
	}

	@NoArgsConstructor
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class Builder {

		String email;

		String nickname;

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder nickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public UserFilter build() {
			return new UserFilter(this);
		}
	}
}

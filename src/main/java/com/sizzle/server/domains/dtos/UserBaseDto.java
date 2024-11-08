package com.sizzle.server.domains.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.sizzle.server.domains.entities.Goal;
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

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "userPostType")
    public static class Post extends UserBaseDto {

        @NotNull
        String name;

        @NotNull
        String email;

        @NotNull
        String password;

        @NotNull
        String nickname;

        @Schema(nullable = true)
        SocialType social;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "userGetType")
    public static class Get extends UserBaseDto {

        @NotNull
        UUID id;

        @NotNull
        String name;

        @NotNull
        String email;

        @NotNull
        String nickname;

        @Schema(nullable = true)
        SocialType social;

        @Schema(nullable = true)
        String introduce;

        @Schema(nullable = true)
        String thumbnailPath;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "userUpdateType")
    public static class Update extends UserBaseDto {

        @Schema(nullable = true)
        String password;

        @Schema(nullable = true)
        String nickname;

        @Schema(nullable = true)
        String introduce;

        @Schema(nullable = true)
        String thumbnailPath;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "userDetailType")
    public static class Detail extends UserBaseDto {

        @NotNull
        UUID id;

        @NotNull
        String name;

        @NotNull
        String email;

        @NotNull
        String nickname;

        @Schema(nullable = true)
        SocialType social;

        @Schema(nullable = true)
        List<Goal> goals;

        @Schema(nullable = true)
        String introduce;

        @Schema(nullable = true)
        String thumbnailPath;
    }
}

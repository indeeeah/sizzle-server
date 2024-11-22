package com.sizzle.server.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.sizzle.server.domains.enums.DateType;

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
@Schema(name = "goalBaseDto")
public class GoalBaseDto implements Serializable {

    @Schema(nullable = true)
    String content;

    @Schema(nullable = true)
    LocalDateTime startedAt;

    @Schema(nullable = true)
    LocalDate targetDate;

    @Schema(nullable = true)
    DateType targetDateType;

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "goalGetType")
    public static class Get extends GoalBaseDto {

        @NotNull
        UUID id;

        @NotNull
        UUID userId;

        @NotNull
        String title;

        @Schema(nullable = true)
        String photoPath;

        @Schema(nullable = true)
        LocalDateTime endedAt;

        @Schema(nullable = true)
        LocalDateTime achivedAt;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "goalPostType")
    public static class Post extends GoalBaseDto {
        @NotNull
        UUID userId;

        @NotNull
        String title;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "goalUpdateType")
    public static class Update extends GoalBaseDto {
        @Schema(nullable = true)
        String title;

        @Schema(nullable = true)
        String photoPath;

        @Schema(nullable = true)
        LocalDateTime endedAt;

        @Schema(nullable = true)
        LocalDateTime achivedAt;
    }
}

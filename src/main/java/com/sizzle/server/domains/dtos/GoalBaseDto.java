package com.sizzle.server.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.sizzle.server.domains.enums.TargetDateType;

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

    @NotNull
    UUID userId;

    @NotNull
    String title;

    @Schema(description = "Goal content", nullable = true)
    String content;

    @Schema(description = "Start date and time", nullable = true)
    LocalDateTime startedAt;

    @Schema(description = "Target date", nullable = true)
    LocalDate targetDate;

    @Schema(description = "Target date type", nullable = true)
    TargetDateType targetDateType;

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "goalGetType")
    public static class Get extends GoalBaseDto {

        @NotNull
        UUID id;

        @Schema(description = "End date and time", nullable = true)
        LocalDateTime endedAt;

        @Schema(description = "Achieved date and time", nullable = true)
        LocalDateTime achivedAt;
    }
}

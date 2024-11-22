package com.sizzle.server.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import com.sizzle.server.domains.enums.DateType;
import com.sizzle.server.domains.enums.DayType;

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
@Schema(name = "planBaseDto")
public class PlanBaseDto implements Serializable {

    @NotNull
    UUID userId;

    @Schema(nullable = true)
    UUID categoryId;

    @NotNull
    String title;

    @Schema(nullable = true)
    String content;

    @NotNull
    LocalTime startTime;

    @NotNull
    LocalTime endTime;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @Schema(nullable = true)
    DateType repeatDateType;

    @Schema(nullable = true)
    int every;

    @Schema(nullable = true)
    String location;

    @Schema(nullable = true)
    String url;

    @Schema(nullable = true)
    String folderPath;

    @Schema(nullable = true)
    int alertMinutes;

    @Schema(nullable = true)
    UUID invitedUserId;

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "planPostType")
    public static class Post extends PlanBaseDto {

        @Schema(nullable = true)
        List<DayType> dayTypes;
    }
}

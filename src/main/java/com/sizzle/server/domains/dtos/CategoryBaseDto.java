package com.sizzle.server.domains.dtos;

import java.io.Serializable;
import java.util.UUID;

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
@Schema(name = "categoryBaseDto")
public class CategoryBaseDto implements Serializable {

    @Schema(nullable = true)
    String color;

    @Schema(nullable = true)
    UUID parentId;

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "categoryPostType")
    public static class Post extends CategoryBaseDto {

        @NotNull
        UUID userId;

        @NotNull
        String name;
    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Schema(name = "categoryUpdateType")
    public static class Update extends CategoryBaseDto {
        @Schema(nullable = true)
        String name;
    }
}

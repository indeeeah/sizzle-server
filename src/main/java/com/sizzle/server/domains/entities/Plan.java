package com.sizzle.server.domains.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sizzle.server.domains.enums.DateType;
import com.sizzle.server.domains.enums.DayType;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "PLANS")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    UUID id;

    @Column(name = "user_id", nullable = false)
    UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private User user;

    @Column(name = "category_id")
    UUID categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Category category;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "start_time", nullable = false)
    LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    LocalTime endTime;

    @Column(name = "start_date", nullable = false)
    LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "repeat_date_type")
    DateType repeatDateType;

    @Column(name = "every")
    int every;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_type")
    DayType dayType;

    @Column(name = "location")
    String location;

    @Column(name = "url")
    String url;

    @Column(name = "folder_path")
    String folderPath;

    @Column(name = "alert_minutes")
    int alertMinutes;

    @Column(name = "invited_user_id")
    UUID invitedUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_user_id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnore
    private User invitedUser;

    @Column(name = "deleted_at")
    LocalDateTime deletedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt;
}

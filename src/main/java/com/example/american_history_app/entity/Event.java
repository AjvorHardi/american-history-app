package com.example.american_history_app.entity;

import com.example.american_history_app.converter.YearMonthAttributeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Entity
@Data
@Table(name = "events")
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Event date is required")//usually handled at DTO layer
    @Column(name = "event_date", nullable = false)
    @Convert(converter = YearMonthAttributeConverter.class)
    private YearMonth eventDate;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    // Constructor with required fields
    public Event(String title, String description, YearMonth eventDate) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
    }

    // Lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

//    @Override
//    public String toString() {
//        return "Event{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", eventDate=" + eventDate +
//                ", createdAt=" + createdAt +
//                '}';
//    }
}
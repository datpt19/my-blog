package unicorns.backend.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import unicorns.backend.util.Schema;

import java.time.LocalDateTime;

@Entity
@Table(schema = Schema.BLOG)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeriesPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    Long userId;

    String hashtag;

    String description;

    @CreatedDate
    LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    LocalDateTime updatedDate = LocalDateTime.now();

}

package unicorns.backend.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import unicorns.backend.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeriesDetailDomain {
    Long id;

    String title;

    Long userId;

    String hashtag;

    String createBy;

    String description;

    LocalDateTime createdDate;

    List<PostDto> posts;
}

package unicorns.backend.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import unicorns.backend.entity.SeriesPost;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeriesPostDomain {
    Long id;
    String title;

    Long userId;

    String hashtag;

    String createBy;

    String description;

    LocalDateTime createdDate;

    public SeriesPostDomain(SeriesPost seriesPost) {
        BeanUtils.copyProperties(seriesPost, this);
    }
}

package unicorns.backend.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSeriesPostRequest {
    Long seriesId;
    String title;
    String description;
    String hashtag;
}

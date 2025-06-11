package unicorns.backend.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CreateSeriesPostResponse {
    Long seriesId;
    String title;
    String hashtag;
    String description;
}

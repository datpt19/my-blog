package unicorns.backend.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import unicorns.backend.domain.SeriesDetailDomain;
import unicorns.backend.dto.request.CreateSeriesPostRequest;
import unicorns.backend.dto.request.EditSeriesRequest;
import unicorns.backend.dto.response.CreateSeriesPostResponse;
import unicorns.backend.dto.response.EditSeriesResponse;
import unicorns.backend.entity.SeriesPost;

public interface SeriesPostService {
    CreateSeriesPostResponse create(CreateSeriesPostRequest request);
    SeriesDetailDomain getById(Long id);
    Page<SeriesPost> findAllByOrderByCreatedDateDescPageable(Pageable pageable);
    EditSeriesResponse edit(EditSeriesRequest request);


}

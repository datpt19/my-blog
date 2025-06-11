package unicorns.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicorns.backend.domain.SeriesDetailDomain;
import unicorns.backend.domain.SeriesPostDomain;
import unicorns.backend.dto.request.CreateSeriesPostRequest;
import unicorns.backend.dto.response.CreateSeriesPostResponse;
import unicorns.backend.entity.SeriesPost;
import unicorns.backend.service.SeriesPostService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/series")
@Log4j2
public class SeriesPostController {
    private final SeriesPostService seriesPostService;

    @GetMapping()
    public ResponseEntity<List<SeriesPostDomain>> getPostList(Pageable pageable) {
        log.debug("REST request to get Posts : {}", pageable);
        Page<SeriesPost> seriesPosts = seriesPostService.findAllByOrderByCreatedDateDescPageable(pageable);
        Page<SeriesPostDomain> seriesPostsDomain = seriesPosts.map(SeriesPostDomain::new);
        return new ResponseEntity<>(seriesPostsDomain.getContent(), HttpStatus.OK);
    }

    @PostMapping("/createSeriesPost")
    public ResponseEntity<CreateSeriesPostResponse> createSeriesPost(@RequestBody CreateSeriesPostRequest createSeriesPostRequest) {
        CreateSeriesPostResponse createSeriesPostResponse = seriesPostService.create(createSeriesPostRequest);
        return new ResponseEntity<>(createSeriesPostResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesDetailDomain> getSeriesById(@PathVariable Long id) {
        SeriesDetailDomain seriesPostDomain = seriesPostService.getById(id);
        return new ResponseEntity<>(seriesPostDomain, HttpStatus.OK);
    }
}

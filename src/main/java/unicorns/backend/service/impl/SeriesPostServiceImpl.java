package unicorns.backend.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import unicorns.backend.domain.SeriesDetailDomain;
import unicorns.backend.domain.SeriesPostDomain;
import unicorns.backend.dto.PostDto;
import unicorns.backend.dto.request.CreateSeriesPostRequest;
import unicorns.backend.dto.request.EditSeriesRequest;
import unicorns.backend.dto.response.CreateSeriesPostResponse;
import unicorns.backend.dto.response.EditSeriesResponse;
import unicorns.backend.entity.Post;
import unicorns.backend.entity.SeriesPost;
import unicorns.backend.entity.User;
import unicorns.backend.exception.ApiException;
import unicorns.backend.repository.PostRepository;
import unicorns.backend.repository.SeriesPostRepository;
import unicorns.backend.repository.UserRepository;
import unicorns.backend.security.SecurityUtil;
import unicorns.backend.security.service.CustomUserDetails;
import unicorns.backend.service.SeriesPostService;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SeriesPostServiceImpl implements SeriesPostService {

    SeriesPostRepository seriesPostRepository;
    PostRepository postRepository;
    private final UserRepository userRepository;


    @Override
    public CreateSeriesPostResponse create(CreateSeriesPostRequest request) {
        Long authorId = SecurityUtil.getCurrentUserLogin().get().getId();
        SeriesPost seriesPost ;
        if(ObjectUtils.isEmpty(request.getSeriesId())){
            seriesPost = new SeriesPost();
        }else{
            seriesPost = seriesPostRepository.findById(request.getSeriesId()).orElseThrow(
                    () -> new ApiException("Post not found ", HttpStatus.NOT_FOUND)
            );
        }
        if (seriesPost.getUserId() != null) {
            if (!seriesPost.getUserId().equals(authorId)) {
                throw new ApiException("Post not found ", HttpStatus.FORBIDDEN);
            }
        }
        seriesPost.setTitle(request.getTitle());
        seriesPost.setUserId(authorId);
        seriesPost.setHashtag(request.getHashtag());
        seriesPost.setDescription(request.getDescription());
        seriesPostRepository.save(seriesPost);
        CreateSeriesPostResponse createSeriesPostResponse = new CreateSeriesPostResponse();
        BeanUtils.copyProperties(seriesPost, createSeriesPostResponse);
        createSeriesPostResponse.setSeriesId(seriesPost.getId());
        return createSeriesPostResponse;
    }

    @Override
    public SeriesDetailDomain getById(Long id) {
        SeriesPost seriesPost = seriesPostRepository.findById(id).orElse(null);
        if (seriesPost == null) {
            throw new ApiException("Post not found ", HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(seriesPost.getUserId()).orElseThrow(
                () -> new ApiException("User not found ", HttpStatus.NOT_FOUND)
        );
        SeriesDetailDomain seriesPostDomain = new SeriesDetailDomain();
        BeanUtils.copyProperties(seriesPost, seriesPostDomain);
        seriesPostDomain.setCreateBy(user.getUserName());
        List<Post> listPost = postRepository.findBySeriesPostId(seriesPost.getId());
        List<PostDto> postDtoList = new ArrayList<>();
        listPost.forEach(post -> {
            PostDto postDto = new PostDto(post);
            postDtoList.add(postDto);
        });
        seriesPostDomain.setPosts(postDtoList);
        return seriesPostDomain;
    }

    @Override
    public Page<SeriesPost> findAllByOrderByCreatedDateDescPageable(Pageable pageable) {
        return seriesPostRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    @Override
    public EditSeriesResponse edit(EditSeriesRequest request) {
        return null;
    }
}

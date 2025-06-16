package unicorns.backend.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unicorns.backend.exception.ApiException;
import unicorns.backend.exception.BadRequestException;
import unicorns.backend.entity.Post;
import unicorns.backend.entity.User;
import unicorns.backend.dto.PostDto;
import unicorns.backend.repository.PostRepository;
import unicorns.backend.security.SecurityUtil;
import unicorns.backend.security.service.CustomUserDetails;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Kim Keumtae
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    public PostDto findDetailForId(Long id) {
        return postRepository.findPostDetailById(id);
    }

    public PostDto registerPost(PostDto postDto, CustomUserDetails customUserDetails) {
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setBody(postDto.getBody());
        newPost.setCreatedBy(customUserDetails.getName());
        newPost.setCreatedDate(LocalDateTime.now());
        newPost.setUser(new User(customUserDetails.getId())); // temporary code
        newPost.setSeriesPostId(postDto.getSeriesPostId());
        return new PostDto(postRepository.saveAndFlush(newPost));
    }

    public PostDto editPost(PostDto editPostDto) {
        Post p = this.findForId(editPostDto.getId())
                .orElseThrow(() -> new ApiException("Post not found"));
        if (p.getUser().getId() != SecurityUtil.getCurrentUserLogin().get().getId()) {
            throw new BadRequestException("It's not a writer.");
        }
        p.setTitle(editPostDto.getTitle());
        p.setBody(editPostDto.getBody());
        p.setSeriesPostId(editPostDto.getSeriesPostId());
        postRepository.save(p);
        PostDto postDto = new PostDto();
        BeanUtils.copyProperties(p, postDto);
        return postDto;
    }

    public Page<Post> findByUserOrderedByCreatedDatePageable(User user, Pageable pageable) {
        return postRepository.findByUserOrderByCreatedDateDesc(user, pageable);
    }

    public Page<Post> findAllByOrderByCreatedDateDescPageable(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    public Page<PostDto> findDetailAllByOrderByCreatedDateDescPageable(Pageable pageable) {
        return postRepository.findDetailAllByOrderByCreatedDateDesc(pageable);
    }

    public void deletePost(Long id) {
        postRepository.findById(id).ifPresent(p -> {
            if (p.getUser().getId() != SecurityUtil.getCurrentUserLogin().get().getId()) {
                throw new BadRequestException("It's not a writer.");
            }
            postRepository.delete(p);
        });
    }
}

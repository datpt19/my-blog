package unicorns.backend.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicorns.backend.dto.PostDto;
import unicorns.backend.entity.Post;
import unicorns.backend.exception.ApiException;
import unicorns.backend.security.CurrentUser;
import unicorns.backend.security.service.CustomUserDetails;
import unicorns.backend.service.impl.PostService;

import java.util.List;
import java.util.Optional;


/**
 * @author Kim Keumtae
 */
@RestController
@RequestMapping("/api")
@Log4j2
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/posts/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        PostDto postDto = postService.findDetailForId(id);
        if (postDto == null) {
            throw new ApiException("Post does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<List<PostDto>> getPostList(Pageable pageable) {
        log.debug("REST request to get Posts : {}", pageable);
        Page<PostDto> posts = postService.findDetailAllByOrderByCreatedDateDescPageable(pageable);
        return new ResponseEntity<>(posts.getContent(), HttpStatus.OK);
    }

    @PostMapping(value = "/posts")
    public ResponseEntity<PostDto> registerPost(@RequestBody PostDto postDto,
                                                @CurrentUser CustomUserDetails customUserDetails) {
        log.debug("REST request to save Post : {}", postDto);
        if (postDto.getId() != null) {
            throw new ApiException("A new post cannot already have an ID", HttpStatus.CONFLICT);
        } else {
            PostDto returnPost = postService.registerPost(postDto, customUserDetails);
            return new ResponseEntity<PostDto>(returnPost, HttpStatus.CREATED);
        }
    }

    @PutMapping(value = "/posts/{id}")
    public ResponseEntity<PostDto> editPost(@PathVariable Long id,
                                            @RequestBody PostDto postDto) {
        log.debug("REST request to edit Post : {}", postDto);
        Optional<Post> post = postService.findForId(id);
        if (!post.isPresent()) {
            throw new ApiException("Post could not be found", HttpStatus.NOT_FOUND);
        }
        PostDto returnPost = postService.editPost(postDto);
        return new ResponseEntity<>(returnPost, HttpStatus.OK);
    }

    @DeleteMapping(value = "/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post id : {}", id);
        if (id == null) {
            throw new ApiException("Post id cannot null", HttpStatus.NOT_FOUND);
        } else {
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

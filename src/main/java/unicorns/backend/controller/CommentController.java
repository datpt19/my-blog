package unicorns.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicorns.backend.entity.Comment;
import unicorns.backend.dto.CommentDto;
import unicorns.backend.security.CurrentUser;
import unicorns.backend.security.service.CustomUserDetails;
import unicorns.backend.service.impl.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Kim Keumtae
 */
@RestController
@RequestMapping("/api")
public class CommentController {

    private final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/comments/posts/{postId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long postId) {
        log.debug("REST request to getComments : {}", postId);

        Optional<List<Comment>> comments = commentService.findCommentsByPostId(postId);
        if (!comments.isPresent()) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<List<CommentDto>>(comments
                    .get()
                    .stream()
                    .map(comment -> new CommentDto(comment))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/comments/posts/{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto,
                                                  @CurrentUser CustomUserDetails customUserDetails) {
        log.debug("REST request to saveComment : {}", commentDto.getUserName());
        CommentDto returnComment = commentService.registerComment(commentDto, customUserDetails);
        return new ResponseEntity<CommentDto>(returnComment, HttpStatus.CREATED);
    }
}

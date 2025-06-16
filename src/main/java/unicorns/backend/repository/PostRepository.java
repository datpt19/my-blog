package unicorns.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unicorns.backend.dto.PostDto;
import unicorns.backend.entity.Post;
import unicorns.backend.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Kim Keumtae
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUserOrderByCreatedDateDesc(User user, Pageable pageable);

    Page<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Optional<Post> findById(Long id);

    void delete(Post post);

    List<Post> findBySeriesPostId(Long id);

    @Query(value = "select new unicorns.backend.dto.PostDto(p, sp.title) from Post p join SeriesPost sp on sp.id = p.seriesPostId where p.id = :id")
    PostDto findPostDetailById(Long id);

    @Query(value = "select new unicorns.backend.dto.PostDto(p, sp.title) from Post p join SeriesPost sp on sp.id = p.seriesPostId order by p.createdDate DESC ")
    Page<PostDto> findDetailAllByOrderByCreatedDateDesc(Pageable pageable);


}
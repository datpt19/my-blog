package unicorns.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicorns.backend.entity.SeriesPost;

@Repository
public interface SeriesPostRepository extends JpaRepository<SeriesPost, Long> {

    Page<SeriesPost> findAllByOrderByCreatedDateDesc(Pageable pageable);

}

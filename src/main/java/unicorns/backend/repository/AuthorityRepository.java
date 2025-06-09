package unicorns.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicorns.backend.entity.Authority;

/**
 * @author Kim Keumtae
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
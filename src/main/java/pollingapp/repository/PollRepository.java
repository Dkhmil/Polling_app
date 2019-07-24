package pollingapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pollingapp.model.Poll;

import java.util.List;
import java.util.Optional;

interface PollRepository extends JpaRepository<Poll, Long> {
     Optional<Poll> findById(Long id);

    Page<Poll> findByCreatedBy(Long userId, Pageable pageable);

    long countByCreateBy(Long userId);

    List<Poll> findByIdIn(List<Long> pollIds);

    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}

package com.habito.platform.repository;

import com.habito.platform.domain.Habit;
import com.habito.platform.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findAllByUserId(@Param("userId") Long userId);

    @Query("select h from Habit h left join fetch h.checks where h.id = :id and h.user.id = :userId")
    Optional<Habit> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    @Query("select h from Habit h left join fetch h.checks c where c.id = :checkId and h.user.id = :userId")
    Optional<Habit> findByCheckIdAndUserId(@Param("checkId") Long checkId, @Param("userId") Long userId);
}

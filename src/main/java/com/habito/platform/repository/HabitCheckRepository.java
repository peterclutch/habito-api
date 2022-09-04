package com.habito.platform.repository;

import com.habito.platform.domain.HabitCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitCheckRepository extends JpaRepository<HabitCheck, Long> {

    @Query("select hc from HabitCheck hc where hc.habit.user.id = :userId")
    List<HabitCheck> findAllByUserId(@Param("userId") Long userId);

    @Query("select hc from HabitCheck hc where hc.id = :id and hc.habit.user.id = :userId")
    Optional<HabitCheck> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

}

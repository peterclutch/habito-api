package com.habito.platform.service;

import com.habito.platform.domain.HabitCheck;
import com.habito.platform.repository.HabitCheckRepository;
import com.habito.platform.security.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class HabitCheckService {

    private final HabitCheckRepository habitCheckRepository;

    public Optional<HabitCheck> findById(Long id) {
        var userId = SecurityUtils.getCurrentUserIdWhenAuthorized();
        return habitCheckRepository.findByIdAndUserId(id, userId);
    }

    public List<HabitCheck> findAll() {
        var userId = SecurityUtils.getCurrentUserIdWhenAuthorized();
        return habitCheckRepository.findAllByUserId(userId);
    }

}

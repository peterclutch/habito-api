package com.habito.platform.service;

import com.habito.platform.domain.Habit;
import com.habito.platform.domain.User;
import com.habito.platform.exception.NotFoundException;
import com.habito.platform.repository.HabitRepository;
import com.habito.platform.security.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;

    public List<Habit> findAll() {
        var userId = SecurityUtils.getCurrentUserIdWhenAuthorized();
        return habitRepository.findAllByUserId(userId);
    }

    public Habit create(@NonNull final Habit habitToSave) {
        // TODO auditorProvider?
        habitToSave.setUser(new User().id(SecurityUtils.getCurrentUserIdWhenAuthorized()));
        return habitRepository.save(habitToSave);
    }

    public void delete(final long id) {
        Habit habitToDelete = habitRepository.findById(id).orElseThrow(() -> new NotFoundException(Habit.class, id));
        habitRepository.delete(habitToDelete);
    }
}

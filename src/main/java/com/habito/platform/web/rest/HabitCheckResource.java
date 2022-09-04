package com.habito.platform.web.rest;

import com.habito.platform.domain.Habit;
import com.habito.platform.domain.HabitCheck;
import com.habito.platform.service.HabitCheckService;
import com.habito.platform.service.HabitService;
import com.habito.platform.service.dto.CheckedHabitViewDto;
import com.habito.platform.service.dto.HabitCheckDto;
import com.habito.platform.service.dto.HabitViewDto;
import com.habito.platform.service.mapper.HabitCheckMapper;
import com.habito.platform.service.mapper.HabitMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.SortedSet;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HabitCheckResource {

    private final HabitCheckMapper habitCheckMapper;
    private final HabitMapper habitMapper;
    private final HabitService habitService;
    private final HabitCheckService habitCheckService;

    @GetMapping("habits/check")
    public ResponseEntity<SortedSet<CheckedHabitViewDto>> getAll() {
        var result = habitCheckMapper.toCheckedViewDto(habitCheckService.findAll());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/habits/check")
    public ResponseEntity<HabitViewDto> create(@Valid @RequestBody HabitCheckDto dto) throws URISyntaxException {
        Habit habit = habitService.findById(dto.getHabitId()).orElseThrow(RuntimeException::new);
        habit.addCheck(habitCheckMapper.toEntity(dto));
        habit = habitService.save(habit);

        return ResponseEntity
            .created(new URI(String.format("/api/habits/%s/check", habit.getId())))
            .body(habitMapper.toViewDto(habit));
    }

    @DeleteMapping("/habits/check/{id}")
    public ResponseEntity<HabitViewDto> delete(@PathVariable Long id) {
        HabitCheck habitCheck = habitCheckService.findById(id).orElseThrow(RuntimeException::new);
        Habit habit = habitCheck.getHabit();
        habit.removeCheck(habitCheck);
        habitService.save(habit);

        return ResponseEntity.noContent().build();
    }
}

package com.habito.platform.service.mapper;

import com.habito.platform.domain.HabitCheck;
import com.habito.platform.service.dto.CheckedHabitViewDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class HabitCheckMapperDecorator implements HabitCheckMapper {

    @Autowired HabitMapper habitMapper;

    @Override
    public SortedSet<CheckedHabitViewDto> toCheckedViewDto(List<HabitCheck> entities) {
        Map<LocalDate, List<HabitCheck>> map = entities.stream().collect(Collectors.groupingBy(HabitCheck::getDate));
        return map.entrySet()
                .stream()
                .map(this::createCheckedHabitViewDto)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private CheckedHabitViewDto createCheckedHabitViewDto(Map.Entry<LocalDate, List<HabitCheck>> entry) {
        return new CheckedHabitViewDto()
                .date(entry.getKey())
                .checks(habitMapper.toDto(entry.getValue()
                        .stream()
                        .map(HabitCheck::getHabit)
                        .collect(Collectors.toList()))
                );
    }
}

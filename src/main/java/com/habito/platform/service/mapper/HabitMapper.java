package com.habito.platform.service.mapper;

import com.habito.platform.domain.Habit;
import com.habito.platform.service.dto.CheckedHabitViewDto;
import com.habito.platform.service.dto.HabitSimpleDto;
import com.habito.platform.service.dto.HabitViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.SortedSet;

@Mapper(
    componentModel = "spring",
    uses = { HabitCheckMapper.class }
)
public interface HabitMapper extends AbstractEntityMapper<HabitSimpleDto, Habit> {

    HabitViewDto toViewDto(Habit entity);

    List<HabitViewDto> toViewDto(List<Habit> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Habit entity, HabitSimpleDto dto);

}

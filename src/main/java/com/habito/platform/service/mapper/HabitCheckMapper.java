package com.habito.platform.service.mapper;

import com.habito.platform.domain.HabitCheck;
import com.habito.platform.service.dto.CheckedHabitViewDto;
import com.habito.platform.service.dto.HabitCheckDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.SortedSet;

@Mapper(componentModel = "spring")
@DecoratedWith(HabitCheckMapperDecorator.class)
public interface HabitCheckMapper extends AbstractEntityMapper<HabitCheckDto, HabitCheck> {

    SortedSet<CheckedHabitViewDto> toCheckedViewDto(List<HabitCheck> entities);

}

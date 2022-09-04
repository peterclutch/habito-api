package com.habito.platform.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CheckedHabitViewDto implements Comparable<CheckedHabitViewDto> {

    @NotNull
    private LocalDate date;

    @NotNull
    private List<HabitSimpleDto> checks = new ArrayList<>();

    @Override
    public int compareTo(CheckedHabitViewDto o) {
        return date.compareTo(o.date) * -1;
    }

    public CheckedHabitViewDto date(LocalDate date) {
        this.date = date;
        return this;
    }

    public CheckedHabitViewDto checks(List<HabitSimpleDto> checks) {
        this.checks = checks;
        return this;
    }
}

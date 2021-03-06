package com.gruzini.tennistico.models.dto.matchDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedMatchDto {

    private MatchStatus matchStatus;

    private Long courtId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private LocalTime start;

    private LocalTime end;

    @AssertTrue(message = "Match has to start before it ends!")
    @JsonIgnore
    public boolean isStartAndEndingTimeValid() {
        if (start == null || end == null) {
            return false;
        }
        return start.isBefore(end);
    }

    @AssertTrue(message = "Match has to take place in the future")
    public boolean isDateTimeValid() {
        if (date == null || start == null) {
            return false;
        }
        return LocalDateTime.of(date, start).isAfter(LocalDateTime.now());
    }
}

package com.gruzini.tennistico.models.dto.matchDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FutureMatchDto {
    private String opponentName;
    private Long opponentId;
    private String court;
    private LocalDateTime start;
    private LocalDateTime end;
    private String matchStatus;
}

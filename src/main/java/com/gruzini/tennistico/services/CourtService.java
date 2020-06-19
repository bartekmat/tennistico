package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Court;
import com.gruzini.tennistico.exceptions.CourtNotFoundException;
import com.gruzini.tennistico.mappers.CourtMapper;
import com.gruzini.tennistico.models.dto.CourtDto;
import com.gruzini.tennistico.repositories.CourtRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@Transactional
@Slf4j
public class CourtService {
    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;

    public CourtService(CourtRepository courtRepository, CourtMapper courtMapper) {
        this.courtRepository = courtRepository;
        this.courtMapper = courtMapper;
    }
    public List<CourtDto> getAll(){
        return courtRepository.findAll().stream()
                .map(courtMapper::toCourtInfoDto)
                .collect(Collectors.toList());
    }

    public Court getById(Long courtId) {
        return courtRepository.findById(courtId).orElseThrow(CourtNotFoundException::new);
    }

    public List<String> getCities() {
        return courtRepository.getCities();
    }

    public List<CourtDto> getByCity(final String city) {
        if (isNull(city)) throw new CourtNotFoundException();
        return courtRepository.getByCity(city).stream()
                .map(courtMapper::toCourtInfoDto)
                .collect(Collectors.toList());
    }
}

package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.events.CreateMatchEvent;
import com.gruzini.tennistico.mappers.CreatedMatchMapper;
import com.gruzini.tennistico.models.dto.CreatedMatchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MatchCreationService {

    private final CreatedMatchMapper createdMatchMapper;
    private final MatchService matchService;
    private final PlayerService playerService;
    private final UserService userService;

    public MatchCreationService(CreatedMatchMapper createdMatchMapper, MatchService matchService, PlayerService playerService, UserService userService) {
        this.createdMatchMapper = createdMatchMapper;
        this.matchService = matchService;
        this.playerService = playerService;
        this.userService = userService;
    }

    @EventListener
    public void handleMatchCreation(final CreateMatchEvent event) {
        create(event.getMatchDto(), event.getUsername());
    }

    public Match create(final CreatedMatchDto createdMatchDto, final String username) {
        final Player player = userService.getByEmail(username).getPlayer();
        final Match createdMatch = saveCreatedMatch(createdMatchDto);
        playerService.add(player, createdMatch);
        return createdMatch;
    }

    private Match saveCreatedMatch(final CreatedMatchDto createdMatchDto) {
        final Match createdMatch = createdMatchMapper.toMatch(createdMatchDto);
        return matchService.save(createdMatch);
    }
}

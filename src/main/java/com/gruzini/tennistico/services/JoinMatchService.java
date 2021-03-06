package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.MatchStatus;
import com.gruzini.tennistico.events.JoinMatchEvent;
import com.gruzini.tennistico.exceptions.WrongMatchStatusException;
import com.gruzini.tennistico.services.entity_related.MatchService;
import com.gruzini.tennistico.services.entity_related.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JoinMatchService {

    private final MatchService matchService;
    private final PlayerService playerService;

    public JoinMatchService(MatchService matchService, PlayerService playerService) {
        this.matchService = matchService;
        this.playerService = playerService;
    }

    @EventListener
    public void handleJoinMatchEvent(final JoinMatchEvent event) {
        joinGuestToMatch(event.getUsername(), event.getMatchId());
    }

    public void joinGuestToMatch(final String guestUsername, final Long matchId) {
        final Match foundMatch = matchService.getById(matchId);
        final Match matchToJoin = changeMatchStatus(foundMatch);
        final Player guest = playerService.getByUsername(guestUsername);
        matchToJoin.setGuest(guest);
        matchService.save(matchToJoin);
    }

    private synchronized Match changeMatchStatus(final Match match) {
        validateMatchStatus(match);
        match.setMatchStatus(MatchStatus.JOIN_REQUEST);
        matchService.save(match);
        return match;
    }

    private void validateMatchStatus(final Match match) {
        final MatchStatus matchStatus = match.getMatchStatus();
        if (!matchStatus.equals(MatchStatus.HOSTED)) {
            throw new WrongMatchStatusException();
        }
    }
}

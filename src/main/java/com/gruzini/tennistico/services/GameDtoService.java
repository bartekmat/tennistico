package com.gruzini.tennistico.services;

import com.gruzini.tennistico.domain.Game;
import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.GameStatus;
import com.gruzini.tennistico.mappers.ArchivedGameMapper;
import com.gruzini.tennistico.mappers.HostedGameMapper;
import com.gruzini.tennistico.models.dto.ArchivedGameDto;
import com.gruzini.tennistico.models.dto.HostedGameDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameDtoService {

    private final GameService gameService;
    private final HostedGameMapper hostedGameMapper;
    private final ArchivedGameMapper archivedGameMapper;

    public GameDtoService(GameService gameService, HostedGameMapper hostedGameMapper, ArchivedGameMapper archivedGameMapper) {
        this.gameService = gameService;
        this.hostedGameMapper = hostedGameMapper;
        this.archivedGameMapper = archivedGameMapper;
    }

    public List<HostedGameDto> getHostedGameDtoExceptHostedBy(final Player player) {
        final List<Game> hostedNotByPlayer = gameService.getHostedGamesExceptHostedBy(player);
        return hostedNotByPlayer.stream()
                .map(hostedGameMapper::toGameInfoDto)
                .collect(Collectors.toList());
    }

    public List<ArchivedGameDto> getArchiveGameDtoBy(final Player player) {
        return gameService.getByPlayerAndStatus(player, GameStatus.ARCHIVED).stream()
                .map(game -> archivedGameMapper.toArchivedGameDTO(game, player))
                .collect(Collectors.toList());
    }
}
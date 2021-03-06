package com.gruzini.tennistico.services.entity_related;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.exceptions.PlayerNotFoundException;
import com.gruzini.tennistico.repositories.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getByUsername(final String username) {
        return playerRepository.findByUsername(username).orElseThrow(PlayerNotFoundException::new);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Page<Player> getPlayersPage(final Pageable pageable){
        return playerRepository.findAllByOrderByRankingPointsDesc(pageable);
    }

    public Player getById(final Long id) {
        return playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    }
}
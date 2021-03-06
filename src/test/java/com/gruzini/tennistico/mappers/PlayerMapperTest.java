package com.gruzini.tennistico.mappers;

import com.gruzini.tennistico.domain.Player;
import com.gruzini.tennistico.domain.enums.Gender;
import com.gruzini.tennistico.domain.enums.PlayerSkill;
import com.gruzini.tennistico.models.forms.PlayerRegistrationForm;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerMapperTest {
    private PlayerMapper playerMapper;

    @Test
    void shouldMapPlayerRegistrationFormToPlayer() {
        //given
        final PlayerRegistrationForm playerRegistrationForm =
                new PlayerRegistrationForm("name", "surname", Gender.MALE.toString(), LocalDate.of(2000, 1, 1), 1, PlayerSkill.BEGINNER.toString());
        //when
        final Player player = playerMapper.toPlayer(playerRegistrationForm);
        //then
        assertAll(() -> {
            assertEquals(playerRegistrationForm.getFirstName(), player.getFirstName());
            assertEquals(playerRegistrationForm.getLastName(), player.getLastName());
            assertEquals(playerRegistrationForm.getGender(), player.getGender());
            assertEquals(playerRegistrationForm.getPlayerSkill(), player.getPlayerSkill());
            assertEquals(playerRegistrationForm.getYearsOfExperience(), player.getYearsOfExperience());
        });
    }

    @Test
    void shouldThrowWhenCannotParseDate() {
        //given
        //when
        //then
    }
}
package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.events.InputScoreEvent;
import com.gruzini.tennistico.models.dto.ScoreDto;
import com.gruzini.tennistico.services.entity_related.MatchService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping("/input-score")
public class InputScoreController {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final MatchService matchService;

    public InputScoreController(ApplicationEventPublisher applicationEventPublisher, MatchService matchService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.matchService = matchService;
    }

    @PostMapping("/begin")
    public String beginSubmittingScore(@RequestParam(name = "match_id") final Long matchId,
                                       @RequestParam(name = "trigger_id") final Long triggerNotificationId,
                                       final Model model) {
        model.addAttribute("match", matchService.getById(matchId));
        model.addAttribute("trigger_id", triggerNotificationId);
        model.addAttribute("score", new ScoreDto());
        return "score";
    }

    @PostMapping("/process")
    public String processSubmittedScore(@ModelAttribute(name = "match_id") final Long matchId,
                                        @ModelAttribute(name = "trigger_id") final Long triggerNotificationId,
                                        @Valid @ModelAttribute(name = "score") final ScoreDto scoreDto,
                                        final Errors errors,
                                        final Model model,
                                        final Principal principal) {
        if (errors.hasErrors()) {
            model.addAttribute("match", matchService.getById(matchId));
            model.addAttribute("score", new ScoreDto());
            model.addAttribute("msg", "Invalid score input");
            return "score";
        }
        applicationEventPublisher.publishEvent(new InputScoreEvent(this, matchId, principal.getName(), scoreDto, triggerNotificationId));
        return "redirect:/dashboard";
    }
}

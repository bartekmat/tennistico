package com.gruzini.tennistico.controllers;

import com.gruzini.tennistico.domain.Match;
import com.gruzini.tennistico.services.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/submit-score")
public class SubmitScoreController {

   private final MatchService matchService;

   public SubmitScoreController(final MatchService matchService) {
      this.matchService = matchService;
   }

   @GetMapping("/{match_id}")
   public String showSubmitScorePage(@PathVariable(name = "match_id")final Long matchId,
                                     final Model model){
      final Match match = matchService.getById(matchId);
      model.addAttribute("match", match);
      return "score";
   }
}
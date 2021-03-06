package com.gruzini.tennistico.events;

import com.gruzini.tennistico.domain.enums.MatchStatus;
import org.springframework.context.ApplicationEvent;

public class ChangeMatchStatusByEndingDateTimeEvent extends ApplicationEvent {
   private final MatchStatus currentMatchStatus;
   private final MatchStatus desiredMatchStatus;

   public ChangeMatchStatusByEndingDateTimeEvent(final Object source,
                                                 final MatchStatus currentMatchStatus,
                                                 final MatchStatus desiredMatchStatus) {
      super(source);
      this.currentMatchStatus = currentMatchStatus;
      this.desiredMatchStatus = desiredMatchStatus;
   }

   public MatchStatus getCurrentMatchStatus() {
      return currentMatchStatus;
   }

   public MatchStatus getDesiredMatchStatus() {
      return desiredMatchStatus;
   }
}

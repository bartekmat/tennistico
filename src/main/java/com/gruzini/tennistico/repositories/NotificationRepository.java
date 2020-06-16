package com.gruzini.tennistico.repositories;

import com.gruzini.tennistico.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM  notifications n WHERE n.recipient = :username")
    List<Notification> findAllByRecipientContaining(@Param("username") final String username);
}

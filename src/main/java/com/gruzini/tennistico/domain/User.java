package com.gruzini.tennistico.domain;

import com.gruzini.tennistico.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
   @Id
   @GeneratedValue
   private Long id;

   @NotNull
   @Email
   private String email;

   @NotNull
   private String password;

   private LocalDateTime createdAt;

   @Enumerated(EnumType.STRING)
   private UserStatus userStatus;
}

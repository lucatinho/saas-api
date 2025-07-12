package com.example.saas_app.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Role> roles;
}

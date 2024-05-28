package com.fx.javafx.model;

import com.fx.javafx.ui.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "naudotojai")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Roles role;

    @OneToMany(mappedBy = "userModel", fetch = FetchType.EAGER)
    Set<CheckedOutModel> books = new HashSet<>();


}

package com.ams.amsbackend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "users")
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    private String loginId;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String loginId, String name, String email, String password, Role role) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Transient
    public String getDiscriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}

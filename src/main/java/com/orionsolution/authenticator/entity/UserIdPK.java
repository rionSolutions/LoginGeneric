package com.orionsolution.authenticator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserIdPK {

    private static final long serialVersionUID = 1;

    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;
}

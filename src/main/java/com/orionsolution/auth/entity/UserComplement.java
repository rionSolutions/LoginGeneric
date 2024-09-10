package com.orionsolution.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "compl_users", schema = "register")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class UserComplement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    @NotNull
    private Long id_user;
    private String compl_phone;
    private String compl_adress;

    private String compl_cpf;
    private String compl_cep;
    private String compl_city;
    private String user_plan;


    public UserComplement(UserComplement complement){
        this.id_user = complement.getId_user();
        this.compl_phone= complement.getCompl_phone();
        this.compl_adress= complement.getCompl_adress();
        this.compl_cep= complement.getCompl_cep();
        this.compl_city= complement.getCompl_city();
        this.user_plan = complement.getUser_plan();
        this.compl_cpf = complement.getCompl_cpf();
    }

}

package com.orionsolution.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode
@ToString
//TODO: Change the schema to the correct one
@Table(name = "TB_APPLICATION", schema = "REGISTER")
public class ApplicationEntity {

    @Id
    @Column(name = "APPLICATION_ID")
    private String applicationId;

    @Column(name = "APPLICATION_NAME")
    private String applicationName;

}

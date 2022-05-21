package com.example.bankingsystem.model.entity;

import com.example.bankingsystem.model.entity.base.BaseModel;
import com.example.bankingsystem.model.entity.enums.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 21.05.2022
 */

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseModel {

    @NotNull
    private String mail;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Customer customer;
}

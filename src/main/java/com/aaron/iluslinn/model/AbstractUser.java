package com.aaron.iluslinn.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;

@MappedSuperclass
@Data
public class AbstractUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    protected List<Role> roles;

    public AbstractUser() {
    }

    public AbstractUser(String userName, List<Role> roles) {
        this.username = userName;
        this.roles = roles;
    }

}

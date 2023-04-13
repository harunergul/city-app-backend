package com.aaron.iluslinn.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "appuser")
public class PartialUser extends AbstractUser {

    public PartialUser() {
        super(null, null);
    }

    public PartialUser(String userName, List<Role> roles) {
        super(userName, roles);

    }

}

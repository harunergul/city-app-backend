package com.aaron.iluslinn.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Organization")
@Data
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer organizationId;

    public String name;

    public String website;

    public Boolean active;

}

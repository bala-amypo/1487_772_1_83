package com.example.demo.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vendor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true)
private String name;

private String contactEmail;
private String contactPhone;
private Boolean active;
private Timestamp createdAt;
private Timestamp updatedAt;

public Vendor() {
this.active = true;
this.createdAt = new Timestamp(System.currentTimeMillis());
this.updatedAt = new Timestamp(System.currentTimeMillis());
}

public Vendor(Long id, String name, String contactEmail, String contactPhone,
Boolean active, Timestamp createdAt, Timestamp updatedAt) {
this.id = id;
this.name = name;
this.contactEmail = contactEmail;
this.contactPhone = contactPhone;
this.active = active;
this.createdAt = createdAt;
this.updatedAt = updatedAt;
}

public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}

public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}

public String getContactEmail() {
return contactEmail;
}
public void setContactEmail(String contactEmail) {
this.contactEmail = contactEmail;
}

public String getContactPhone() {
return contactPhone;
}
public void setContactPhone(String contactPhone) {
this.contactPhone = contactPhone;
}

public Boolean getActive() {
return active;
}
public void setActive(Boolean active) {
this.active = active;
}

public Timestamp getCreatedAt() {
return createdAt;
}
public void setCreatedAt(Timestamp createdAt) {
this.createdAt = createdAt;
}

public Timestamp getUpdatedAt() {
return updatedAt;
}
public void setUpdatedAt(Timestamp updatedAt) {
this.updatedAt = updatedAt;
}
}
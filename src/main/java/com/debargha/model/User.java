package com.debargha.model;

import javax.persistence.*;

import com.debargha.model.UserDto;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;
    @Column
    private String firstName;
    @Column
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sex sex;
    @Column
    private String contactNo;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Preference preference;
    @OneToMany(mappedBy = "user")
    private final Set<Purchase> purchases = new LinkedHashSet<>();

    public User() {
    }


    public User(UserDto dto)
    {
        email = dto.getEmail();
        password = dto.getPassword();
        sex = dto.getSex();
        firstName = dto.getFirstName();
        lastName = dto.getLastName();
        contactNo = dto.getContactNo();
        preference = dto.getPreference();
    }

    public Preference getPreference() {
        return preference;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", contactNo='" + contactNo + '\'' +
                ", password='" + password + '\'' +
                ", preference=" + preference +
                '}';
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
    public boolean hasRole(String roleName) {
		if (this.role.getName().equals(roleName))
			return true;
		return false;
	}
	
}
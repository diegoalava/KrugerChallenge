package com.example.kruger.model;

import com.google.common.annotations.VisibleForTesting;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Diego.Alava
 */
@Entity(name="employee")
@Table(name = "employee")
public class Employee {

    //Info for a new employee
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "dni", length = 10, nullable = false, unique = true)
    private Long dni;

    @Column(name = "names", nullable = false)
    private String names;

    @Column(name = "lastNames", nullable = false)
    private String lastNames;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    //Info available for updates
    private Date birthDate;
    private String homeAddress;
    private String phoneNumber;
    @Column(name = "vaccination_status", length = 1)
    private int vaccinationStatus;

    public Employee() {
    }

    public Employee(Long userId, Long dni, String names, String lastNames, String email, Date birthDate, String homeAddress, String phoneNumber, int vaccinationStatus) {
        this.userId = userId;
        this.dni = dni;
        this.names = names;
        this.lastNames = lastNames;
        this.email = email;
        this.birthDate = birthDate;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.vaccinationStatus = vaccinationStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(int vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }
}

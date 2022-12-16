package com.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.Employment;

/**
 * Класс (сущность) "Author", наследуется от класса "BaseEntity"
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "author")
public class Author extends BaseEntity {

    /**
     * Поле "firstName" - имя автора
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Поле "lastName" - фамилия автора
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Поле "middleName" - отчество автора
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Поле "address" - адрес автора
     */
    @Column(name = "address")
    private String address;

    /**
     * Поле "snils" - СНИЛС автора
     */
    @Column(name = "snils")
    private String snils;

    /**
     * Поле "mobilePhone" - номер телефона автора (мобильный)
     */
    @Column(name = "mobile_phone")
    private int mobilePhone;

    /**
     * Поле "email" - адрес электронной почты автора
     */
    @Column(name = "email")
    private String email;

    /**
     * Поле "employment" - трудоустройство автора (Безработный, Работник, Учащийся)
     */
    @Column(name = "employment")
    @Enumerated(EnumType.STRING)
    private Employment employment;

    /**
     * Поле "fioDative" - ФИО автора в дательном падеже
     */
    @Column(name = "fio_dative")
    private String fioDative;

    /**
     * Поле "fioGenitive" - ФИО автора в родительном падеже
     */
    @Column(name = "fio_genitive")
    private String fioGenitive;

    /**
     * Поле "fioNominative" - ФИО автора в именительном падеже
     */
    @Column(name = "fio_nominative")
    private String fioNominative;

}
package ru.moex.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Сущность клиент
 */
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Идентификатор клиента в банке
     */
    private Long bankId;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Имя
     */
    private String firstName;
    /**
     * Отчество
     */
    private String thirdName;
    /**
     * Дата рождения
     */
    private Date birthDate;

    /**
     * Номер паспорта (вместе с серией в формате ХХХХ ХХХХХХ)
     */
    private String passport;

    /**
     * Место рождения
     */
    private String birthPlace;

    /**
     * Телефон (в формате 7ХХХХХХХХХХ)
     */
    private String phone;

    /**
     * Email
     */
    private String email;

    /**
     * Адрес регистрации
     */
    private String regAddress;

    /**
     * Адрес проживания
     */
    private String liveAddress;

}

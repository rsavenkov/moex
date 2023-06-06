package ru.moex.test.dto;

import java.util.Date;

/**
 * Dto for create method
 */
public class ClientDTO {

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

    public Long getBankId() {
        return bankId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPassport() {
        return passport;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public String getLiveAddress() {
        return liveAddress;
    }
}

package ru.moex.test.validators;

/**
 * Enum field validator
 */
public enum Validator {

    MAIL(MailValidator.class),
    MOBILE(MobileValidator.class),
    BANK(BankValidator.class),
    GOSUSLUGI(GosuslugiValidator.class);

    private Class clazz;
    Validator(Class clazz) {
        this.clazz = clazz;
    }
}

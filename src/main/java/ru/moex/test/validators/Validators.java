package ru.moex.test.validators;

/**
 * Enum with field validators
 */
public enum Validators {

    MAIL(new MailValidator()),
    MOBILE(new MobileValidator()),
    BANK(new BankValidator()),
    GOSUSLUGI(new GosuslugiValidator());

    private Validatable validator;

    <T extends Validatable> Validators(T validator) {
        this.validator = validator;
    }

    public Validatable getValidator() {
        return validator;
    }

    public static Validators getByName(String name) {
        for (Validators v : Validators.values()) {
            if (v.name().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }
}

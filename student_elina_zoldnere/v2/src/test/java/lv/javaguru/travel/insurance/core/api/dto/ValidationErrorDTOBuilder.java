package lv.javaguru.travel.insurance.core.api.dto;

public class ValidationErrorDTOBuilder {
    private String errorCode;
    private String description;

    public static ValidationErrorDTOBuilder createValidationError() {
        return new ValidationErrorDTOBuilder();
    }

    public ValidationErrorDTO build() {
        return new ValidationErrorDTO(
                errorCode,
                description);
    }

    public ValidationErrorDTOBuilder withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ValidationErrorDTOBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

}

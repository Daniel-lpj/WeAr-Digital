package wear.digital.model;

public record RestValidationError(
        Integer code,
        String field,
        String message) {

}

package validatorsDraft;

@FunctionalInterface
public interface InputValidator {
    public boolean validate(String input);
}

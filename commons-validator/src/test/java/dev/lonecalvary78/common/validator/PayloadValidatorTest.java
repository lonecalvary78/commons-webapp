package dev.lonecalvary78.common.validator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import dev.lonecalvary78.test.model.Person;

public class PayloadValidatorTest {
    @ParameterizedTest
    @MethodSource("constructTestingPayloads")
    public void validatePayload(Person person) {
        PayloadValidator<Person> payloadValidator = PayloadValidator.getInstance();
        var errors = payloadValidator.validate(person);
        Assertions.assertAll(()-> assertNotNull(errors),
        ()->assertTrue(!errors.isEmpty()));
    }

    private static Stream<Person> constructTestingPayloads() {
        return Stream.of(Person.of("John", null),
        Person.of(null, "Doe"),
        Person.of(null, null));
    }
}
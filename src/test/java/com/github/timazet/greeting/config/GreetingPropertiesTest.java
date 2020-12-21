package com.github.timazet.greeting.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingPropertiesTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldHaveViolationsByDefault() {
        //given
        GreetingProperties properties = new GreetingProperties();

        //when
        Set<ConstraintViolation<GreetingProperties>> violations = validator.validate(properties);

        //then
        assertThat(violations).hasSize(2)
                .anySatisfy(violation -> {
                    assertThat(violation.getInvalidValue()).isEqualTo(null);
                    assertThat(violation.getPropertyPath()).hasToString("username");
                    assertThat(violation.getMessage()).isEqualTo("must not be blank");
                });
    }

    @Test
    void shouldHaveNoViolationsWithDefaultValues() {
        //given
        GreetingProperties properties = new GreetingProperties();
        properties.setUsername(RandomStringUtils.randomAlphanumeric(5));

        //when
        Set<ConstraintViolation<GreetingProperties>> violations = validator.validate(properties);

        //then
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldHaveViolationsForNull() {
        //given
        GreetingProperties properties = new GreetingProperties();
        properties.setUsername(null);

        //when
        Set<ConstraintViolation<GreetingProperties>> violations = validator.validate(properties);

        //then
        assertThat(violations).hasSize(1)
                .anySatisfy(violation -> {
                    assertThat(violation.getInvalidValue()).isNull();
                    assertThat(violation.getPropertyPath()).hasToString("username");
                    assertThat(violation.getMessage()).isEqualTo("must not be blank");
                });
    }

    @Test
    void shouldHaveViolationsForLowerBoundaryValuesOfInboundProperties() {
        //given
        GreetingProperties properties = new GreetingProperties();
        properties.setUsername(RandomStringUtils.randomAlphanumeric(4));

        //when
        Set<ConstraintViolation<GreetingProperties>> violations = validator.validate(properties);

        //then
        assertThat(violations).hasSize(1)
                .anySatisfy(violation -> {
                    assertThat(violation.getInvalidValue()).isEqualTo(properties.getUsername());
                    assertThat(violation.getPropertyPath()).hasToString("username");
                    assertThat(violation.getMessage()).isEqualTo("size must be between 5 and 100");
                });
    }

    @Test
    void shouldHaveViolationsForUpperBoundaryValuesOfInboundProperties() {
        //given
        GreetingProperties properties = new GreetingProperties();
        properties.setUsername(RandomStringUtils.randomAlphanumeric(101));

        //when
        Set<ConstraintViolation<GreetingProperties>> violations = validator.validate(properties);

        //then
        assertThat(violations).hasSize(1)
                .anySatisfy(violation -> {
                    assertThat(violation.getInvalidValue()).isEqualTo(properties.getUsername());
                    assertThat(violation.getPropertyPath()).hasToString("username");
                    assertThat(violation.getMessage()).isEqualTo("size must be between 5 and 100");
                });
    }

}
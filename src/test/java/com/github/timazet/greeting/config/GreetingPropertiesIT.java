package com.github.timazet.greeting.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.validation.BindValidationException;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingPropertiesIT {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(GreetingPropertiesTestConfiguration.class);

    @Test
    void shouldFailContextLoadingDueToPropertiesViolations() {
        contextRunner.run(context -> assertThat(context).hasFailed().getFailure()
                .hasRootCauseInstanceOf(BindValidationException.class)
                .hasStackTraceContaining("Field error in object 'greeting' on field 'username'"));
    }

    @Test
    void shouldLoadContextWithoutPropertiesViolations() {
        contextRunner.withPropertyValues(
                "greeting.username=" + RandomStringUtils.randomAlphanumeric(10)
        ).run(context -> assertThat(context).hasNotFailed()
                .getBean(GreetingProperties.class).hasNoNullFieldsOrProperties());
    }

    @EnableConfigurationProperties(GreetingProperties.class)
    protected static class GreetingPropertiesTestConfiguration {

    }

}
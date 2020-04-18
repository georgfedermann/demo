package com.oreilly.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SpringApplicationTests {

    private Logger logger = LoggerFactory.getLogger(SpringApplicationTests.class.getName());

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        logger.info(String.format("There are %s beans in the application context.",
                context.getBeanDefinitionCount()));
        Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
        assertAll(
                () -> assertThat(context.getBeanDefinitionNames()).asList().contains("defaultNumberFormat"),
                () -> assertThat(context.getBeanDefinitionNames()).asList().contains("germanNumberFormat"),
                () -> assertTrue(Arrays.asList(context.getBeanDefinitionNames()).contains("defaultNumberFormat"))
        );
    }

    @Test
    void testDefaultNF(@Autowired @Qualifier("defaultNumberFormat") NumberFormat numberFormat) {
        double amount = 1234567.8901234;
        logger.info(numberFormat.format(amount));
    }

    @Test
    void testGermanNF(@Autowired NumberFormat germanNumberFormat) {
        double amount = 1234567.8901234;
        logger.info(germanNumberFormat.format(amount));
    }

}

package com.example.Pessoas.annotation;

import com.example.Pessoas.validation.PessoaValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.*;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PessoaValidator.class)
public @interface PessoaValidation {

    Class<?>[] groups() default {};

    String message() default "Id inválido";

    Class<? extends Payload>[] payload() default {};

}


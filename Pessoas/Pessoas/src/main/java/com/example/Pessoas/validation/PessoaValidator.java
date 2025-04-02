package com.example.Pessoas.validation;

import com.example.Pessoas.annotation.PessoaValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PessoaValidator implements ConstraintValidator< PessoaValidation, Long> {

    @Override
    public void initialize(final PessoaValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long telefone, final ConstraintValidatorContext context) {
        boolean result = false;
        if ( telefone == null || telefone.compareTo(0L) == 0) {
            result = false;
        } else {
            result = telefone.compareTo(10900000000L) >= 0 && telefone.compareTo(100000000000L) <= 0 ? true : false;
        }
        return result;
    }

}

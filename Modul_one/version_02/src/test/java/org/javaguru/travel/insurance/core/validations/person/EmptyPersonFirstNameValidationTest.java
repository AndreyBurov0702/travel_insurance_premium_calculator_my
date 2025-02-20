package org.javaguru.travel.insurance.core.validations.person;

import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.api.dto.ValidationErrorDTO;
import org.javaguru.travel.insurance.core.validations.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmptyPersonFirstNameValidationTest {

    @Mock
    private PersonDTO person;
    @Mock
    private AgreementDTO agreement;
    @Mock
    private ValidationErrorFactory errorFactory;
    @Mock
    private ValidationErrorDTO validationError;
    @InjectMocks
    private EmptyPersonFirstNameValidation validation;
    @Test
    public void shouldReturnErrorWhenFirstNameIsNull() {
        when(person.getPersonFirstName()).thenReturn(null);
        when(errorFactory.buildError("ERROR_CODE_7")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOptional = validation.validate(agreement, person);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }
    @Test
    public void shouldReturnErrorWhenFirstNameIsEmpty() {
        when(person.getPersonFirstName()).thenReturn("");
        when(errorFactory.buildError("ERROR_CODE_7")).thenReturn(validationError);
        Optional<ValidationErrorDTO> errorOptional = validation.validate(agreement, person);
        assertTrue(errorOptional.isPresent());
        assertSame(errorOptional.get(), validationError);
    }
    @Test
    public void shouldReturnNoErrorsWhenFirstNameIsProvided() {
        when(person.getPersonFirstName()).thenReturn("Andrey");
        Optional<ValidationErrorDTO> errorOptional = validation.validate(agreement, person);
        assertTrue(errorOptional.isEmpty());
        verifyNoInteractions(errorFactory);
    }
}

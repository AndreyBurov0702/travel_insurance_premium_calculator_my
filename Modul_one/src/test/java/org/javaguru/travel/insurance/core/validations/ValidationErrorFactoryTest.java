package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {
    @Mock
    private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory factory;

    @Test
    public void shouldReturnValidationErrorWithDescription() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE"))
                .thenReturn("error description");
        ValidationError error = factory.buildError("ERROR_CODE");
        assertEquals(error.errorCode(), "ERROR_CODE");
        assertEquals(error.description(), "error description");
    }
    @Test
    public void shouldReturnValidationErrorWithDescriptionUsingPlaceholder(){
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "AAA");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder))).thenReturn("error AAA description");
        ValidationError error = factory.buildError("ERROR_CODE", List.of(placeholder));
        assertEquals(error.errorCode(), "ERROR_CODE");
        assertEquals(error.description(), "error AAA description");
    }
}

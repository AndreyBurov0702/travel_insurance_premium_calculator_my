package org.javaguru.travel.insurance.core.underwriting.calculators.medical;

import org.javaguru.travel.insurance.core.domain.CountryDefaultDayRate;
import org.javaguru.travel.insurance.core.repositories.CountryDefaultDayRateRepository;
import org.javaguru.travel.insurance.dto.v1.TravelCalculatePremiumRequestV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryDefaultDayRateCalculatorTest {
    @Mock
    private
    CountryDefaultDayRateRepository countryDefaultDayRateRepository;
    @Mock
    private CountryDefaultDayRate countryDefaultDayRate;

    @InjectMocks
    private CountryDefaultDayRateCalculator calculator;

    private TravelCalculatePremiumRequestV1 request;
    @BeforeEach
    void setUp() {
        request = new TravelCalculatePremiumRequestV1();
        request.setCountry("USA");
    }
    @Test
    void shouldCalculateDayRateWhenCountryDayRateExists() {
        BigDecimal expectedDayRate = BigDecimal.valueOf(10.0);
        when(countryDefaultDayRate.getDefaultDayRate()).thenReturn(expectedDayRate);
        when(countryDefaultDayRateRepository.findByCountryIc(request.getCountry()))
                .thenReturn(Optional.of(countryDefaultDayRate));
        BigDecimal result = calculator.calculate(request);
        assertEquals(expectedDayRate, result);
    }
    @Test
    void shouldThrowExceptionWhenCountryDayRateNotFound() {
        when(countryDefaultDayRateRepository.findByCountryIc(request.getCountry())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculate(request));
        assertEquals("Country day rate not found by country id = " + request.getCountry(), exception.getMessage());
    }
}

package org.javaguru.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.javaguru.travel.insurance.dto.util.BigDecimalSerializer;
import org.javaguru.travel.insurance.dto.CoreResponse;
import org.javaguru.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV2 extends CoreResponse {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date personBirthDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date agreementDateTo;

    private String country;

    private String medicalRiskLimitLevel;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal agreementPremium;

    @JsonAlias("persons")
    private List<PersonResponseDTO> persons;

    public TravelCalculatePremiumResponseV2(List<ValidationError> errors) {
        super(errors);
    }
}

package com.balaji.rms.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateCheckValidator implements ConstraintValidator<DateCheck, Object> {

    private String startDate;
    private String endDate;

    public void initialize(DateCheck datecheckAnnotation){
        this.startDate=datecheckAnnotation.startDate();
        this.endDate=datecheckAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object startDateValue = new BeanWrapperImpl(value).getPropertyValue(startDate);
        Object endDateValue = new BeanWrapperImpl(value).getPropertyValue(endDate);

        if(startDateValue != null && endDateValue != null){
            LocalDate localStartDate = LocalDate.parse((CharSequence) startDateValue);
            LocalDate localEndDate = LocalDate.parse((CharSequence) endDateValue);
            return localEndDate.isAfter(localStartDate);
        }

        return true;
    }
}

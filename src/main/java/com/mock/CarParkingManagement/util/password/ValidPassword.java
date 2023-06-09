package com.mock.CarParkingManagement.util.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default "password must contain at least 6 characters, including uppercase, lowercase and number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

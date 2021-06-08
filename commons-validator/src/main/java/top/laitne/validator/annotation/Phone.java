package top.laitne.validator.annotation;

import top.laitne.validator.internal.constraintvalidator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: zhangjl
 * @Date: 21-6-8
 * @Description:
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {PhoneValidator.class})
public @interface Phone {
    boolean allowPhoneCall() default false; // 是否允许座机号

    String message() default "手机号格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

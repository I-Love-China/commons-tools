package top.laitne.validator.internal.constraintvalidator;

import top.laitne.regex.PhoneRegex;
import top.laitne.validator.annotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author: zhangjl
 * @Date: 21-6-8
 * @Description:
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    private boolean isAllowPhoneCall;

    @Override
    public void initialize(Phone constraintAnnotation) {
        this.isAllowPhoneCall = constraintAnnotation.allowPhoneCall();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value || value.length() == 0) {
            return true;
        }

        return
                Pattern.matches(PhoneRegex.PHONE_PATTERN, value)
                        ||
                        (isAllowPhoneCall && Pattern.matches(PhoneRegex.PHONE_CALL_PATTERN, value));
    }
}

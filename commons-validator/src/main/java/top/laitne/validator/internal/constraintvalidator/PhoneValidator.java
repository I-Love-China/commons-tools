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

    private String phonePattern;

    private String phoneCallPattern;

    @Override
    public void initialize(Phone constraintAnnotation) {
        this.isAllowPhoneCall = constraintAnnotation.allowPhoneCall();

        String phonePattern = constraintAnnotation.phonePattern();
        this.phonePattern = 0 == phonePattern.length() ? PhoneRegex.PHONE_PATTERN : phonePattern;

        String phoneCallPattern = constraintAnnotation.phoneCallPattern();
        this.phoneCallPattern = 0 == phoneCallPattern.length() ? PhoneRegex.PHONE_CALL_PATTERN : phoneCallPattern;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value || value.length() == 0) {
            return true;
        }

        return
                Pattern.matches(phonePattern, value)
                        ||
                        (isAllowPhoneCall && Pattern.matches(phoneCallPattern, value));
    }
}

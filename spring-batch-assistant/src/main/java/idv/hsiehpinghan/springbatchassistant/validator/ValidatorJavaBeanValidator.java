package idv.hsiehpinghan.springbatchassistant.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import idv.hsiehpinghan.springbatchassistant.vo.ValidatorJavaBeanVo;

public class ValidatorJavaBeanValidator implements Validator<ValidatorJavaBeanVo> {
	private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private javax.validation.Validator validator = validatorFactory.getValidator();

	@Override
	public void validate(ValidatorJavaBeanVo vo) throws ValidationException {
		Set<ConstraintViolation<ValidatorJavaBeanVo>> violations = validator.validate(vo);
		if (violations.isEmpty() == false) {
			throw new ValidationException(
					"ValidatorJavaBeanValidator validate fail : violations(" + violations + ") !!!");
		}
	}

}

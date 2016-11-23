package idv.hsiehpinghan.springbatchassistant.validator;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

public class ValidatorValidator implements Validator<String> {

	@Override
	public void validate(String value) throws ValidationException {
		if (value.equals("3")) {
			throw new ValidationException("ValidatorValidator value == 3 !!!");
		}
	}

}

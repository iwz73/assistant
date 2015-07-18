package idv.hsiehpinghan.hibernateassistant.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UpperCaseConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return attribute.toUpperCase();
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return dbData;
	}

}

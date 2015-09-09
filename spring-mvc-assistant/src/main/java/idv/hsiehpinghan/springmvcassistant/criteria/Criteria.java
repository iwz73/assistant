package idv.hsiehpinghan.springmvcassistant.criteria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Criteria {
	private Integer integerValue;
	private Float floatValue;
	private String stringValue;
	private Date dateValue;
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private LocalDate localDate;
	@DateTimeFormat(iso = ISO.TIME)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private LocalTime localTime;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private LocalDateTime localDateTime;

	public Integer getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}

	public Float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(Float floatValue) {
		this.floatValue = floatValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public LocalTime getLocalTime() {
		return localTime;
	}

	public void setLocalTime(LocalTime localTime) {
		this.localTime = localTime;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "Criteria [integerValue=" + integerValue + ", floatValue="
				+ floatValue + ", stringValue=" + stringValue + ", dateValue="
				+ dateValue + ", localDate=" + localDate + ", localTime="
				+ localTime + ", localDateTime=" + localDateTime + "]";
	}

}

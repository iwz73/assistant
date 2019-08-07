package idv.hsiehpinghan.commonsbeanutilsassistant.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import idv.hsiehpinghan.commonsbeanutilsassistant.enumeration.Enumeration;

public class BasicTypeExtensionVo extends BasicTypeVo {
	private static final long serialVersionUID = 1L;

	public BasicTypeExtensionVo() {
		super();
	}

	public BasicTypeExtensionVo(boolean primativeBoolean, Boolean wrappedBoolean, byte primativeByte, Byte wrappedByte,
			char primativeChar, Character wrappedChar, double primativeDouble, Double wrappedDouble,
			float primativeFloat, Float wrappedFloat, int primativeInt, Integer wrappedInt, long primativeLong,
			Long wrappedLong, short primativeShort, Short wrappedShort, String string, BigInteger bigInteger,
			BigDecimal bigDecimal, Locale locale, TimeZone timeZone, Currency currency, Class<BasicTypeVo> clazz,
			Serializable serializable, Date date, Calendar calendar, java.sql.Date sqlDate, Time sqlTime,
			Timestamp sqlTimestamp, byte[] byteArray, char[] charArray, Enumeration enumeration,
			SubBasicType subBasicType, List<SubBasicType> subBasicTypeList, Set<SubBasicType> subBasicTypeSet,
			Map<SubBasicType, SubBasicType> subBasicTypeMap) {
		super(primativeBoolean, wrappedBoolean, primativeByte, wrappedByte, primativeChar, wrappedChar, primativeDouble,
				wrappedDouble, primativeFloat, wrappedFloat, primativeInt, wrappedInt, primativeLong, wrappedLong,
				primativeShort, wrappedShort, string, bigInteger, bigDecimal, locale, timeZone, currency, clazz,
				serializable, date, calendar, sqlDate, sqlTime, sqlTimestamp, byteArray, charArray, enumeration,
				subBasicType, subBasicTypeList, subBasicTypeSet, subBasicTypeMap);
	}
}

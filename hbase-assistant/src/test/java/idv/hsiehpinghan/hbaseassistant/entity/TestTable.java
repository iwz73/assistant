package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.ByteUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class TestTable extends HBaseTable {
	private static final byte[] SPACE = ByteUtility.SINGLE_SPACE_BYTE_ARRAY;
	private RowKey rowKey;
	private InfoFamily infoFamily;
	private XbrlInstanceFamily xbrlInstanceFamily;
	private FinancialReportFamily financialReportFamily;
	private MonthlyFamily monthlyFamily;
	private DailyFamily dailyFamily;

	@Override
	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	@Override
	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = (RowKey) rowKey;
	}

	public InfoFamily getInfoFamily() {
		if (infoFamily == null) {
			infoFamily = this.new InfoFamily(this);
		}
		return infoFamily;
	}

	public XbrlInstanceFamily getXbrlInstanceFamily() {
		if (xbrlInstanceFamily == null) {
			xbrlInstanceFamily = this.new XbrlInstanceFamily(this);
		}
		return xbrlInstanceFamily;
	}

	public FinancialReportFamily getFinancialReportFamily() {
		if (financialReportFamily == null) {
			financialReportFamily = this.new FinancialReportFamily(this);
		}
		return financialReportFamily;
	}

	public MonthlyFamily getMonthlyFamily() {
		if (monthlyFamily == null) {
			monthlyFamily = this.new MonthlyFamily(this);
		}
		return monthlyFamily;
	}

	public DailyFamily getDailyFamily() {
		if (dailyFamily == null) {
			dailyFamily = this.new DailyFamily(this);
		}
		return dailyFamily;
	}

	public class RowKey extends HBaseRowKey {
		public RowKey(TestTable entity) {
			super(entity);
		}

		public RowKey(byte[] bytes, TestTable entity) {
			super(entity);
			setBytes(bytes);
		}

		public RowKey(String stockCode, TestTable entity) {
			super(entity);
			setStockCode(stockCode);
		}

		public String getStockCode() {
			return ByteConvertUtility.getStringFromBytes(getBytes());
		}

		public void setStockCode(String stockCode) {
			byte[] bytes = ByteConvertUtility.toBytes(stockCode);
			setBytes(bytes);
		}
	}

	public class InfoFamily extends HBaseColumnFamily {
		public static final String ENUMERATION = "enumeration";
		public static final String STRING = "string";

		private InfoFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<InfoQualifier> getQualifiers() {
			return (Set<InfoQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public Enumeration getEnumeration() {
			HBaseColumnQualifier qual = new InfoQualifier(ENUMERATION);
			InfoValue val = (InfoValue) super.getLatestValue(qual);
			return val.getAsEnumeration();
		}

		public void setEnumeration(Date ver, Enumeration enumeration) {
			InfoQualifier qual = new InfoQualifier(ENUMERATION);
			InfoValue val = new InfoValue();
			val.set(enumeration);
			add(qual, ver, val);
		}

		public String getString() {
			HBaseColumnQualifier qual = new InfoQualifier(STRING);
			InfoValue val = (InfoValue) super.getLatestValue(qual);
			return val.getAsString();
		}

		public void setString(Date ver, String string) {
			InfoQualifier qual = new InfoQualifier(STRING);
			InfoValue val = new InfoValue();
			val.set(string);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new InfoQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new InfoValue(bytes);
		}

		public class InfoQualifier extends HBaseColumnQualifier {
			public InfoQualifier() {
				super();
			}

			public InfoQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public InfoQualifier(String columnName) {
				super();
				setColumnName(columnName);
			}

			public String getColumnName() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void setColumnName(String columnName) {
				byte[] bytes = ByteConvertUtility.toBytes(columnName);
				setBytes(bytes);
			}
		}

		public class InfoValue extends HBaseValue {
			public InfoValue() {
				super();
			}

			public InfoValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public String getAsString() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void set(String value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public Enumeration getAsEnumeration() {
				return Enumeration.valueOf(ByteConvertUtility
						.getStringFromBytes(getBytes()));
			}

			public void set(Enumeration value) {
				setBytes(ByteConvertUtility.toBytes(value.name()));
			}
		}
	}

	public class XbrlInstanceFamily extends HBaseColumnFamily {
		private XbrlInstanceFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<XbrlInstanceQualifier> getQualifiers() {
			return (Set<XbrlInstanceQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public XbrlInstanceValue getXbrlInstanceValue(String elementId,
				Enumeration enumeration, Date instant) {
			HBaseColumnQualifier qual = new XbrlInstanceQualifier(elementId,
					enumeration, instant);
			return (XbrlInstanceValue) super.getLatestValue(qual);
		}

		public void setXbrlInstanceValue(String elementId,
				Enumeration enumeration, Date instant, Date ver,
				String unitType, BigDecimal value) {
			HBaseColumnQualifier qual = new XbrlInstanceQualifier(elementId,
					enumeration, instant);
			XbrlInstanceValue val = new XbrlInstanceValue(unitType, value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new XbrlInstanceQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new XbrlInstanceValue(bytes);
		}

		public class XbrlInstanceQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int ENUMERATION_LENGTH = 10;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int ENUMERATION_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int ENUMERATION_END_INDEX = ENUMERATION_BEGIN_INDEX
					+ ENUMERATION_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ENUMERATION_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public XbrlInstanceQualifier() {
				super();
			}

			public XbrlInstanceQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public XbrlInstanceQualifier(String elementId,
					Enumeration enumeration, Date instant) {
				super();
				byte[] elementIdBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				byte[] enumerationBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				byte[] instantBytes = ByteConvertUtility.toBytes(instant);
				super.setBytes(ArrayUtility.addAll(elementIdBytes, SPACE,
						enumerationBytes, SPACE, instantBytes));
			}

			public String getElementId() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						ELEMENT_ID_BEGIN_INDEX, ELEMENT_ID_END_INDEX);
			}

			public void setElementId(String elementId) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ELEMENT_ID_BEGIN_INDEX,
						ELEMENT_ID_END_INDEX);
			}

			public Enumeration getEnumeration() {
				return Enumeration
						.valueOf(ByteConvertUtility.getStringFromBytes(
								getBytes(), ENUMERATION_BEGIN_INDEX,
								ENUMERATION_END_INDEX));
			}

			public void setEnumeration(Enumeration enumeration) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ENUMERATION_BEGIN_INDEX,
						ENUMERATION_END_INDEX);
			}

			public Date getInstant() {
				try {
					return ByteConvertUtility.getDateFromBytes(getBytes(),
							INSTANT_BEGIN_INDEX, INSTANT_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}

			public void setInstant(Date instant) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(instant);
				ArrayUtility.replace(bytes, subBytes, INSTANT_BEGIN_INDEX,
						INSTANT_END_INDEX);
			}
		}

		public class XbrlInstanceValue extends HBaseValue {
			private static final int UNIT_TYPE_LENGTH = 10;
			private static final int VALUE_LENGTH = 20;
			private static final int UNIT_TYPE_BEGIN_INDEX = 0;
			private static final int UNIT_TYPE_END_INDEX = UNIT_TYPE_BEGIN_INDEX
					+ UNIT_TYPE_LENGTH;
			private static final int VALUE_BEGIN_INDEX = UNIT_TYPE_END_INDEX + 1;
			private static final int VALUE_END_INDEX = VALUE_BEGIN_INDEX
					+ VALUE_LENGTH;

			public XbrlInstanceValue() {
				super();
			}

			public XbrlInstanceValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public XbrlInstanceValue(String unitType, BigDecimal value) {
				super();
				byte[] unitTypeBytes = ByteConvertUtility.toBytes(unitType,
						UNIT_TYPE_LENGTH);
				byte[] valueBytes = ByteConvertUtility.toBytes(value,
						VALUE_LENGTH);
				super.setBytes(ArrayUtility.addAll(unitTypeBytes, SPACE,
						valueBytes));
			}

			public String getUnitType() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						UNIT_TYPE_BEGIN_INDEX, UNIT_TYPE_END_INDEX);
			}

			public void setUnitType(String unitType) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(unitType,
						UNIT_TYPE_LENGTH);
				ArrayUtility.replace(bytes, subBytes, UNIT_TYPE_BEGIN_INDEX,
						UNIT_TYPE_END_INDEX);
			}

			public BigDecimal getValue() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes(),
						VALUE_BEGIN_INDEX, VALUE_END_INDEX);
			}

			public void setValue(BigDecimal value) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(value,
						VALUE_LENGTH);
				ArrayUtility.replace(bytes, subBytes, VALUE_BEGIN_INDEX,
						VALUE_END_INDEX);
			}
		}
	}

	public class FinancialReportFamily extends HBaseColumnFamily {
		private FinancialReportFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<FinancialReportQualifier> getQualifiers() {
			return (Set<FinancialReportQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public FinancialReportValue getFinancialReportValue(String elementId,
				Enumeration enumeration, Date instant) {
			HBaseColumnQualifier qual = new FinancialReportQualifier(elementId,
					enumeration, instant);
			return (FinancialReportValue) super.getLatestValue(qual);
		}

		public void setFinancialReportValue(String elementId,
				Enumeration enumeration, Date instant, Date ver,
				BigDecimal value) {
			HBaseColumnQualifier qual = new FinancialReportQualifier(elementId,
					enumeration, instant);
			FinancialReportValue val = new FinancialReportValue(value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new FinancialReportQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new FinancialReportValue(bytes);
		}

		public class FinancialReportQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int ENUMERATION_LENGTH = 10;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int ENUMERATION_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int ENUMERATION_END_INDEX = ENUMERATION_BEGIN_INDEX
					+ ENUMERATION_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ENUMERATION_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public FinancialReportQualifier() {
				super();
			}

			public FinancialReportQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public FinancialReportQualifier(String elementId,
					Enumeration enumeration, Date instant) {
				super();
				byte[] elementIdBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				byte[] enumerationBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				byte[] instantBytes = ByteConvertUtility.toBytes(instant);
				super.setBytes(ArrayUtility.addAll(elementIdBytes, SPACE,
						enumerationBytes, SPACE, instantBytes));
			}

			public String getElementId() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						ELEMENT_ID_BEGIN_INDEX, ELEMENT_ID_END_INDEX);
			}

			public void setElementId(String elementId) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ELEMENT_ID_BEGIN_INDEX,
						ELEMENT_ID_END_INDEX);
			}

			public Enumeration getEnumeration() {
				return Enumeration
						.valueOf(ByteConvertUtility.getStringFromBytes(
								getBytes(), ENUMERATION_BEGIN_INDEX,
								ENUMERATION_END_INDEX));
			}

			public void setEnumeration(Enumeration enumeration) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ENUMERATION_BEGIN_INDEX,
						ENUMERATION_END_INDEX);
			}

			public Date getInstant() {
				try {
					return ByteConvertUtility.getDateFromBytes(getBytes(),
							INSTANT_BEGIN_INDEX, INSTANT_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}

			public void setInstant(Date instant) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(instant);
				ArrayUtility.replace(bytes, subBytes, INSTANT_BEGIN_INDEX,
						INSTANT_END_INDEX);
			}
		}

		public class FinancialReportValue extends HBaseValue {
			public FinancialReportValue() {
				super();
			}

			public FinancialReportValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public FinancialReportValue(BigDecimal value) {
				super();
				setValue(value);
			}

			public BigDecimal getValue() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes());
			}

			public void setValue(BigDecimal value) {
				byte[] bytes = ByteConvertUtility.toBytes(value);
				setBytes(bytes);
			}
		}
	}

	public class MonthlyFamily extends HBaseColumnFamily {
		public static final String OPERATING_INCOME_OF_CURRENT_MONTH = "operatingIncomeOfCurrentMonth";
		public static final String OPERATING_INCOME_OF_DIFFERENT_PERCENT = "operatingIncomeOfDifferentPercent";
		public static final String OPERATING_INCOME_OF_COMMENT = "operatingIncomeOfComment";

		private MonthlyFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<MonthlyQualifier> getQualifiers() {
			return (Set<MonthlyQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigInteger getOperatingIncomeOfCurrentMonth(int year, int month) {
			HBaseColumnQualifier qual = new MonthlyQualifier(
					OPERATING_INCOME_OF_CURRENT_MONTH, year, month);
			MonthlyValue val = (MonthlyValue) super.getLatestValue(qual);
			return val.getAsBigInteger();
		}

		public void setOperatingIncomeOfCurrentMonth(int year, int month,
				Date ver, BigInteger operatingIncomeOfCurrentMonth) {
			MonthlyQualifier qual = new MonthlyQualifier(
					OPERATING_INCOME_OF_CURRENT_MONTH, year, month);
			MonthlyValue val = new MonthlyValue();
			val.set(operatingIncomeOfCurrentMonth);
			add(qual, ver, val);
		}

		public BigDecimal getOperatingIncomeOfDifferentPercent(int year,
				int month) {
			HBaseColumnQualifier qual = new MonthlyQualifier(
					OPERATING_INCOME_OF_DIFFERENT_PERCENT, year, month);
			MonthlyValue val = (MonthlyValue) super.getLatestValue(qual);
			return val.getAsBigDecimal();
		}

		public void setOperatingIncomeOfDifferentPercent(int year, int month,
				Date ver, BigDecimal operatingIncomeOfDifferentPercent) {
			MonthlyQualifier qual = new MonthlyQualifier(
					OPERATING_INCOME_OF_DIFFERENT_PERCENT, year, month);
			MonthlyValue val = new MonthlyValue();
			val.set(operatingIncomeOfDifferentPercent);
			add(qual, ver, val);
		}

		public String getOperatingIncomeOfComment(int year, int month) {
			HBaseColumnQualifier qual = new MonthlyQualifier(
					OPERATING_INCOME_OF_COMMENT, year, month);
			MonthlyValue val = (MonthlyValue) super.getLatestValue(qual);
			return val.getAsString();
		}

		public void setOperatingIncomeOfComment(int year, int month, Date ver,
				String operatingIncomeOfComment) {
			MonthlyQualifier qual = new MonthlyQualifier(
					OPERATING_INCOME_OF_COMMENT, year, month);
			MonthlyValue val = new MonthlyValue();
			val.set(operatingIncomeOfComment);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new MonthlyQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new MonthlyValue(bytes);
		}

		public class MonthlyQualifier extends HBaseColumnQualifier {
			private static final int COLUMN_NAME_LENGTH = 100;
			private static final int YEAR_LENGTH = 4;
			private static final int MONTH_LENGTH = 2;
			private static final int COLUMN_NAME_BEGIN_INDEX = 0;
			private static final int COLUMN_NAME_END_INDEX = COLUMN_NAME_BEGIN_INDEX
					+ COLUMN_NAME_LENGTH;
			private static final int YEAR_BEGIN_INDEX = COLUMN_NAME_END_INDEX + 1;
			private static final int YEAR_END_INDEX = YEAR_BEGIN_INDEX
					+ YEAR_LENGTH;
			private static final int MONTH_BEGIN_INDEX = YEAR_END_INDEX + 1;
			private static final int MONTH_END_INDEX = MONTH_BEGIN_INDEX
					+ MONTH_LENGTH;

			public MonthlyQualifier() {
				super();
			}

			public MonthlyQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public MonthlyQualifier(String columnName, int year, int month) {
				super();
				byte[] columnNameBytes = ByteConvertUtility.toBytes(columnName,
						COLUMN_NAME_LENGTH);
				byte[] yearBytes = ByteConvertUtility
						.toBytes(year, YEAR_LENGTH);
				byte[] monthBytes = ByteConvertUtility.toBytes(month,
						MONTH_LENGTH);
				super.setBytes(ArrayUtility.addAll(columnNameBytes, SPACE,
						yearBytes, SPACE, monthBytes));
			}

			public String getColumnName() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						COLUMN_NAME_BEGIN_INDEX, COLUMN_NAME_END_INDEX);
			}

			public void setColumnName(String columnName) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(columnName,
						COLUMN_NAME_LENGTH);
				ArrayUtility.replace(bytes, subBytes, COLUMN_NAME_BEGIN_INDEX,
						COLUMN_NAME_END_INDEX);
			}

			public int getYear() {
				return ByteConvertUtility.getIntFromBytes(getBytes(),
						YEAR_BEGIN_INDEX, YEAR_END_INDEX);
			}

			public void setYear(int year) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(year, YEAR_LENGTH);
				ArrayUtility.replace(bytes, subBytes, YEAR_BEGIN_INDEX,
						YEAR_END_INDEX);
			}

			public int getMonth() {
				return ByteConvertUtility.getIntFromBytes(getBytes(),
						MONTH_BEGIN_INDEX, MONTH_END_INDEX);
			}

			public void setMonth(int month) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(month,
						MONTH_LENGTH);
				ArrayUtility.replace(bytes, subBytes, MONTH_BEGIN_INDEX,
						MONTH_END_INDEX);
			}
		}

		public class MonthlyValue extends HBaseValue {
			public MonthlyValue() {
				super();
			}

			public MonthlyValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public BigInteger getAsBigInteger() {
				return ByteConvertUtility.getBigIntegerFromBytes(getBytes());
			}

			public void set(BigInteger value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public String getAsString() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void set(String value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public BigDecimal getAsBigDecimal() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes());
			}

			public void set(BigDecimal value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}
		}
	}

	public class DailyFamily extends HBaseColumnFamily {
		public static final String CLOSING_CONDITION_OF_OPENING_PRICE = "closingConditionOfOpeningPrice";
		public static final String CLOSING_CONDITION_OF_STOCK_AMOUNT = "closingConditionOfStockAmount";

		private DailyFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<DailyQualifier> getQualifiers() {
			return (Set<DailyQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigDecimal getClosingConditionOfOpeningPrice(Date date) {
			HBaseColumnQualifier qual = new DailyQualifier(
					CLOSING_CONDITION_OF_OPENING_PRICE, date);
			DailyValue val = (DailyValue) super.getLatestValue(qual);
			return val.getAsBigDecimal();
		}

		public void setClosingConditionOfOpeningPrice(Date date, Date ver,
				BigDecimal closingConditionOfOpeningPrice) {
			DailyQualifier qual = new DailyQualifier(
					CLOSING_CONDITION_OF_OPENING_PRICE, date);
			DailyValue val = new DailyValue();
			val.set(closingConditionOfOpeningPrice);
			add(qual, ver, val);
		}

		public BigInteger getClosingConditionOfStockAmount(Date date) {
			HBaseColumnQualifier qual = new DailyQualifier(
					CLOSING_CONDITION_OF_STOCK_AMOUNT, date);
			DailyValue val = (DailyValue) super.getLatestValue(qual);
			return val.getAsBigInteger();
		}

		public void setClosingConditionOfStockAmount(Date date, Date ver,
				BigInteger closingConditionOfStockAmount) {
			DailyQualifier qual = new DailyQualifier(
					CLOSING_CONDITION_OF_STOCK_AMOUNT, date);
			DailyValue val = new DailyValue();
			val.set(closingConditionOfStockAmount);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new DailyQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new DailyValue(bytes);
		}

		public class DailyQualifier extends HBaseColumnQualifier {
			private static final int COLUMN_NAME_LENGTH = 100;
			private static final int DATE_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int COLUMN_NAME_BEGIN_INDEX = 0;
			private static final int COLUMN_NAME_END_INDEX = COLUMN_NAME_BEGIN_INDEX
					+ COLUMN_NAME_LENGTH;
			private static final int DATE_BEGIN_INDEX = COLUMN_NAME_END_INDEX + 1;
			private static final int DATE_END_INDEX = DATE_BEGIN_INDEX
					+ DATE_LENGTH;

			public DailyQualifier() {
				super();
			}

			public DailyQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public DailyQualifier(String columnName, Date date) {
				super();
				byte[] columnNameBytes = ByteConvertUtility.toBytes(columnName,
						COLUMN_NAME_LENGTH);
				byte[] dateBytes = ByteConvertUtility.toBytes(date);
				super.setBytes(ArrayUtility.addAll(columnNameBytes, SPACE,
						dateBytes));
			}

			public String getColumnName() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						COLUMN_NAME_BEGIN_INDEX, COLUMN_NAME_END_INDEX);
			}

			public void setColumnName(String columnName) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(columnName,
						COLUMN_NAME_LENGTH);
				ArrayUtility.replace(bytes, subBytes, COLUMN_NAME_BEGIN_INDEX,
						COLUMN_NAME_END_INDEX);
			}

			public Date getDate() {
				try {
					return ByteConvertUtility.getDateFromBytes(getBytes(),
							DATE_BEGIN_INDEX, DATE_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}

			public void setDate(Date date) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(date);
				ArrayUtility.replace(bytes, subBytes, DATE_BEGIN_INDEX,
						DATE_END_INDEX);
			}
		}

		public class DailyValue extends HBaseValue {
			public DailyValue() {
				super();
			}

			public DailyValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public BigInteger getAsBigInteger() {
				return ByteConvertUtility.getBigIntegerFromBytes(getBytes());
			}

			public void set(BigInteger value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public BigDecimal getAsBigDecimal() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes());
			}

			public void set(BigDecimal value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}
		}
	}
}

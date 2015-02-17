package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.datatypeutility.utility.StringUtility;
import idv.hsiehpinghan.hbaseassistant.utility.HBaseClassGenerateUtility.Container.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

public class HBaseClassGenerateUtility {
	private static final String EMPTY_STRING = StringUtility.EMPTY_STRING;
	private static final String COLUMNS = "Columns";
	private static final String COLUMN_NAME = "columnName";
	private static final String QUALIFIER = "Qualifier";
	private static final String VALUE = "Value";
	private static String tableName;
	private static Container rowKey;
	private static List<Family> families = new ArrayList<Family>();
	private static int seriesNumber = 0;

	private static void parseJson(File jsonfile) throws IOException {
		JsonNode jsonNode = getJson(jsonfile);
		parseTable(jsonNode);
	}

	private static String getEntityClassCode() {
		StringBuilder sb = new StringBuilder();
		generateImportSection(sb);
		generateTableSection(sb);
		return sb.toString();
	}

	private static String getEntityClassTestCode() {
		StringBuilder sb = new StringBuilder();
		generateImportTestSection(sb);
		generateTableTestSection(sb);
		return sb.toString();
	}

	private static String getRepositoryClassCode() {
		StringBuilder sb = new StringBuilder();
		generateRepositoryImportSection(sb);
		generateRepositorySection(sb);
		return sb.toString();
	}

	private static String getRepositoryClassTestCode() {
		StringBuilder sb = new StringBuilder();
		generateRepositoryImportTestSection(sb);
		generateRepositoryTestSection(sb);
		return sb.toString();
	}

	private static int getSeriesNumber() {
		return ++seriesNumber;
	}

	private static void parseTable(JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		int tableCnt = 0;
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			tableName = ent.getKey();
			parseRowKeyAndColumnFamily(ent.getValue());
			++tableCnt;
		}
		checkTableAmount(tableCnt);
	}

	private static void checkTableAmount(int tableCnt) {
		if (tableCnt != 1) {
			throw new RuntimeException("Table amount(" + tableCnt
					+ ") not equal 1 !!!");
		}
	}

	private static void parseRowKeyAndColumnFamily(JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		int i = 0;
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			if (i == 0) {
				rowKey = new Container(ent.getKey());
				parseContainerContent(rowKey, ent.getValue());
			} else {
				Family fam = new Family(ent.getKey());
				families.add(fam);
				parseFamilyContent(fam, ent.getValue());
			}
			++i;
		}
	}

	private static void parseContainerContent(Container con, JsonNode jsonNode) {
		ArrayNode arrNode = (ArrayNode) jsonNode;
		for (JsonNode node : arrNode) {
			String type = node.get("type").getTextValue();
			String name = node.get("name").getTextValue();
			JsonNode lenNode = node.get("length");
			String length = null;
			if (lenNode != null) {
				length = lenNode.getValueAsText();
			}
			con.values.add(new Value(type, name, length));
		}
	}

	private static void parseColumnsContent(List<Value> cols, JsonNode jsonNode) {
		ArrayNode arrNode = (ArrayNode) jsonNode;
		for (JsonNode node : arrNode) {
			String type = node.get("type").getTextValue();
			String name = node.get("name").getTextValue();
			cols.add(new Value(type, name));
		}
	}

	private static void parseFamilyContent(Family family, JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		int qualCnt = 0;
		int valCnt = 0;
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			String key = ent.getKey();
			if (COLUMNS.equals(key)) {
				List<Value> cols = new ArrayList<Value>();
				family.columns = cols;
				parseColumnsContent(cols, ent.getValue());
			} else if (key.endsWith(QUALIFIER)) {
				Container con = new Container(family, key);
				family.qualCon = con;
				parseContainerContent(con, ent.getValue());
				++qualCnt;
			} else if (key.endsWith(VALUE)) {
				Container con = new Container(family, key);
				family.valCon = con;
				parseContainerContent(con, ent.getValue());
				++valCnt;
			} else {
				throw new RuntimeException("Key(" + key
						+ ") not a qualified format !!!");
			}
		}
		checkQualAndValAmount(family, qualCnt, valCnt);
	}

	private static void checkQualAndValAmount(Family family, int qualCnt,
			int valCnt) {
		if (qualCnt != 1) {
			throw new RuntimeException("Family(" + family.type
					+ ")'s qualifier amount(" + qualCnt + ") not equal 1 !!!");
		}
		if (valCnt != 1) {
			throw new RuntimeException("Family(" + family.type
					+ ")'s qualifier amount(" + qualCnt + ") not equal 1 !!!");
		}
	}

	private static void generateRepositoryGenerateEntityMethod(StringBuilder sb) {
		sb.append("public " + tableName + " generateEntity("
				+ getRowKeyFieldParameterString(true) + ") { ");
		sb.append(tableName + " entity = new " + tableName + "(); ");
		sb.append("generateRowKey(" + getRowKeyFieldParameterString(false)
				+ ", entity); ");
		sb.append("return entity; ");
		sb.append("} ");
	}

	private static void generateRepositoryGetMethod(StringBuilder sb) {
		sb.append("public " + tableName + " get("
				+ getRowKeyFieldParameterString(true)
				+ ") throws IllegalAccessException, ");
		sb.append("NoSuchMethodException, SecurityException, InstantiationException, ");
		sb.append("IllegalArgumentException, InvocationTargetException, IOException { ");
		sb.append("HBaseRowKey rowKey = getRowKey("
				+ getRowKeyFieldParameterString(false) + "); ");
		sb.append("return (" + tableName + ") hbaseAssistant.get(rowKey); ");
		sb.append("} ");
	}

	private static void generateRepositoryGetRowAmountMethod(StringBuilder sb) {
		sb.append("public int getRowAmount() { ");
		sb.append("return hbaseAssistant.getRowAmount(getTargetTableClass()); ");
		sb.append("} ");
	}

	private static void generateRepositoryGetRowKeysMethod(StringBuilder sb) {
		sb.append("public List<RowKey> getRowKeys() { ");
		sb.append("List<HBaseTable> entities = hbaseAssistant.scan(getTargetTableClass(), new KeyOnlyFilter()); ");
		sb.append("List<RowKey> rowKeys = new ArrayList<RowKey>(entities.size()); ");
		sb.append("for (HBaseTable entity : entities) { ");
		sb.append("RowKey rowKey = (RowKey) entity.getRowKey(); ");
		sb.append("rowKeys.add(rowKey); ");
		sb.append("} ");
		sb.append("return rowKeys; ");
		sb.append("} ");
	}

	private static void generateRepositoryGetRowKeyMethod(StringBuilder sb) {
		sb.append("private HBaseRowKey getRowKey("
				+ getRowKeyFieldParameterString(true) + ") { ");
		sb.append(tableName + " entity = new " + tableName + "(); ");
		sb.append("generateRowKey(" + getRowKeyFieldParameterString(false)
				+ ", entity); ");
		sb.append("return entity.getRowKey(); ");
		sb.append("} ");
	}

	private static void generateRepositoryGenerateRowKeyMethod(StringBuilder sb) {
		sb.append("private void generateRowKey("
				+ getRowKeyFieldParameterString(true) + ", " + tableName
				+ " entity) { ");
		sb.append("entity.new RowKey(" + getRowKeyFieldParameterString(false)
				+ ", entity); ");
		sb.append("} ");
	}

	private static void generateRepositorySection(StringBuilder sb) {
		sb.append("@Repository ");
		sb.append("public class " + tableName
				+ "Repository extends RepositoryBase { ");
		sb.append("@Autowired ");
		sb.append("private HbaseAssistant hbaseAssistant; ");
		sb.append("@Override ");
		sb.append("public Class<? extends HBaseTable> getTargetTableClass() { ");
		sb.append("return " + tableName + ".class; ");
		sb.append("} ");
		generateRepositoryGenerateEntityMethod(sb);
		generateRepositoryGetMethod(sb);
		generateRepositoryGetRowAmountMethod(sb);
		generateRepositoryGetRowKeysMethod(sb);
		sb.append("@Override ");
		sb.append("protected HbaseAssistant getHbaseAssistant() { ");
		sb.append("return hbaseAssistant; ");
		sb.append("} ");
		generateRepositoryGetRowKeyMethod(sb);
		generateRepositoryGenerateRowKeyMethod(sb);
		sb.append("} ");
	}

	private static void generateTableSection(StringBuilder sb) {
		sb.append("public class " + tableName + " extends HBaseTable { ");
		sb.append(" private static final byte[] SPACE = ByteUtility.SINGLE_SPACE_BYTE_ARRAY; ");
		sb.append(" private " + rowKey.type + " rowKey; ");
		for (Family family : families) {
			sb.append("private " + family.type + " "
					+ StringUtils.uncapitalize(family.type) + "; ");
		}
		sb.append("@Override ");
		sb.append("public HBaseRowKey getRowKey() { ");
		sb.append("return rowKey; ");
		sb.append("} ");
		sb.append("@Override ");
		sb.append("public void setRowKey(HBaseRowKey rowKey) { ");
		sb.append("this.rowKey = (" + rowKey.type + ") rowKey; ");
		sb.append("} ");

		for (Family family : families) {
			sb.append("public " + family.type + " get" + family.type + "() { ");
			sb.append("if (" + StringUtils.uncapitalize(family.type)
					+ " == null) { ");
			sb.append(StringUtils.uncapitalize(family.type) + " = this.new "
					+ family.type + "(this); ");
			sb.append("} ");
			sb.append("return " + StringUtils.uncapitalize(family.type) + "; ");
			sb.append("} ");
		}
		generateRowKeySection(sb);
		generateColumnFamiliesSection(sb);
		sb.append("} ");
	}

	private static String generateTestField(Value value) {
		StringBuilder sb = new StringBuilder();
		sb.append("private " + value.type + " " + value.name + " = ");
		int sn = getSeriesNumber();
		switch (value.type) {
		case "boolean":
			sb.append("false");
			break;
		case "byte":
			sb.append("'b'");
			break;
		case "char":
			sb.append("'c'");
			break;
		case "double":
			sb.append(sn + "." + sn);
			break;
		case "float":
			sb.append(sn + "." + sn + "f");
			break;
		case "int":
			sb.append(String.valueOf(sn));
			break;
		case "long":
			sb.append(String.valueOf(sn));
			break;
		case "short":
			sb.append(String.valueOf(sn));
			break;
		case "Date":
			sb.append("DateUtility.getDate(2015, 2, 3)");
			break;
		case "String":
			sb.append("\"" + value.name + "\"");
			break;
		default:
			sb.append("null");
		}
		sb.append("; ");
		return sb.toString();
	}

	private static void generateRepositoryTestFields(StringBuilder sb) {
		generateTestFields(sb);
		sb.append("private " + tableName + "Repository repository; ");
	}

	private static void generateTestFields(StringBuilder sb) {
		sb.append("private Date ver = DateUtility.getDate(2015, 2, 3); ");
		for (Value value : rowKey.values) {
			sb.append(generateTestField(value));
		}
		for (Family family : families) {
			for (Value value : family.columns) {
				sb.append(generateTestField(value));
			}
			for (Value value : family.valCon.values) {
				sb.append(generateTestField(value));
			}
		}
	}

	private static void generateTableTestSection(StringBuilder sb) {
		sb.append("public class " + tableName + "Test { ");
		generateTestFields(sb);
		generateTestBytesConvertMethod(sb);
		generateTestRowKeyMethod(sb);
		for (Family family : families) {
			generateTestFamilyMethod(sb, family);
			generateTestGenerateFamilyContentMethod(sb, family);
			generateTestAssertFamilyMethod(sb, family);
		}
		sb.append("} ");
	}

	private static void generateRepositoryTestSection(StringBuilder sb) {
		sb.append("public class " + tableName + "RepositoryTest { ");
		generateRepositoryTestFields(sb);
		generateRepositoryTestBeforeClassMethod(sb);
		generateRepositoryTestPutMethod(sb);
		generateRepositoryTestGetMethod(sb);
		// generateTestRowKeyMethod(sb);
		// for (Family family : families) {
		// generateTestFamilyMethod(sb, family);
		// generateTestGenerateFamilyContentMethod(sb, family);
		// generateTestAssertFamilyMethod(sb, family);
		// }
		for (Family family : families) {
			generateTestGenerateFamilyContentMethod(sb, family);
			generateTestAssertFamilyMethod(sb, family);
		}
		sb.append("} ");
	}

	private static void generateRepositoryTestPutMethod(StringBuilder sb) {
		sb.append("@Test ");
		sb.append("public void put() throws Exception { ");
		sb.append(tableName + " entity = repository.generateEntity("
				+ getRowKeyFieldParameterString(false) + "); ");
		for (Family family : families) {
			sb.append("generate" + family.type + "Content(entity); ");
		}
		sb.append("repository.put(entity); ");
		sb.append("Assert.assertTrue(repository.exists(entity.getRowKey())); ");
		sb.append("} ");
	}

	private static void generateRepositoryTestGetMethod(StringBuilder sb) {
		sb.append("@Test(dependsOnMethods = { \"put\" }) ");
		sb.append("public void get() throws Exception { ");
		sb.append(tableName + " entity = repository.get("
				+ getRowKeyFieldParameterString(false) + "); ");
		for (Family family : families) {
			sb.append("assert" + family.type + "(entity); ");
		}
		sb.append("} ");
	}

	private static void generateRepositoryTestBeforeClassMethod(StringBuilder sb) {
		sb.append("@BeforeClass ");
		sb.append("public void beforeClass() throws Exception { ");
		sb.append("ApplicationContext applicationContext = TestngSuitSetting.getApplicationContext(); ");
		sb.append("repository = applicationContext.getBean(" + tableName
				+ "Repository.class); ");
		sb.append("} ");
	}

	private static void generateTestFamilyMethod(StringBuilder sb, Family family) {
		sb.append("private void test" + family.type + "(" + tableName
				+ " entity) { ");
		sb.append("generate" + family.type + "Content(entity); ");
		sb.append("assert" + family.type + "(entity); ");
		sb.append("} ");
	}

	private static void generateTestGenerateFamilyContentMethod(
			StringBuilder sb, Family family) {
		sb.append("private void generate" + family.type + "Content("
				+ tableName + " entity) { ");
		sb.append(family.type + " fam = entity.get" + family.type + "(); ");
		for (Value value : family.columns) {
			sb.append("fam.set" + StringUtils.capitalize(value.name) + "(ver, "
					+ value.name + "); ");
		}
		sb.append("} ");
	}

	private static void generateTestAssertFamilyMethod(StringBuilder sb,
			Family family) {
		sb.append("private void assert" + family.type + "(" + tableName
				+ " entity) { ");
		sb.append(family.type + " fam = entity.get" + family.type + "(); ");
		for (Value value : family.columns) {
			sb.append("Assert.assertEquals(" + value.name + ", fam.get"
					+ StringUtils.capitalize(value.name) + "()); ");
		}
		sb.append("} ");
	}

	private static void generateTestBytesConvertMethod(StringBuilder sb) {
		sb.append("@Test ");
		sb.append("public void bytesConvert() { ");
		sb.append(tableName + " entity = new " + tableName + "(); ");
		sb.append("testRowKey(entity); ");
		for (Family family : families) {
			sb.append("test" + family.type + "(entity); ");
		}
		sb.append("} ");
	}

	private static String getAssertEquals(Value value) {
		return "Assert.assertEquals(" + value.name + ", key.get"
				+ StringUtils.capitalize(value.name) + "()); ";
	}

	private static void generateTestRowKeyMethod(StringBuilder sb) {
		sb.append("private void testRowKey(" + tableName + " entity) { ");
		sb.append("RowKey key = entity.new RowKey("
				+ getRowKeyFieldParameterString(false) + ", entity); ");
		for (Value value : rowKey.values) {
			sb.append(getAssertEquals(value));
		}
		sb.append("} ");
	}

	private static void generateImportTestSection(StringBuilder sb) {
		generateImportSection(sb);
		sb.append("import idv.hsiehpinghan.datetimeutility.utility.DateUtility; ");
		sb.append("import org.testng.Assert; ");
		sb.append("import org.testng.annotations.Test; ");
	}

	private static void generateRepositoryImportSection(StringBuilder sb) {
		generateImportSection(sb);
		sb.append("import idv.hsiehpinghan.hbaseassistant.repository.RepositoryBase; ");
		sb.append("import idv.hsiehpinghan.hbaseassistant.assistant.HbaseAssistant; ");
		sb.append("import org.springframework.beans.factory.annotation.Autowired; ");
		sb.append("import org.springframework.stereotype.Repository; ");
		sb.append("import java.io.IOException; ");
		sb.append("import java.lang.reflect.InvocationTargetException; ");
		sb.append("import java.util.ArrayList; ");
		sb.append("import java.util.List; ");
		sb.append("import org.apache.hadoop.hbase.filter.KeyOnlyFilter; ");
	}

	private static void generateRepositoryImportTestSection(StringBuilder sb) {
		generateImportSection(sb);
		sb.append("import org.testng.annotations.BeforeClass; ");
		sb.append("import org.springframework.context.ApplicationContext; ");
		sb.append("import idv.hsiehpinghan.datetimeutility.utility.DateUtility; ");
		sb.append("import org.testng.Assert; ");
		sb.append("import org.testng.annotations.Test; ");
	}

	private static void generateImportSection(StringBuilder sb) {
		sb.append("import idv.hsiehpinghan.collectionutility.utility.ArrayUtility; ");
		sb.append("import idv.hsiehpinghan.datatypeutility.utility.ByteUtility; ");
		sb.append("import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily; ");
		sb.append("import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier; ");
		sb.append("import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey; ");

		sb.append("import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable; ");
		sb.append("import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue; ");
		sb.append("import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility; ");
		sb.append("import java.math.BigDecimal; ");
		sb.append("import java.math.BigInteger; ");
		sb.append("import java.text.ParseException; ");
		sb.append("import java.util.Date; ");
		sb.append("import java.util.Set; ");
	}

	private static String getColumnNameString(String str) {
		return StringUtility.getFinalFormatString(str);
	}

	private static String getLengthString(String str) {
		return StringUtility.getFinalFormatString(str) + "_LENGTH";
	}

	private static String getBeginIndexString(String str) {
		return StringUtility.getFinalFormatString(str) + "_BEGIN_INDEX";
	}

	private static String getEndIndexString(String str) {
		return StringUtility.getFinalFormatString(str) + "_END_INDEX";
	}

	private static void generateLengthSection(StringBuilder sb, Container con) {
		if (con.values.size() <= 1) {
			return;
		} else {
			for (Value value : con.values) {
				if (value.length == null && "Date".equals(value.type)) {
					sb.append("private static final int "
							+ getLengthString(value.name)
							+ " = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH; ");
				} else {
					sb.append("private static final int "
							+ getLengthString(value.name) + " = "
							+ value.length + "; ");
				}
			}
		}
	}

	private static void generateIndexSection(StringBuilder sb, Container con) {
		if (con.values.size() <= 1) {
			return;
		} else {
			int i = 0;
			String beforeEndIndexString = null;
			for (Value value : con.values) {
				if (i == 0) {
					sb.append("private static final int "
							+ getBeginIndexString(value.name) + " = 0; ");
				} else {
					sb.append("private static final int "
							+ getBeginIndexString(value.name) + " = "
							+ beforeEndIndexString + " + 1; ");
				}
				beforeEndIndexString = getEndIndexString(value.name);
				sb.append("private static final int " + beforeEndIndexString
						+ " = " + getBeginIndexString(value.name) + " + "
						+ getLengthString(value.name) + "; ");
				++i;
			}
		}
	}

	private static String getParamsWithTypeString(List<Value> values) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = values.size(); i < size; ++i) {
			Value val = values.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append(val.type + " " + val.name);
		}
		return sb.toString();
	}

	private static String getToBytesString(Value val) {
		StringBuilder sb = new StringBuilder();
		if (isEnum(val.type)) {
			sb.append("ByteConvertUtility.toBytes(" + val.name + ".name()");
		} else {
			sb.append("ByteConvertUtility.toBytes(" + val.name);
		}
		if (val.length != null) {
			sb.append(", " + getLengthString(val.name));
		}
		sb.append("); ");
		return sb.toString();
	}

	private static String getFromBytesString(Value val, int filedsAmt) {
		if (isEnum(val.type)) {
			return val.type
					+ ".valueOf(ByteConvertUtility.getStringFromBytes(getBytes()"
					+ getBeginAndEndIndexString(filedsAmt, val) + ")); ";
		} else if (isPrimativeType(val.type)) {
			return "ByteConvertUtility.get" + StringUtils.capitalize(val.type)
					+ "FromBytes(getBytes()"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ";
		} else {
			return "ByteConvertUtility.get" + val.type + "FromBytes(getBytes()"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ";
		}
	}

	private static void generateTypeOneConstructor(StringBuilder sb,
			Container con, boolean withEntity) {
		if (withEntity == true) {
			sb.append("public " + con.type + "(" + tableName + " entity) { ");
			sb.append("super(entity); ");
			sb.append("} ");
		} else {
			sb.append("public " + con.type + "() { ");
			sb.append("super(); ");
			sb.append("} ");
		}
	}

	private static void generateTypeTwoConstructor(StringBuilder sb,
			Container con, boolean withEntity) {
		if (withEntity == true) {
			sb.append("public " + con.type + "(byte[] bytes, " + tableName
					+ " entity) { ");
			sb.append("super(entity); ");
		} else {
			sb.append("public  " + con.type + "(byte[] bytes) { ");
			sb.append("super(); ");
		}
		sb.append("setBytes(bytes); ");
		sb.append("} ");
	}

	private static void generateTypeThreeConstructor(StringBuilder sb,
			Container con, boolean withEntity) {
		List<Value> vals = con.values;
		int filedsAmt = vals.size();
		if (filedsAmt <= 0) {
			return;
		}
		if (withEntity == true) {
			sb.append("public " + con.type + "("
					+ getParamsWithTypeString(con.values) + ", " + tableName
					+ " entity) { ");
			sb.append("super(entity); ");
		} else {
			sb.append("public " + con.type + "("
					+ getParamsWithTypeString(con.values) + ") { ");
			sb.append("super(); ");
		}
		if (filedsAmt == 1) {
			Value val = vals.get(0);
			sb.append("set" + StringUtils.capitalize(val.name) + "(" + val.name
					+ "); ");
		} else {
			for (Value val : vals) {
				sb.append("byte[] " + val.name + "Bytes = "
						+ getToBytesString(val));
			}
			sb.append("super.setBytes(ArrayUtility.addAll( ");
			int i = 0;
			for (Value val : vals) {
				if (i > 0) {
					sb.append(", SPACE, ");
				}
				sb.append(val.name + "Bytes ");
				++i;
			}
			sb.append(")); ");
		}
		sb.append("} ");
	}

	private static void generateRowKeyConstructorsSection(StringBuilder sb,
			Container con) {
		generateTypeOneConstructor(sb, con, true);
		generateTypeTwoConstructor(sb, con, true);
		generateTypeThreeConstructor(sb, con, true);
	}

	private static void generateConstructorsSection(StringBuilder sb,
			Container con) {
		generateTypeOneConstructor(sb, con, false);
		generateTypeTwoConstructor(sb, con, false);
		generateTypeThreeConstructor(sb, con, false);
	}

	private static String getBeginAndEndIndexString(int filedsAmt, Value val) {
		if (filedsAmt <= 1) {
			return "";
		}
		return getBeginAndEndIndexString(val);
	}

	private static String getBeginAndEndIndexString(Value val) {
		String beginIdxStr = getBeginIndexString(val.name);
		String endIdxStr = getEndIndexString(val.name);
		return ", " + beginIdxStr + ", " + endIdxStr;
	}

	private static void generateGetterSetterSection(StringBuilder sb,
			Container con) {
		List<Value> vals = con.values;
		int filedsAmt = vals.size();
		for (Value val : vals) {
			generateValueGetter(sb, val, filedsAmt);
			generateValueSetter(sb, val, filedsAmt);
		}
	}

	private static void generateValueContainerAsGetterAsSetterSection(
			StringBuilder sb, Container con) {
		Set<String> colTypes = getColumnTypes(con.family);
		for (String colType : colTypes) {
			generateValueContainerAsGetter(sb, colType);
			generateValueContainerAsSetter(sb, colType);
		}
	}

	private static String getQualifierFieldWithoutColumnNameParameterString(
			Family family, boolean withType) {
		List<Value> vals = family.qualCon.values;
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Value val : vals) {
			if (COLUMN_NAME.equals(val.name)) {
				continue;
			}
			if (i > 0) {
				sb.append(",");
			}
			if (withType == true) {
				sb.append(val.type + " ");
			}
			sb.append(val.name);
			++i;
		}
		return sb.toString();
	}

	private static String getValueFieldParameterString(Family family,
			boolean withType) {
		List<Value> vals = family.valCon.values;
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Value val : vals) {
			if (i > 0) {
				sb.append(",");
			}
			if (withType == true) {
				sb.append(val.type + " ");
			}
			sb.append(val.name);
			++i;
		}
		return sb.toString();
	}

	private static String getRowKeyFieldParameterString(boolean withType) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Value val : rowKey.values) {
			if (i > 0) {
				sb.append(",");
			}
			if (withType == true) {
				sb.append(val.type + " ");
			}
			sb.append(val.name);
			++i;
		}
		return sb.toString();
	}

	private static void generateFamilyGetterSetterSection(StringBuilder sb,
			Family family) {

		List<Value> columns = family.columns;
		if (columns == null) {
			generateFamilyValueGetter(sb, family);
			generateFamilyValueSetter(sb, family);
		} else {
			for (Value column : columns) {
				generateFamilyColumnGetter(sb, family, column);
				generateFamilyColumnSetter(sb, family, column);
			}
		}
	}

	private static void generateValueGetter(StringBuilder sb, Value val,
			int filedsAmt) {
		sb.append("public " + val.type + " get"
				+ StringUtils.capitalize(val.name) + "() { ");
		if ("Date".equals(val.type)) {
			sb.append("try { ");
		}
		sb.append("return " + getFromBytesString(val, filedsAmt));
		if ("Date".equals(val.type)) {
			sb.append("} catch (ParseException e) { ");
			sb.append("throw new RuntimeException(e); ");
			sb.append("} ");
		}
		sb.append("} ");
	}

	private static void generateFamilyColumnGetter(StringBuilder sb,
			Family family, Value val) {
		String qualParamStr = getQualifierFieldWithoutColumnNameParameterString(
				family, true);
		String qualParamStrWithoutType = getQualifierFieldWithoutColumnNameParameterString(
				family, false);
		sb.append("public " + val.type + " get"
				+ StringUtils.capitalize(val.name) + "(" + qualParamStr
				+ ") { ");
		sb.append("HBaseColumnQualifier qual = new " + family.qualCon.type
				+ "(");
		sb.append(getColumnNameString(val.name));
		if (EMPTY_STRING.equals(qualParamStrWithoutType) == false) {
			sb.append("," + qualParamStrWithoutType);
		}
		sb.append("); ");
		sb.append(family.valCon.type + " val = (" + family.valCon.type
				+ ") super.getLatestValue(qual); ");
		sb.append("return val.getAs" + val.type + "(); ");
		sb.append("} ");
	}

	private static void generateFamilyValueGetter(StringBuilder sb,
			Family family) {
		String paramStr = getQualifierFieldWithoutColumnNameParameterString(
				family, true);
		String paramStrWithoutType = getQualifierFieldWithoutColumnNameParameterString(
				family, false);
		sb.append("public " + family.valCon.type + " get" + family.valCon.type
				+ "(" + paramStr + ") { ");
		sb.append("HBaseColumnQualifier qual = new " + family.qualCon.type
				+ "(" + paramStrWithoutType + "); ");
		sb.append("return (" + family.valCon.type
				+ ") super.getLatestValue(qual); ");
		sb.append("} ");
	}

	private static Set<String> getColumnTypes(Family family) {
		List<Value> columns = family.columns;
		if (columns == null) {
			return new HashSet<String>(0);
		}
		Set<String> colTypes = new HashSet<String>(columns.size());
		for (Value column : columns) {
			colTypes.add(column.type);
		}
		return colTypes;
	}

	private static void generateValueContainerAsGetter(StringBuilder sb,
			String colType) {
		sb.append("public " + colType + " getAs" + colType + "() { ");
		if (isPrimativeType(colType)) {
			sb.append("return ByteConvertUtility.get"
					+ StringUtils.capitalize(colType)
					+ "FromBytes(getBytes()); ");
		} else if (isEnum(colType)) {
			sb.append("return "
					+ colType
					+ ".valueOf(ByteConvertUtility.getStringFromBytes(getBytes())); ");
		} else {
			sb.append("return ByteConvertUtility.get" + colType
					+ "FromBytes(getBytes()); ");
		}
		sb.append("} ");
	}

	private static void generateValueSetter(StringBuilder sb, Value val,
			int filedsAmt) {
		sb.append("public void set" + StringUtils.capitalize(val.name) + "("
				+ val.type + " " + val.name + ") { ");
		if (filedsAmt <= 1) {
			sb.append("byte[] bytes = " + getToBytesString(val));
			sb.append("setBytes(bytes); ");
		} else {
			sb.append("byte[] bytes = getBytes(); ");
			sb.append("byte[] subBytes = " + getToBytesString(val));
			sb.append("ArrayUtility.replace(bytes, subBytes"
					+ getBeginAndEndIndexString(val) + "); ");
		}

		sb.append("} ");
	}

	private static void generateFamilyColumnSetter(StringBuilder sb,
			Family family, Value val) {
		String paramStr = getQualifierFieldWithoutColumnNameParameterString(
				family, true);
		String paramStrWithoutType = getQualifierFieldWithoutColumnNameParameterString(
				family, false);
		sb.append("public void set" + StringUtils.capitalize(val.name) + "(");
		if (EMPTY_STRING.equals(paramStr) == false) {
			sb.append(paramStr + ",");
		}
		sb.append("Date ver," + val.type + " " + val.name + ") { ");

		sb.append(family.qualCon.type + " qual = new " + family.qualCon.type
				+ "(");
		sb.append(getColumnNameString(val.name));
		if (EMPTY_STRING.equals(paramStrWithoutType) == false) {
			sb.append("," + paramStrWithoutType);
		}
		sb.append("); ");
		sb.append(family.valCon.type + " val = new " + family.valCon.type
				+ "(); ");
		sb.append("val.set(" + val.name + "); ");
		sb.append("add(qual, ver, val); ");
		sb.append("} ");
	}

	private static void generateFamilyValueSetter(StringBuilder sb,
			Family family) {
		String qualParamStr = getQualifierFieldWithoutColumnNameParameterString(
				family, true);
		String qualParamStrWithoutType = getQualifierFieldWithoutColumnNameParameterString(
				family, false);
		String valParamStr = getValueFieldParameterString(family, true);
		String valParamStrWithoutType = getValueFieldParameterString(family,
				false);
		sb.append("public void set" + family.valCon.type + "(" + qualParamStr
				+ ", Date ver, " + valParamStr + ") { ");
		sb.append("HBaseColumnQualifier qual = new " + family.qualCon.type
				+ "(" + qualParamStrWithoutType + "); ");
		sb.append(family.valCon.type + " val = new " + family.valCon.type + "("
				+ valParamStrWithoutType + "); ");
		sb.append("add(qual, ver, val); ");
		sb.append("} ");
	}

	private static boolean isPrimativeType(String type) {
		switch (type) {
		case "boolean":
		case "byte":
		case "char":
		case "double":
		case "float":
		case "int":
		case "long":
		case "short":
			return true;
		default:
			return false;
		}
	}

	private static boolean isEnum(String type) {
		switch (type) {
		case "String":
		case "Date":
		case "BigDecimal":
		case "Integer":
		case "BigInteger":
		case "int":
			return false;
		default:
			return true;
		}
	}

	private static void generateValueContainerAsSetter(StringBuilder sb,
			String colType) {
		if (isEnum(colType)) {
			sb.append("public void set(" + colType + " value) { ");
			sb.append("setBytes(ByteConvertUtility.toBytes(value.name())); ");
			sb.append("} ");

		} else {
			sb.append("public void set(" + colType + " value) { ");
			sb.append("setBytes(ByteConvertUtility.toBytes(value)); ");
			sb.append("} ");
		}
	}

	private static void generateRowKeySection(StringBuilder sb) {
		sb.append("public class " + rowKey.type + " extends HBaseRowKey { ");
		generateRowKeyContainerContent(sb, rowKey);
		sb.append("} ");
	}

	private static void generateRowKeyContainerContent(StringBuilder sb,
			Container con) {
		generateLengthSection(sb, con);
		generateIndexSection(sb, con);
		generateRowKeyConstructorsSection(sb, con);
		generateGetterSetterSection(sb, con);
	}

	private static void generateQualifierContainerContent(StringBuilder sb,
			Container con) {
		generateLengthSection(sb, con);
		generateIndexSection(sb, con);
		generateConstructorsSection(sb, con);
		generateGetterSetterSection(sb, con);
	}

	private static void generateValueContainerContent(StringBuilder sb,
			Container con) {
		generateLengthSection(sb, con);
		generateIndexSection(sb, con);
		generateConstructorsSection(sb, con);
		generateGetterSetterSection(sb, con);
		generateValueContainerAsGetterAsSetterSection(sb, con);
	}

	private static void generateColumnFamilyConstructorSection(
			StringBuilder sb, Family family) {
		sb.append("private " + family.type + "(" + tableName + " entity) { ");
		sb.append("super(entity); ");
		sb.append("} ");
	}

	private static void generateColumnNamesSection(StringBuilder sb,
			List<Value> columns) {
		if (columns == null) {
			return;
		}
		for (Value val : columns) {
			sb.append("public static final String "
					+ getColumnNameString(val.name) + " = \"" + val.name
					+ "\"; ");
		}
	}

	private static void generateColumnFamiliesSection(StringBuilder sb) {
		for (Family family : families) {
			sb.append("public class " + family.type
					+ " extends HBaseColumnFamily { ");
			generateColumnNamesSection(sb, family.columns);
			generateColumnFamilyConstructorSection(sb, family);
			sb.append("@SuppressWarnings(\"unchecked\") ");
			sb.append("public Set<" + family.qualCon.type
					+ "> getQualifiers() { ");
			sb.append("return (Set<"
					+ family.qualCon.type
					+ ">)(Object)super.getQualifierVersionValueMap().keySet(); ");
			sb.append("} ");
			generateFamilyGetterSetterSection(sb, family);
			sb.append("@Override ");
			sb.append("protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) { ");
			sb.append("return this.new " + family.qualCon.type + "(bytes); ");
			sb.append("} ");
			sb.append("@Override ");
			sb.append("protected HBaseValue generateValue(byte[] bytes) { ");
			sb.append("return this.new " + family.valCon.type + "(bytes); ");
			sb.append("} ");
			generateQualifierSection(sb, family.qualCon);
			generateValueSection(sb, family.valCon);
			sb.append("} ");
		}

	}

	private static void generateValueSection(StringBuilder sb, Container val) {
		sb.append("public class " + val.type + " extends HBaseValue { ");
		generateValueContainerContent(sb, val);
		sb.append("} ");
	}

	private static void generateQualifierSection(StringBuilder sb,
			Container qual) {
		sb.append("public class " + qual.type
				+ " extends HBaseColumnQualifier { ");
		generateQualifierContainerContent(sb, qual);
		sb.append("} ");
	}

	private static JsonNode getJson(File jsonfile) throws IOException {
		String jsonStr = FileUtils.readFileToString(jsonfile, "UTF-8");
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readTree(jsonStr);
	}

	static class Container {
		public Family family;
		public String type;
		public List<Value> values = new ArrayList<Value>();

		public Container(String type) {
			this.type = type;
		}

		public Container(Family family, String type) {
			this.family = family;
			this.type = type;
		}

		static class Value {
			public String type;
			public String name;
			public String length;

			public Value(String type, String name) {
				this.type = type;
				this.name = name;
			}

			public Value(String type, String name, String length) {
				this.type = type;
				this.name = name;
				this.length = length;
			}
		}
	}

	private static class Family {
		public String type;
		public List<Value> columns;
		public Container qualCon;
		public Container valCon;

		public Family(String type) {
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		File f = new File(
				"/home/hsiehpinghan/git/dao/stock-dao/src/test/entity-json/StockInfo.json");
		parseJson(f);
		// String classCode = getEntityClassCode();
		// System.err.println("entity : " + classCode);
		// String classTestCode = getEntityClassTestCode();
		// System.err.println("entity test : " + classTestCode);
		// String repositoryClassCode = getRepositoryClassCode();
		// System.err.println("repository : " + repositoryClassCode);
		String repositoryClassTestCode = getRepositoryClassTestCode();
		System.err.println("repository test : " + repositoryClassTestCode);
	}
}

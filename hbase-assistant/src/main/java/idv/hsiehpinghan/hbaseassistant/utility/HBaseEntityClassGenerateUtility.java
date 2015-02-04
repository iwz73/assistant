package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.datatypeutility.utility.StringUtility;
import idv.hsiehpinghan.hbaseassistant.utility.HBaseEntityClassGenerateUtility.Container.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;

public class HBaseEntityClassGenerateUtility {
	private static String table;
	private static Container rowKey;
	private static List<Family> families = new ArrayList<Family>();

	public static String getEntityClassCode(File jsonfile) throws IOException {
		JsonNode jsonNode = getJson(jsonfile);
		parseTable(jsonNode);
		return generateTableSection();
	}

	private static void parseTable(JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			table = ent.getKey();
			parseRowKeyAndColumnFamily(ent.getValue());
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

	private static void parseFamilyContent(Family family, JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		int i = 0;
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			Container con = new Container(ent.getKey());
			if (i == 0) {
				family.qualCon = con;
			} else if (i == 1) {
				family.valCon = con;
			}
			parseContainerContent(con, ent.getValue());
			++i;
		}
		if (i != 2) {
			throw new RuntimeException(
					"Qualifier and value must has only one for each.");
		}
	}

	private static String generateTableSection() {
		StringBuilder sb = new StringBuilder();
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
		sb.append("public class " + table + " extends HBaseTable { ");
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
		return sb.toString();
	}

	private static void generateFieldsSection(StringBuilder sb, Container con) {
		for (Value value : con.values) {
			sb.append("private " + value.type + " " + value.name + "; ");
		}
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
		if (con.values.size() == 1) {
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
		if (con.values.size() == 1) {
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

	private static void generateRowKeyConstructorsSection(StringBuilder sb,
			Container con) {
		sb.append("public " + con.type + "(" + table + " entity) { ");
		sb.append("super(entity); ");
		sb.append("} ");
		sb.append("public " + con.type + "(" + getParamsWithTypeString(con.values)
				+ ", " + table + " entity) { ");
		sb.append("super(entity); ");
		for (Value val : con.values) {
			sb.append("this." + val.name + " = " + val.name + "; ");
		}
		sb.append("} ");
		sb.append("public " + con.type + "(byte[] bytes, " + table
				+ " entity) { ");
		sb.append("super(entity); ");
		sb.append("fromBytes(bytes); ");
		sb.append("} ");
	}

	private static void generateConstructorsSection(StringBuilder sb,
			Container con) {
		sb.append("public " + con.type + "() { ");
		sb.append("super(); ");
		sb.append("} ");
		sb.append("public " + con.type + "(" + getParamsWithTypeString(con.values)
				+ ") { ");
		sb.append("super(); ");
		for (Value val : con.values) {
			sb.append("this." + val.name + " = " + val.name + "; ");
		}
		sb.append("} ");
		sb.append("public  " + con.type + "(byte[] bytes) { ");
		sb.append("super(); ");
		sb.append("fromBytes(bytes); ");
		sb.append("} ");
	}

	private static String getBeginAndEndIndexString(int filedsAmt, Value val) {
		if (filedsAmt > 1) {
			String beginIdxStr = getBeginIndexString(val.name);
			String endIdxStr = getEndIndexString(val.name);
			return ", " + beginIdxStr + ", " + endIdxStr;
		}
		return "";
	}

	private static void generateGetFromBytesString(StringBuilder sb,
			int filedsAmt, Value val) {
		if ("String".equals(val.type)) {
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getStringFromBytes(bytes"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ");
		} else if ("Date".equals(val.type)) {
			sb.append("try { ");
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getDateFromBytes(bytes"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ");
			sb.append("} catch (ParseException e) { ");
			sb.append("throw new RuntimeException(e); ");
			sb.append("} ");
		} else if ("BigDecimal".equals(val.type)) {
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getBigDecimalFromBytes(bytes"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ");
		} else if ("Integer".equals(val.type)) {
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getIntegerFromBytes(bytes"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ");
		} else if ("BigInteger".equals(val.type)) {
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getBigIntegerFromBytes(bytes"
					+ getBeginAndEndIndexString(filedsAmt, val) + "); ");
		} else {
			throw new RuntimeException("Type(" + val.type
					+ " not implements !!!");
		}
	}

	private static void generateToBytesFromBytesSection(StringBuilder sb,
			Container con) {
		List<Value> vals = con.values;
		// ToBytes.
		sb.append("@Override ");
		sb.append("public byte[] toBytes() { ");
		for (Value val : vals) {
			sb.append("byte[] " + val.name
					+ "Bytes = ByteConvertUtility.toBytes(" + val.name);
			if (val.length != null) {
				sb.append(", " + val.length);
			}
			sb.append("); ");
		}
		sb.append("return ArrayUtility.addAll( ");
		int i = 0;
		for (Value val : vals) {
			if (i > 0) {
				sb.append(", SPACE, ");
			}
			sb.append(val.name + "Bytes ");
			++i;
		}
		sb.append("); ");
		sb.append("} ");
		// From bytes.
		sb.append("@Override ");
		sb.append("public void fromBytes(byte[] bytes) { ");
		int filedsAmt = vals.size();
		for (Value val : vals) {
			generateGetFromBytesString(sb, filedsAmt, val);
		}
		sb.append("} ");
	}

	private static void generateGetterSetterSection(StringBuilder sb,
			Container con) {
		for (Value val : con.values) {
			sb.append("public " + val.type + " get"
					+ StringUtils.capitalize(val.name) + "() { ");
			sb.append("return " + val.name + "; ");
			sb.append("} ");
			sb.append("public void set" + StringUtils.capitalize(val.name)
					+ "(" + val.type + " " + val.name + ") { ");
			sb.append("this." + val.name + " = " + val.name + "; ");
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
		generateFieldsSection(sb, con);
		generateRowKeyConstructorsSection(sb, con);
		generateToBytesFromBytesSection(sb, con);
		generateGetterSetterSection(sb, con);
	}

	private static void generateContainerContent(StringBuilder sb, Container con) {
		generateLengthSection(sb, con);
		generateIndexSection(sb, con);
		generateFieldsSection(sb, con);
		generateConstructorsSection(sb, con);
		generateToBytesFromBytesSection(sb, con);
		generateGetterSetterSection(sb, con);
	}

	private static String getParamsString(List<Value> values) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0, size = values.size(); i < size; ++i) {
			Value val = values.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append(val.name);
		}
		return sb.toString();
	}
	
	private static void generateColumnFamiliesSection(StringBuilder sb) {
		for (Family family : families) {
			sb.append("public class " + family.type
					+ " extends HBaseColumnFamily { ");
			sb.append("private " + family.type + "(" + table + " entity) { ");
			sb.append("super(entity); ");
			sb.append("} ");

			sb.append("public " + family.valCon.type + " getLatestValue("
					+ getParamsWithTypeString(family.qualCon.values) + ") { ");
			sb.append(family.qualCon.type + " qual = this.new "
					+ family.qualCon.type + "( ");
			sb.append(getParamsString(family.qualCon.values));
			sb.append("); ");
			sb.append("return (" + family.valCon.type
					+ ") super.getLatestValue(qual); ");
			sb.append("} ");
			
			sb.append("public void add( ");
			sb.append(getParamsWithTypeString(family.qualCon.values));
			sb.append(", Date date, ");
			sb.append(getParamsWithTypeString(family.valCon.values));
			sb.append(") { ");
			sb.append("HBaseColumnQualifier qualifier = this.new " + family.qualCon.type + "( ");
			sb.append(getParamsString(family.qualCon.values));
			sb.append("); ");
			sb.append(family.valCon.type + " val = this.new " + family.valCon.type + "( ");
			sb.append(getParamsString(family.valCon.values));
			sb.append("); ");
			sb.append("add(qualifier, date, val); ");
			sb.append("} ");
			
			generateQualifierSection(sb, family.qualCon);
			generateValueSection(sb, family.valCon);
			sb.append("@Override ");
			sb.append("protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) { ");
			sb.append("return this.new " + family.qualCon.type + "(bytes); ");
			sb.append("} ");
			sb.append("@Override ");
			sb.append("protected HBaseValue generateValue(byte[] bytes) { ");
			sb.append("return this.new " + family.valCon.type + "(bytes); ");
			sb.append("} ");
			sb.append("} ");
		}

	}

	private static void generateValueSection(StringBuilder sb, Container val) {
		sb.append("public class " + val.type + " extends HBaseValue { ");
		generateContainerContent(sb, val);
		sb.append("} ");
	}

	private static void generateQualifierSection(StringBuilder sb,
			Container qual) {
		sb.append("public class " + qual.type
				+ " extends HBaseColumnQualifier { ");
		generateContainerContent(sb, qual);
		sb.append("} ");
	}

	private static JsonNode getJson(File jsonfile) throws IOException {
		String jsonStr = FileUtils.readFileToString(jsonfile, "UTF-8");
		ObjectMapper objMapper = new ObjectMapper();
		return objMapper.readTree(jsonStr);
	}

	static class Container {
		public String type;
		public List<Value> values = new ArrayList<Value>();

		public Container(String type) {
			this.type = type;
		}

		static class Value {
			public String type;
			public String name;
			public String length;

			public Value(String type, String name, String length) {
				this.type = type;
				this.name = name;
				this.length = length;
			}
		}
	}

	private static class Family {
		public String type;
		public Container qualCon;
		public Container valCon;

		public Family(String type) {
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		File f = new File(
				"/home/centos/git/dao/stock-dao/src/test/entity-json/CompanyBasicInfo.json");
		String str = getEntityClassCode(f);

		System.err.println(str);
	}
}

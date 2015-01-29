package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
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
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			String type = ent.getKey();
			String name = ent.getValue().get("name").getTextValue();
			JsonNode lenNode = ent.getValue().get("length");
			String length = null;
			if (lenNode != null) {
				length = lenNode.getTextValue();
			}
			con.values.add(new Value(type, name, length));
		}
	}

	private static void parseFamilyContent(Family family, JsonNode jsonNode) {
		Iterator<Map.Entry<String, JsonNode>> iter = jsonNode.getFields();
		while (iter.hasNext()) {
			Map.Entry<String, JsonNode> ent = iter.next();
			Container qualOrVal = new Container(ent.getKey());
			parseContainerContent(qualOrVal, ent.getValue());
			family.qualAndVal.add(qualOrVal);

		}
	}

	private static String generateTableSection() {
		StringBuilder sb = new StringBuilder();
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
				sb.append("private static final " + value.type + " "
						+ getLengthString(value.name) + " = " + value.length
						+ "; ");
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
					sb.append("private static final " + value.type + " "
							+ getBeginIndexString(value.name) + " = 0; ");
				} else {
					sb.append("private static final " + value.type + " "
							+ getBeginIndexString(value.name) + " = "
							+ beforeEndIndexString + " + 1; ");
				}
				beforeEndIndexString = getEndIndexString(value.name);
				sb.append("private static final " + value.type + " "
						+ beforeEndIndexString + " = "
						+ getBeginIndexString(value.name) + " + "
						+ getLengthString(value.name) + "; ");
				++i;
			}
		}
	}

	private static String getParamsString(List<Value> values) {
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

	private static void generateConstructorsSection(StringBuilder sb,
			Container con) {
		sb.append("public " + rowKey.type + "(" + table + " entity) { ");
		sb.append("super(entity); ");
		sb.append("} ");

		sb.append("public " + rowKey.type + "(" + getParamsString(con.values)
				+ ", " + table + " entity) { ");
		sb.append("super(entity); ");
		for (Value val : con.values) {
			sb.append("this." + val.name + " = " + val.name + "; ");
		}
		sb.append("} ");

		sb.append("public " + rowKey.type + "(byte[] rowKey, " + table
				+ " entity) { ");
		sb.append("super(entity); ");
		sb.append("fromBytes(rowKey); ");
		sb.append("} ");
	}

	private static void generateGetFromBytes(StringBuilder sb, Value val) {
		if ("String".equals(val.type)) {
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getStringFromBytes(bytes");
			String length = getLengthString(val.length);
			if(length != null) {
				sb.append(", OPENING_PRICE_BEGIN_INDEX, OPENING_PRICE_END_INDEX");
			}
			sb.append("); ");
		} else if ("Date".equals(val.type)) {
			sb.append("this." + val.name
					+ " = ByteConvertUtility.getDateFromBytes(bytes); ");
		} else {
			throw new RuntimeException("Type(" + val.type
					+ " not implements !!!");
		}
	}
	private static void generateToBytesFromBytesSection(StringBuilder sb,
			Container con) {
		List<Value> vals = con.values;
		if (vals.size() == 1) {
			Value val = vals.get(0);
			sb.append("@Override ");
			sb.append("public byte[] toBytes() { ");
			sb.append("return ByteConvertUtility.toBytes(" + val.name + "); ");
			sb.append("} ");
			sb.append("@Override ");
			sb.append("public void fromBytes(byte[] bytes) { ");
			if ("String".equals(val.type)) {
				sb.append("this." + val.name
						+ " = ByteConvertUtility.getStringFromBytes(bytes); ");
			} else if ("Date".equals(val.type)) {
				sb.append("this." + val.name
						+ " = ByteConvertUtility.getDateFromBytes(bytes); ");
			} else {
				throw new RuntimeException("Type(" + val.type
						+ " not implements !!!");
			}
			sb.append("} ");
		} else {
			// ToBytes.
			sb.append("@Override ");
			sb.append("public byte[] toBytes() { ");
			for (Value val : vals) {
				sb.append("byte[] " + val.name + "Bytes = ByteConvertUtility.toBytes(" + val.name + ", " + getLengthString(val.length) +"); ");
			}
			sb.append("return ArrayUtility.addAll( ");
			int i = 0;
			for (Value val : vals) {
				if(i > 0) {
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
			for (Value val : vals) {
				sb.append("this." + val.name + " = ByteConvertUtility.getBigDecimalFromBytes(bytes, OPENING_PRICE_BEGIN_INDEX, OPENING_PRICE_END_INDEX); ");
			}
			sb.append("} ");
		}
	}

	private static void generateGetterSetterSection(StringBuilder sb,
			Container con) {
		for (Value val : con.values) {
			sb.append("public String get" + StringUtils.capitalize(val.name)
					+ "() { ");
			sb.append("return " + val.name + "; ");
			sb.append("} ");
			sb.append("public void set" + StringUtils.capitalize(val.name)
					+ "(String " + val.name + ") { ");
			sb.append("this." + val.name + " = " + val.name + "; ");
			sb.append("} ");
		}
	}

	private static void generateRowKeySection(StringBuilder sb) {
		sb.append("public class " + rowKey.type + " extends HBaseRowKey { ");
		generateContainerContent(sb, rowKey);
		sb.append("} ");
	}

	private static void generateContainerContent(StringBuilder sb, Container con) {
		generateLengthSection(sb, con);
		generateIndexSection(sb, con);
		generateFieldsSection(sb, con);
		generateConstructorsSection(sb, con);
		generateToBytesFromBytesSection(sb, con);
		generateGetterSetterSection(sb, con);
	}

	private static void generateColumnFamiliesSection(StringBuilder sb) {

		for (Family family : families) {
			sb.append("public class " + family.type
					+ " extends HBaseColumnFamily { ");
			sb.append("private " + family.type + "(" + table + " entity) { ");
			sb.append("super(entity); ");
			sb.append("} ");
			generateQualAndValSection(sb, family);
			sb.append("} ");
		}

	}

	private static void generateQualAndValSection(StringBuilder sb,
			Family family) {
		sb.append("public class " + family.type
				+ " extends HBaseColumnQualifier { ");
		for (Container con : family.qualAndVal) {
			generateContainerContent(sb, con);
		}
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
		public List<Container> qualAndVal = new ArrayList<Container>();

		public Family(String type) {
			this.type = type;
		}
	}

	public static void main(String[] args) throws IOException {
		File f = new File("/home/centos/Desktop/f");
		String str = getEntityClassCode(f);

		System.err.println(str);

	}
}

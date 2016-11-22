package idv.hsiehpinghan.springbatchassistant.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import idv.hsiehpinghan.springbatchassistant.entity.JdbcEntity;

public class JdbcRowMapper implements RowMapper<JdbcEntity> {

	@Override
	public JdbcEntity mapRow(ResultSet rs, int arg1) throws SQLException {
		Long id = rs.getLong("id");
		boolean primativeBoolean = rs.getBoolean("primativeboolean");
		byte primativeByte = rs.getByte("primativebyte");
		double primativeDouble = rs.getDouble("primativedouble");
		float primativeFloat = rs.getFloat("primativefloat");
		int primativeInt = rs.getInt("primativeint");
		long primativeLong = rs.getLong("primativelong");
		short primativeShort = rs.getShort("primativeshort");
		String string = rs.getString("string");
		BigDecimal bigDecimal = rs.getBigDecimal("bigdecimal");
		java.sql.Date sqlDate = rs.getDate("sqldate");
		java.sql.Time sqlTime = rs.getTime("sqltime");
		java.sql.Timestamp sqlTimestamp = rs.getTimestamp("sqltimestamp");
		byte[] byteArray = rs.getBytes("bytearray");
		JdbcEntity entity = new JdbcEntity();
		entity.setId(id + 100);
		entity.setPrimativeBoolean(primativeBoolean);
		entity.setPrimativeByte(primativeByte);
		entity.setPrimativeDouble(primativeDouble);
		entity.setPrimativeFloat(primativeFloat);
		entity.setPrimativeInt(primativeInt);
		entity.setPrimativeLong(primativeLong);
		entity.setPrimativeShort(primativeShort);
		entity.setString(string);
		entity.setBigDecimal(bigDecimal);
		entity.setSqlDate(sqlDate);
		entity.setSqlTime(sqlTime);
		entity.setSqlTimestamp(sqlTimestamp);
		entity.setByteArray(byteArray);
		return entity;
	}

}

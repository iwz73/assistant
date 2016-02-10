package idv.hsiehpinghan.springjdbcassistant.repository;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.stereotype.Repository;

@Repository
public class BasicTypeRepository {
	@Autowired
	private DataSource dataSource;

	public int insertByAbstractLobCreatingPreparedStatementCallback(
			BasicTypeEntity entity, final long clobContentLength,
			final long blobContentLength) {
		final Long id = entity.getId();
		final boolean primativeBoolean = entity.isPrimativeBoolean();
		final byte primativeByte = entity.getPrimativeByte();
		final double primativeDouble = entity.getPrimativeDouble();
		final float primativeFloat = entity.getPrimativeFloat();
		final int primativeInt = entity.getPrimativeInt();
		final long primativeLong = entity.getPrimativeLong();
		final short primativeShort = entity.getPrimativeShort();
		final String string = entity.getString();
		final BigDecimal bigDecimal = entity.getBigDecimal();
		final java.sql.Date sqlDate = entity.getSqlDate();
		final java.sql.Time sqlTime = entity.getSqlTime();
		final java.sql.Timestamp sqlTimestamp = entity.getSqlTimestamp();
		final Reader clobReader = entity.getClobReader();
		final InputStream blobInputStream = entity.getBlobInputStream();
		final byte[] byteArray = entity.getByteArray();
		String sql = "INSERT INTO spring_jdbc_assistant.basictypeentity(id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, clob, blob, bytearray) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		DefaultLobHandler lobHandler = new DefaultLobHandler();
		return jdbcTemplate.execute(sql,
				new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
					@Override
					protected void setValues(PreparedStatement ps,
							LobCreator lobCreator) throws SQLException,
							DataAccessException {
						ps.setLong(1, id);
						ps.setBoolean(2, primativeBoolean);
						ps.setByte(3, primativeByte);
						ps.setDouble(4, primativeDouble);
						ps.setFloat(5, primativeFloat);
						ps.setInt(6, primativeInt);
						ps.setLong(7, primativeLong);
						ps.setShort(8, primativeShort);
						ps.setString(9, string);
						ps.setBigDecimal(10, bigDecimal);
						ps.setDate(11, sqlDate);
						ps.setTime(12, sqlTime);
						ps.setTimestamp(13, sqlTimestamp);
						lobCreator.setClobAsCharacterStream(ps, 14, clobReader,
								(int) clobContentLength);
						lobCreator.setBlobAsBinaryStream(ps, 15,
								blobInputStream, (int) blobContentLength);
						ps.setBytes(16, byteArray);
					}
				});
	}

	// public int insertByPreparedStatement(BasicTypeEntity entity) {
	// final Long id = entity.getId();
	// final boolean primativeBoolean = entity.isPrimativeBoolean();
	// final byte primativeByte = entity.getPrimativeByte();
	// final double primativeDouble = entity.getPrimativeDouble();
	// final float primativeFloat = entity.getPrimativeFloat();
	// final int primativeInt = entity.getPrimativeInt();
	// final long primativeLong = entity.getPrimativeLong();
	// final short primativeShort = entity.getPrimativeShort();
	// final String string = entity.getString();
	// final BigDecimal bigDecimal = entity.getBigDecimal();
	// final java.sql.Date sqlDate = entity.getSqlDate();
	// final java.sql.Time sqlTime = entity.getSqlTime();
	// final java.sql.Timestamp sqlTimestamp = entity.getSqlTimestamp();
	// // final java.sql.Clob clob = entity.getClob();
	// // final java.sql.Blob blob = entity.getBlob();
	// final byte[] byteArray = entity.getByteArray();
	// JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	//
	// return jdbcTemplate.update(new PreparedStatementCreator() {
	// public PreparedStatement createPreparedStatement(Connection con)
	// throws SQLException {
	// String sql =
	// "INSERT INTO spring_jdbc_assistant.basictypeentity(id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, clob, blob, bytearray) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?);";
	// PreparedStatement ps = con.prepareStatement(sql);
	// ps.setLong(1, id);
	// ps.setBoolean(2, primativeBoolean);
	// ps.setByte(3, primativeByte);
	// ps.setDouble(4, primativeDouble);
	// ps.setFloat(5, primativeFloat);
	// ps.setInt(6, primativeInt);
	// ps.setLong(7, primativeLong);
	// ps.setShort(8, primativeShort);
	// ps.setString(9, string);
	// ps.setBigDecimal(10, bigDecimal);
	// ps.setDate(11, sqlDate);
	// ps.setTime(12, sqlTime);
	// ps.setTimestamp(13, sqlTimestamp);
	// // ps.setClob(14, clob);
	// // ps.setBlob(15, blob);
	// ps.setBytes(16, byteArray);
	// return ps;
	// }
	// });
	// }
	//
	// public BasicTypeEntity queryForObjectByRowMapper(long id) {
	// JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	// String sql =
	// "SELECT id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, clob, blob, bytearray FROM spring_jdbc_assistant.basictypeentity where id = ?;";
	// Object[] args = new Object[] { id };
	// return jdbcTemplate.queryForObject(sql, args,
	// new RowMapper<BasicTypeEntity>() {
	// public BasicTypeEntity mapRow(ResultSet rs, int paramInt)
	// throws SQLException {
	// Long id = rs.getLong("id");
	// boolean primativeBoolean = rs
	// .getBoolean("primativeboolean");
	// byte primativeByte = rs.getByte("primativebyte");
	// double primativeDouble = rs
	// .getDouble("primativedouble");
	// float primativeFloat = rs.getFloat("primativefloat");
	// int primativeInt = rs.getInt("primativeint");
	// long primativeLong = rs.getLong("primativelong");
	// short primativeShort = rs.getShort("primativeshort");
	// String string = rs.getString("string");
	// BigDecimal bigDecimal = rs.getBigDecimal("bigdecimal");
	// java.sql.Date sqlDate = rs.getDate("sqldate");
	// java.sql.Time sqlTime = rs.getTime("sqltime");
	// java.sql.Timestamp sqlTimestamp = rs
	// .getTimestamp("sqltimestamp");
	// // java.sql.Clob clob = rs.getClob("clob");
	// // java.sql.Blob blob = rs.getBlob("blob");
	// byte[] byteArray = rs.getBytes("bytearray");
	// BasicTypeEntity entity = new BasicTypeEntity();
	// entity.setId(id);
	// entity.setPrimativeBoolean(primativeBoolean);
	// entity.setPrimativeByte(primativeByte);
	// entity.setPrimativeDouble(primativeDouble);
	// entity.setPrimativeFloat(primativeFloat);
	// entity.setPrimativeInt(primativeInt);
	// entity.setPrimativeLong(primativeLong);
	// entity.setPrimativeShort(primativeShort);
	// entity.setString(string);
	// entity.setBigDecimal(bigDecimal);
	// entity.setSqlDate(sqlDate);
	// entity.setSqlTime(sqlTime);
	// entity.setSqlTimestamp(sqlTimestamp);
	// // entity.setClob(clob);
	// // entity.setBlob(blob);
	// entity.setByteArray(byteArray);
	// return entity;
	// }
	// });
	// }
}

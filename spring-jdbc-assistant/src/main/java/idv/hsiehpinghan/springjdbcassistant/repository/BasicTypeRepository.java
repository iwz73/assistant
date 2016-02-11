package idv.hsiehpinghan.springjdbcassistant.repository;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BasicTypeRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insertByPreparedStatementCreator(BasicTypeEntity entity) {
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
		final byte[] byteArray = entity.getByteArray();

		return jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "INSERT INTO spring_jdbc_assistant.basictypeentity(id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, bytearray) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement ps = con.prepareStatement(sql);
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
				ps.setBytes(14, byteArray);
				return ps;
			}
		});
	}

	public BasicTypeEntity queryForObjectByRowMapper(long id) {
		String sql = "SELECT id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, bytearray FROM spring_jdbc_assistant.basictypeentity where id = ?;";
		Object[] args = new Object[] { id };
		return jdbcTemplate.queryForObject(sql, args,
				new RowMapper<BasicTypeEntity>() {
					public BasicTypeEntity mapRow(ResultSet rs, int paramInt)
							throws SQLException {
						Long id = rs.getLong("id");
						boolean primativeBoolean = rs
								.getBoolean("primativeboolean");
						byte primativeByte = rs.getByte("primativebyte");
						double primativeDouble = rs
								.getDouble("primativedouble");
						float primativeFloat = rs.getFloat("primativefloat");
						int primativeInt = rs.getInt("primativeint");
						long primativeLong = rs.getLong("primativelong");
						short primativeShort = rs.getShort("primativeshort");
						String string = rs.getString("string");
						BigDecimal bigDecimal = rs.getBigDecimal("bigdecimal");
						java.sql.Date sqlDate = rs.getDate("sqldate");
						java.sql.Time sqlTime = rs.getTime("sqltime");
						java.sql.Timestamp sqlTimestamp = rs
								.getTimestamp("sqltimestamp");
						byte[] byteArray = rs.getBytes("bytearray");
						BasicTypeEntity entity = new BasicTypeEntity();
						entity.setId(id);
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
				});
	}

	public int updateByPreparedStatementCreator(BasicTypeEntity entity) {
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
		final byte[] byteArray = entity.getByteArray();
		return jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "UPDATE spring_jdbc_assistant.basictypeentity SET primativeboolean=?, primativebyte=?, primativedouble=?, primativefloat=?, primativeint=?, primativelong=?, primativeshort=?, string=?, bigdecimal=?, sqldate=?, sqltime=?, sqltimestamp=?, bytearray=? WHERE id=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setBoolean(1, primativeBoolean);
				ps.setByte(2, primativeByte);
				ps.setDouble(3, primativeDouble);
				ps.setFloat(4, primativeFloat);
				ps.setInt(5, primativeInt);
				ps.setLong(6, primativeLong);
				ps.setShort(7, primativeShort);
				ps.setString(8, string);
				ps.setBigDecimal(9, bigDecimal);
				ps.setDate(10, sqlDate);
				ps.setTime(11, sqlTime);
				ps.setTimestamp(12, sqlTimestamp);
				ps.setBytes(13, byteArray);
				ps.setLong(14, id);
				return ps;
			}
		});
	}

	public void deleteByPreparedStatementCreator(final Long id) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "DELETE FROM spring_jdbc_assistant.basictypeentity WHERE id=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1, id);
				return ps;
			}
		});
	}

	public boolean exists(long id) {
		String sql = "SELECT COUNT(1) FROM spring_jdbc_assistant.basictypeentity WHERE id=?;";
		Object[] args = new Object[] { id };
		int amt = jdbcTemplate.queryForObject(sql, Integer.class, args);
		return amt > 0;
	}
}

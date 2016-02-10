package idv.hsiehpinghan.springjdbcassistant.repository;

import idv.hsiehpinghan.springjdbcassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.streamutility.utility.InputStreamUtility;
import idv.hsiehpinghan.streamutility.utility.ReaderUtility;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

@Repository
public class BasicTypeRepository {
	@Autowired
	private DataSource dataSource;

	public int insertByPreparedStatement(BasicTypeEntity entity) {
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
		final char[] clobCharArray = entity.getClobCharArray();
		final byte[] blobByteArray = entity.getBlobByteArray();
		final byte[] byteArray = entity.getByteArray();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "INSERT INTO spring_jdbc_assistant.basictypeentity(id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, clob, blob, bytearray) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
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
				ps.setClob(14, new SerialClob(clobCharArray));
				ps.setBlob(15, new SerialBlob(blobByteArray));
				ps.setBytes(16, byteArray);
				return ps;
			}
		});
	}

	public BasicTypeEntity queryForObjectByRowMapper(long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, clob, blob, bytearray FROM spring_jdbc_assistant.basictypeentity where id = ?;";
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
						LobHandler lobHandler = new DefaultLobHandler(); 
						Reader reader = lobHandler.getClobAsCharacterStream(rs, "clob");
						InputStream inputStream = lobHandler.getBlobAsBinaryStream(rs, "blob");
//						java.sql.Clob clob = rs.getClob("clob");
//						java.sql.Blob blob = rs.getBlob("blob");
						

						try {
							System.err.println(ReaderUtility.readAsString(reader));
							System.err.println(InputStreamUtility.readAsString(inputStream));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
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
//						try {
//							char[] clobCharArray = ReaderUtility
//									.readAsCharArray(clob.getCharacterStream(),
//											(int) clob.length());
//							entity.setClobCharArray(clobCharArray);
//						} catch (IOException e) {
//							throw new RuntimeException(e);
//						}
//						try {
//							byte[] blobByteArray = InputStreamUtility
//									.readAsByteArray(blob.getBinaryStream(),
//											(int) blob.length());
//							entity.setBlobByteArray(blobByteArray);
//						} catch (IOException e) {
//							throw new RuntimeException(e);
//						}
						entity.setByteArray(byteArray);
						return entity;
					}
				});
	}
}

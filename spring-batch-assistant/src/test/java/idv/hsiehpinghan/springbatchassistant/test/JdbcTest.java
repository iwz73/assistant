package idv.hsiehpinghan.springbatchassistant.test;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springbatchassistant.entity.JdbcEntity;
import idv.hsiehpinghan.springbatchassistant.service.JdbcService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JdbcTest extends AbstractTestNGSpringContextTests {
	private boolean primativeBoolean = true;
	private byte primativeByte = 0x1;
	private double primativeDouble = 1.1;
	private float primativeFloat = 1.1f;
	private int primativeInt = 1;
	private long primativeLong = 1L;
	private short primativeShort = 1;
	private String string = "string";
	private BigDecimal bigDecimal = BigDecimal.ONE;
	private java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
	private java.sql.Time sqlTime = new java.sql.Time(Calendar.getInstance().getTimeInMillis());
	private java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	private byte[] byteArray = getByteArray();
	@Autowired
	private JdbcService service;
	@Autowired
	private DataSource dataSource;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("jdbcJob")
	private Job job;

	@BeforeClass
	public void beforeClass() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:/script/postgresql.sql");
		ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
		for (long i = 0; i < 3; ++i) {
			JdbcEntity entity = generateJdbcEntity(i);
			service.insertByPreparedStatementCreator(entity);
		}
	}

	@Test
	public void test() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("sql", "SELECT id, primativeboolean, primativebyte, primativedouble, primativefloat, primativeint, primativelong, primativeshort, string, bigdecimal, sqldate, sqltime, sqltimestamp, bytearray FROM spring_batch_assistant.jdbcentity");
		jobParametersBuilder.addLong("maxRows", 10L);
		jobParametersBuilder.addLong("fetchSize", 1L);
		
		jobParametersBuilder.addString("encoding", "utf-8");
		jobParametersBuilder.addLong("linesToSkip", 1L);
		jobParametersBuilder.addString("strict", "true");
		jobParametersBuilder.addString("inputResource",
				"/home/thank/git/assistant/spring-batch-assistant/src/test/data/jdbc");
		jobParametersBuilder.addString("comments", "!,#");
		jobParametersBuilder.addString("appendAllowed", "false");
		jobParametersBuilder.addString("lineSeparator", System.lineSeparator());
		jobParametersBuilder.addString("saveState", "true");
		jobParametersBuilder.addString("shouldDeleteIfEmpty", "false");
		jobParametersBuilder.addString("shouldDeleteIfExists", "true");
		jobParametersBuilder.addString("transactional", "true");
		jobParametersBuilder.addString("outputResource", "/tmp/jdbc");
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(), ExitStatus.COMPLETED.getExitCode());
	}

	private JdbcEntity generateJdbcEntity(Long id) {
		JdbcEntity entity = new JdbcEntity();
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

	private byte[] getByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}
}

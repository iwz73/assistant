package idv.hsiehpinghan.hbaseassistant.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HbaseAssistantProperty implements InitializingBean {
	private String tableOperation;

	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		String pTableOperation = "hbase_assistant_table_operation";
		tableOperation = environment.getProperty(pTableOperation);
		if (tableOperation == null) {
			throw new RuntimeException(pTableOperation + " not set !!!");
		}
	}

	public String getTableOperation() {
		return tableOperation;
	}

}

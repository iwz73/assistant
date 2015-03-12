package idv.hsiehpinghan.pigassistant.assistant;

import java.io.File;
import java.io.IOException;

import org.apache.pig.PigServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PigAssistant {
	@Autowired
	private PigServer pigServer;

	public void downloadHbaseData(File targetDirectory) throws IOException {
//		pigServer
//				.registerQuery("result = load 'hbase://sample_names' using org.apache.pig.backend.hadoop.hbase.HBaseStorage('info:fname info:lname', '-loadKey true') as (id, fname: chararray,lname: chararray);");
		pigServer
		.registerQuery("result = load 'hbase://Xbrl' using org.apache.pig.backend.hadoop.hbase.HBaseStorage('ratioDifferenceFamily:*', '-loadKey true') as (id, ratioDifferenceFamily:map[]);");

		pigServer.store("result", targetDirectory.getAbsolutePath());
	}

	// public static void main(String[] args) throws Exception {
	// // Properties props = new Properties();
	// // props.setProperty("fs.default.name", "hdfs://localhost:8020");
	// // props.setProperty("mapred.job.tracker",
	// "<jobtracker-hostname>:<port>");
	// // PigServer pigServer = new PigServer(ExecType.MAPREDUCE, props);
	// // pigServer.registerScript("/test.pig");
	//
	// // PigServer pigServer = new PigServer(ExecType.LOCAL);
	// // runMyQuery(pigServer);
	// // storeIntoHbase(pigServer);
	// loadFromHbase(pigServer);
	// }

	// public static void runMyQuery(PigServer pigServer) throws IOException {
	// // pigServer.registerQuery("cd Desktop;");
	// pigServer.registerQuery("A = load '/tmp/passwd';");
	// // pigServer.registerQuery("store A into 'pig_result';");
	// // pigServer.registerQuery("C = group B by $1;");
	// //
	// pigServer.registerQuery("D = foreach C generate flatten(group), COUNT(B.$0);");
	//
	// pigServer.store("A", "/tmp/passwd_out");
	// }

	// public static void storeIntoHbase(PigServer pigServer) throws IOException
	// {
	// pigServer.registerQuery("pigTestData = LOAD '/tmp/pigTestData' USING PigStorage(',') AS (id: chararray, fname: chararray,lname: chararray );");
	// pigServer.registerQuery("STORE pigTestData INTO 'hbase://sample_names' USING org.apache.pig.backend.hadoop.hbase.HBaseStorage ('info:fname info:lname');");
	// System.err.println("storeIntoHbase done!!!");
	// }

	// public static void loadFromHbase(PigServer pigServer) throws IOException
	// {
	// pigServer.registerQuery("pigHbaseData = load 'hbase://sample_names' using org.apache.pig.backend.hadoop.hbase.HBaseStorage('info:fname info:lname', '-loadKey true') as (id, fname: chararray,lname: chararray);");
	// pigServer.store("pigHbaseData", "/tmp/output");
	// System.err.println("loadFromHbase done!!!");
	// }
}

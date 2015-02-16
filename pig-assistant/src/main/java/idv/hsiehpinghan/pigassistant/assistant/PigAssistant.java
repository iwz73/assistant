package idv.hsiehpinghan.pigassistant.assistant;

import java.io.IOException;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;

public class PigAssistant {
    public static void main(String[] args) throws Exception {
//    	Properties props = new Properties();
//    	props.setProperty("fs.default.name", "hdfs://localhost:8020");
//    	props.setProperty("mapred.job.tracker", "<jobtracker-hostname>:<port>");
//    	PigServer pigServer = new PigServer(ExecType.MAPREDUCE, props);
//    	pigServer.registerScript("/test.pig");
    	
    	PigServer pigServer = new PigServer(ExecType.LOCAL);
    	runMyQuery(pigServer);
    }
    
    public static void runMyQuery(PigServer pigServer) throws IOException {  
//        pigServer.registerQuery("cd Desktop;");
        pigServer.registerQuery("A = load '/tmp/passwd';");
//        pigServer.registerQuery("store A into 'pig_result';");
//        pigServer.registerQuery("C = group B by $1;");
//        pigServer.registerQuery("D = foreach C generate flatten(group), COUNT(B.$0);");
       
        pigServer.store("A", "/tmp/passwd_out");
        
        System.err.println("done!!!");
    }
}

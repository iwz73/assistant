package idv.hsiehpinghan.rassistant.assistant;

import java.io.File;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Rengine rengine;

	public synchronized REXP runScript(File script, File logFile) {
		try {
			if (rengine.waitForR() == false) {
				throw new RuntimeException("Wait for R fail !!!");
			}
			if (logFile != null) {
				rengine.eval("sink('" + logFile.getAbsolutePath() + "')");
			}
			REXP rexp = rengine.eval("source('" + script.getAbsolutePath()
					+ "')");
			return rexp;
		} finally {
			if (rengine != null) {
				if (logFile != null) {
					rengine.eval("sink()");
				}
				rengine.end();
			}
		}
	}
}
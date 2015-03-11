package idv.hsiehpinghan.rassistant.assistant;

import java.io.File;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.springframework.stereotype.Component;

@Component
public class RAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	public synchronized boolean runScript(File scriptFile) {
		if (Rengine.versionCheck() == false) {
			throw new RuntimeException("Version check fail !!!");
		}
		Rengine engine = new Rengine(new String[] { "--save" }, false, null);
		try {
			if (engine.waitForR() == false) {
				throw new RuntimeException("Wait for R fail !!!");
			}
			REXP rexp = engine.eval("source('" + scriptFile.getAbsolutePath()
					+ "')");
			if (rexp == null) {
				return false;
			}
			return true;
		} finally {
			if (engine != null) {
				engine.end();
			}
		}
	}
}
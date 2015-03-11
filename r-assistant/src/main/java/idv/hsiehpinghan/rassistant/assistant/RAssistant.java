package idv.hsiehpinghan.rassistant.assistant;

import java.io.File;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;
import org.springframework.stereotype.Component;

@Component
public class RAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	private volatile Rengine rengine;

	public REXP runScript(File scriptFile, File logFile) {
		Rengine engine = getRengine();
		try {
			if (engine.waitForR() == false) {
				throw new RuntimeException("Wait for R fail !!!");
			}
			if (logFile != null) {
				engine.eval("sink('" + logFile.getAbsolutePath() + "')");
			}
			REXP rexp = engine.eval("source('" + scriptFile.getAbsolutePath()
					+ "')");
			return rexp;
		} finally {
			if (engine != null) {
				if (logFile != null) {
					engine.eval("sink()");
				}
				engine.end();
			}
		}
	}

	public Rengine getRengine() {
		if (rengine == null) {
			synchronized (this) {
				if (rengine == null) {
					if (Rengine.versionCheck() == false) {
						throw new RuntimeException("Version check fail !!!");
					}
					rengine = new Rengine(new String[] { "--save" }, false,
							null);
				}
			}
		}
		return rengine;
	}
}
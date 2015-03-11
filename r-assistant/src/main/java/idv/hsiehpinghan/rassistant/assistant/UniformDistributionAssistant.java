package idv.hsiehpinghan.rassistant.assistant;

import org.rosuda.JRI.Rengine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniformDistributionAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Rengine rengine;

	public double getDensity(int quantile, int min, int max) {
		rengine.eval("result <- dunif(" + quantile + ", min = " + min
				+ ", max = " + max + ")");
		return rengine.eval("result").asDouble();
	}

	public double getDistribution(int quantile, int min, int max) {
		rengine.eval("result <- punif(" + quantile + ", min = " + min
				+ ", max = " + max + ")");
		return rengine.eval("result").asDouble();
	}

}
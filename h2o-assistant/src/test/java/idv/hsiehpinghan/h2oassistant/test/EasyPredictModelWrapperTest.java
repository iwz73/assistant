package idv.hsiehpinghan.h2oassistant.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.prediction.BinomialModelPrediction;
import idv.hsiehpinghan.h2oassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class EasyPredictModelWrapperTest extends AbstractTestNGSpringContextTests {
	/**
	 * run
	 * /home/hsiehpinghan/git/python/h2o-python/notebook/estimators/gbm/H2OGradientBoostingEstimator.ipynb
	 */
	private static final String PREDICT_BINOMIAL_MODEL_PATH = "/home/hsiehpinghan/git/assistant/h2o-assistant/model/GBM_model_python_1536301120018_1.zip";

	@Test
	public void predictBinomial() throws Exception {
		EasyPredictModelWrapper model = new EasyPredictModelWrapper(MojoModel.load(PREDICT_BINOMIAL_MODEL_PATH));
		RowData row = new RowData();
		row.put("AGE", "68");
		row.put("RACE", "2");
		row.put("DCAPS", "2");
		row.put("VOL", "0");
		row.put("GLEASON", "6");
		BinomialModelPrediction p = model.predictBinomial(row);
		System.err.println("prediction lable (1=yes; 0=no): " + p.label);
		System.err.print("probabilities: ");
		for (int i = 0; i < p.classProbabilities.length; i++) {
			if (i > 0) {
				System.err.print(",");
			}
			System.err.print(p.classProbabilities[i]);
		}
		System.err.println("");
	}
}

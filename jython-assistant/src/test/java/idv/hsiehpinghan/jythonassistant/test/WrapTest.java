package idv.hsiehpinghan.jythonassistant.test;

import org.python.util.PythonInterpreter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jythonassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jythonassistant.module.ExecImportModule;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class WrapTest extends AbstractTestNGSpringContextTests {

	@Test
	public void wrap() {
		final int FUNCTION_PARAMETER = 5;
		final int CLASS_PARAMETER = 3;
		try (PythonInterpreter pythonInterpreter = new PythonInterpreter()) {
			ExecImportModule module = new ExecImportModule(pythonInterpreter, CLASS_PARAMETER);
			Assert.assertEquals(module.function_1(FUNCTION_PARAMETER), FUNCTION_PARAMETER);
			Assert.assertEquals(module.getExecImportClass().function_0(), CLASS_PARAMETER);
		}
	}

}

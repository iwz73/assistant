package idv.hsiehpinghan.jythonassistant.module;

import org.python.core.PyFunction;
import org.python.core.PyInstance;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;

public class ExecImportModule {
	private PythonInterpreter pythonInterpreter;
	private PyFunction function_1;
	private ExecImportClass execImportClass;

	public ExecImportModule(PythonInterpreter pythonInterpreter, int parameter) {
		this.pythonInterpreter = pythonInterpreter;
		pythonInterpreter.exec("from execImportModule.execImport import function_1");
		function_1 = pythonInterpreter.get("function_1", PyFunction.class);
		execImportClass = new ExecImportClass(parameter);
	}

	public int function_1(int parameter) {
		PyInteger result = (PyInteger) function_1.__call__(new PyInteger(parameter));
		return result.asInt();
	}

	public ExecImportClass getExecImportClass() {
		return execImportClass;
	}

	public class ExecImportClass {
		private PyInteger function_0;

		public ExecImportClass(int parameter) {
			pythonInterpreter.exec("from execImportModule.execImport import execImportClass");
			pythonInterpreter.exec("execImportClass = execImportClass(" + parameter + ")");
			PyInstance execImportClass = pythonInterpreter.get("execImportClass", PyInstance.class);
			function_0 = (PyInteger) execImportClass.invoke("function_0");
		}

		public int function_0() {
			return function_0.asInt();
		}
	}
}

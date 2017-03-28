package idv.hsiehpinghan.jythonassistant.test;

import org.python.core.PyArray;
import org.python.core.PyBoolean;
import org.python.core.PyByteArray;
import org.python.core.PyFloat;
import org.python.core.PyFunction;
import org.python.core.PyInstance;
import org.python.core.PyInteger;
import org.python.core.PyLong;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jythonassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicTest extends AbstractTestNGSpringContextTests {
	private final String PRIMATIVE_CHAR_VALUE_NAME = "primativeCharValue";
	private final char PRIMATIVE_CHAR_VALUE = 'a';
	private final String PRIMATIVE_BOOLEAN_VALUE_NAME = "primativeBooleanValue";
	private final boolean PRIMATIVE_BOOLEAN_VALUE = true;
	private final String PRIMATIVE_INT_VALUE_NAME = "primativeIntValue";
	private final int PRIMATIVE_INT_VALUE = 1;
	private final String PRIMATIVE_LONG_VALUE_NAME = "primativeLongValue";
	private final long PRIMATIVE_LONG_VALUE = 1;
	private final String STRING_VALUE_NAME = "stringValue";
	private final String STRING_VALUE = "STRING_VALUE";
	private final String BYTE_ARRAY_VALUE_NAME = "byteArrayValue";
	private final byte[] BYTE_ARRAY_VALUE = new byte[] { 'a', 'b', 'c' };
	private final String OBJECT_ARRAY_VALUE_NAME = "objectArrayValue";
	private final String[] OBJECT_ARRAY_VALUE = new String[] { new String("string_0"), new String("string_1"),
			new String("string_2") };
	private final String FILE_NAME = "src/test/resources/execfile.py";

	@Test
	public void type() {
		try (PythonInterpreter interpreter = new PythonInterpreter()) {
			testPrimativeBoolean(interpreter);
			testPrimativeChar(interpreter);
			testPrimativeInt(interpreter);
			testPrimativeLong(interpreter);
			testString(interpreter);
			testByteArray(interpreter);
			testObjectArray(interpreter);
		}
	}

	@Test
	public void exec() {
		try (PythonInterpreter interpreter = new PythonInterpreter()) {
			execBasic(interpreter);
			execImport(interpreter);
		}
	}

	@Test
	public void execfile() {
		try (PythonInterpreter interpreter = new PythonInterpreter()) {
			interpreter.execfile(FILE_NAME);
			PyFloat now = interpreter.get("now", PyFloat.class);
			System.err.println("execfile now : " + now);
		}
	}

	private void execImport(PythonInterpreter interpreter) {
		pyInstance(interpreter);
		pyFunction(interpreter);
	}

	private void pyInstance(PythonInterpreter interpreter) {
		int param = 3;
		interpreter.exec("from execImportModule.execImport import execImportClass");
		interpreter.exec("obj = execImportClass(" + param + ")");
		PyInstance pyInstance = interpreter.get("obj", PyInstance.class);
		PyInteger pyInteger = (PyInteger) pyInstance.invoke("function_0");
		Assert.assertEquals(pyInteger.asInt(), param);
	}

	private void pyFunction(PythonInterpreter interpreter) {
		int param = 5;
		interpreter.exec("from execImportModule.execImport import function_1");
		PyFunction pyFunction = (PyFunction) interpreter.get("function_1");
		PyInteger pyInteger = (PyInteger) pyFunction.__call__(new PyInteger(param));
		Assert.assertEquals(pyInteger.asInt(), param);
	}

	private void execBasic(PythonInterpreter interpreter) {
		interpreter.exec("import time");
		interpreter.exec("now = time.time()");
		PyFloat now = interpreter.get("now", PyFloat.class);
		System.err.println("execBasic now : " + now);
	}

	private void testObjectArray(PythonInterpreter interpreter) {
		PyArray pyArray = new PyArray(BasicTest.class, OBJECT_ARRAY_VALUE);
		interpreter.set(OBJECT_ARRAY_VALUE_NAME, pyArray);
		PyArray objectArrayValue = interpreter.get(OBJECT_ARRAY_VALUE_NAME, PyArray.class);
		Assert.assertTrue(objectArrayValueEqual((String[]) objectArrayValue.getArray()));
	}

	private void testByteArray(PythonInterpreter interpreter) {
		interpreter.set(BYTE_ARRAY_VALUE_NAME, new PyByteArray(BYTE_ARRAY_VALUE));
		PyByteArray byteArrayValue = interpreter.get(BYTE_ARRAY_VALUE_NAME, PyByteArray.class);
		Assert.assertTrue(byteArrayValueEqual(byteArrayValue.toArray()));
	}

	private void testString(PythonInterpreter interpreter) {
		interpreter.set(STRING_VALUE_NAME, new PyString(STRING_VALUE));
		PyString stringValue = interpreter.get(STRING_VALUE_NAME, PyString.class);
		Assert.assertEquals(stringValue.asString(), STRING_VALUE);
	}

	private void testPrimativeLong(PythonInterpreter interpreter) {
		interpreter.set(PRIMATIVE_LONG_VALUE_NAME, new PyLong(PRIMATIVE_LONG_VALUE));
		PyLong primativeLongValue = interpreter.get(PRIMATIVE_LONG_VALUE_NAME, PyLong.class);
		Assert.assertEquals(primativeLongValue.asLong(), PRIMATIVE_LONG_VALUE);
	}

	private void testPrimativeInt(PythonInterpreter interpreter) {
		interpreter.set(PRIMATIVE_INT_VALUE_NAME, new PyInteger(PRIMATIVE_INT_VALUE));
		PyInteger primativeIntValue = interpreter.get(PRIMATIVE_INT_VALUE_NAME, PyInteger.class);
		Assert.assertEquals(primativeIntValue.asInt(), PRIMATIVE_INT_VALUE);
	}

	private void testPrimativeChar(PythonInterpreter interpreter) {
		interpreter.set(PRIMATIVE_CHAR_VALUE_NAME, new PyString(PRIMATIVE_CHAR_VALUE));
		PyString primativeCharValue = interpreter.get(PRIMATIVE_CHAR_VALUE_NAME, PyString.class);
		Assert.assertEquals(primativeCharValue.asString(), String.valueOf(PRIMATIVE_CHAR_VALUE));
	}

	private void testPrimativeBoolean(PythonInterpreter interpreter) {
		interpreter.set(PRIMATIVE_BOOLEAN_VALUE_NAME, new PyBoolean(PRIMATIVE_BOOLEAN_VALUE));
		PyBoolean primativeBooleanValue = interpreter.get(PRIMATIVE_BOOLEAN_VALUE_NAME, PyBoolean.class);
		Assert.assertEquals(primativeBooleanValue.getBooleanValue(), PRIMATIVE_BOOLEAN_VALUE);
	}

	private boolean byteArrayValueEqual(Object[] byteArrayValue) {
		int size = byteArrayValue.length;
		if (size != BYTE_ARRAY_VALUE.length) {
			return false;
		}
		for (int i = 0; i < size; ++i) {
			if (((PyInteger) byteArrayValue[i]).asInt() != BYTE_ARRAY_VALUE[i]) {
				return false;
			}
		}
		return true;
	}

	private boolean objectArrayValueEqual(String[] objectArrayValue) {
		int size = objectArrayValue.length;
		if (size != OBJECT_ARRAY_VALUE.length) {
			return false;
		}
		for (int i = 0; i < size; ++i) {
			if (objectArrayValue[i].equals(OBJECT_ARRAY_VALUE[i]) == false) {
				return false;
			}
		}
		return true;
	}

}

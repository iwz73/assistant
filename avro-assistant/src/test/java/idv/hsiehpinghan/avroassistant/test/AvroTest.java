package idv.hsiehpinghan.avroassistant.test;

import idv.hsiehpinghan.avroassistant.avro.Avro;
import idv.hsiehpinghan.avroassistant.enumeration.Enumeration;

import java.io.File;
import java.nio.ByteBuffer;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AvroTest {
	private boolean _boolean = true;
	private int _int = 1;
	private long _long = 2;
	private float _float = 1.1f;
	private double _double = 2.2;
	private ByteBuffer _bytes = java.nio.ByteBuffer
			.wrap(new byte[] { '\u0041' });
	private CharSequence _string = "string";
	private boolean _record_boolean = true;
	private int _record_int = 1;
	private Enumeration _enum = Enumeration.ENUM_1;

	@Test
	public void defaultValue() {
		Avro avro = Avro.newBuilder().build();
		testAvro(avro);
	}

	@Test
	public void serializeAndDeserialize() throws Exception {
		File file = new File("/tmp/avro");
		DatumWriter<Avro> userDatumWriter = new SpecificDatumWriter<Avro>(
				Avro.class);
		try (DataFileWriter<Avro> dataFileWriter = new DataFileWriter<Avro>(
				userDatumWriter)) {
			dataFileWriter.create(Avro.getClassSchema(), file);
			dataFileWriter.append(Avro.newBuilder().build());
		}
		DatumReader<Avro> userDatumReader = new SpecificDatumReader<Avro>(
				Avro.class);
		try (DataFileReader<Avro> dataFileReader = new DataFileReader<Avro>(
				file, userDatumReader)) {
			Avro avro = null;
			while (dataFileReader.hasNext()) {
				avro = dataFileReader.next(avro);
				testAvro(avro);
			}
		}
	}

	private void testAvro(Avro avro) {
		Assert.assertEquals(avro.getBoolean$1(), Boolean.valueOf(_boolean));
		Assert.assertEquals(avro.getInt$1(), Integer.valueOf(_int));
		Assert.assertEquals(avro.getLong$1(), Long.valueOf(_long));
		Assert.assertEquals(avro.getFloat$1(), Float.valueOf(_float));
		Assert.assertEquals(avro.getDouble$1(), Double.valueOf(_double));
		Assert.assertEquals(avro.getBytes$1(), _bytes);
		Assert.assertEquals(String.valueOf(avro.getString$1()),
				String.valueOf(_string));
		Assert.assertEquals(avro.getRecord$1().getBoolean$1(),
				Boolean.valueOf(_record_boolean));
		Assert.assertEquals(avro.getRecord$1().getInt$1(),
				Integer.valueOf(_record_int));
		Assert.assertEquals(avro.getEnum$1(), _enum);
		Assert.assertEquals(avro.getArray$1().size(), 0);
		Assert.assertEquals(avro.getMap$1().size(), 0);
		Assert.assertEquals(String.valueOf(avro.getUnions$1()), "");
		Assert.assertEquals(avro.getFixed$1().bytes(), new byte[] { 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
	}
}

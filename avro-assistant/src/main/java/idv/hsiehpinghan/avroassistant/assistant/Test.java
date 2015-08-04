package idv.hsiehpinghan.avroassistant.assistant;

import java.io.File;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.specific.SpecificDatumReader;

import example.avro.User;

public class Test {
	public static void main(String[] args) throws Exception {
//		User user1 = new User();
//		user1.setName("Alyssa");
//		user1.setFavoriteNumber(256);
//		// Leave favorite color null
//
//		// Alternate constructor
//		User user2 = new User("Ben", 7, "red");
//
//		// Construct via builder
//		User user3 = User.newBuilder().setName("Charlie")
//				.setFavoriteColor("blue").setFavoriteNumber(null).build();

		// DatumWriter<User> userDatumWriter = new
		// SpecificDatumWriter<User>(User.class);
		// DataFileWriter<User> dataFileWriter = new
		// DataFileWriter<User>(userDatumWriter);
		// dataFileWriter.create(user1.getSchema(), new
		// File("/home/hsiehpinghan/Desktop/users.avro"));
		// dataFileWriter.append(user1);
		// dataFileWriter.append(user2);
		// dataFileWriter.append(user3);
		// dataFileWriter.close();

		DatumReader<User> userDatumReader = new SpecificDatumReader<User>(
				User.class);
		DataFileReader<User> dataFileReader = new DataFileReader<User>(
				new File("/home/hsiehpinghan/Desktop/users.avro"),
				userDatumReader);
		User user = null;
		while (dataFileReader.hasNext()) {
			user = dataFileReader.next(user);
			System.out.println(user);
		}
		dataFileReader.close();
	}
}

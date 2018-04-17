import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;

public class Main {

	// some definitions
	static String tutorialURI = "http://hostname/rdf/tutorial/";
	static String briansName = "Brian McBride";
	static String briansEmail1 = "brian_mcbride@hp.com";
	static String briansEmail2 = "brian_mcbride@hpl.hp.com";
	static String title = "An Introduction to RDF and the Jena API";
	static String date = "23/01/2001";

	public static void main (String args[]) throws Exception {
    
        // some definitions
        String personURI    = "http://somewhere/JohnSmith";
        String givenName    = "John";
        String familyName   = "Smith";
        String fullName     = givenName + " " + familyName;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith 
          = model.createResource(personURI)
                 .addProperty(VCARD.FN, fullName)
                 .addProperty(VCARD.N, 
                              model.createResource()
                                   .addProperty(VCARD.Given, givenName)
                                   .addProperty(VCARD.Family, familyName)
                                   .addProperty(VCARD.NICKNAME, "Smithy")
                                   .addProperty(VCARD.NICKNAME, "Adman"));
        
        // now write the model in XML form to a file
        File f = new File("/tmp/test");
        FileOutputStream out = new FileOutputStream(f);
        model.write(out, "N-TRIPLES");
        
        Model m = ModelFactory.createDefaultModel();

        // use the FileManager to find the input file
        InputStream in = FileManager.get().open( "/tmp/test" );
       if (in == null) {
           throw new IllegalArgumentException(
                                        "File: " + "/tmp/test" + " not found");
       }

       // read the RDF/XML file
       m.read(in, null, "N-TRIPLES");

       // write it to standard out
       Resource vcard = model.getResource(johnSmith.getURI());
       model.write(System.err);
}
}

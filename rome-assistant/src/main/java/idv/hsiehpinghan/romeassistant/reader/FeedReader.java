package idv.hsiehpinghan.romeassistant.reader;

import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * It Reads and prints any RSS/Atom feed type.
 * <p>
 * 
 * @author Alejandro Abdelnur
 *
 */
public class FeedReader {

	public static void main(String[] args) {
		boolean ok = false;
		try {
			URL feedUrl = new URL("http://rss.cnn.com/rss/edition_europe.rss");

			SyndFeedInput input = new SyndFeedInput();
			SyndFeedImpl feed = (SyndFeedImpl)input.build(new XmlReader(feedUrl));

			List<SyndEntryImpl> entries = feed.getEntries();
			for(SyndEntryImpl entry : entries) {
				System.out.println(entry.getTitle() + " : " + entry.getLink());
			}


			ok = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}

		if (!ok) {
			System.out.println();
			System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
			System.out.println("The first parameter must be the URL of the feed to read.");
			System.out.println();
		}
	}

}
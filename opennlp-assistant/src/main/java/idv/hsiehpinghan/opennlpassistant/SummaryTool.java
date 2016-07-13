package idv.hsiehpinghan.opennlpassistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Object;
import java.lang.reflect.Array;
import java.util.regex.*;

import org.springframework.core.io.ClassPathResource;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;

public class SummaryTool 
{
	
	
//Text into sentences
	public static String[] splitToSentences(String content)
	{
		
		String[] sent = sentenceDetect.sentDetect(content);
		return sent;
	}
	
//Text into paragraphs
	public static String[] splitToParagraphs(String content)
	{
    	String[] mystring = content.split(",");
		
		return mystring;
	}
	
	public static <T> Collection <T> intersect (Collection <? extends T> a, Collection <? extends T> b)
	{
	    Collection <T> result = new ArrayList <T> ();

	    for (T t: a)
	    {
	        if (b.remove (t)) result.add (t);
	    }

	    return result;
	}
	
//Computing the intersection(common words) between two sentences
	public static float sentenceIntersection (String sentence1, String sentence2)
	{
		String[] sent1 = tokenizer.tokenize(sentence1);
		String[] sent2 = tokenizer.tokenize(sentence2);
		
		if (sent1.length + sent2.length == 0)
			return 0;
		
		
		List<String> intersectArray = (List<String>) intersect(new ArrayList<String>(Arrays.asList(sent1)),new ArrayList<String>(Arrays.asList(sent2)));
		
		float result = ((float)(float)intersectArray.size() / ((float)sent1.length + ((float)sent2.length) / 2));
		
		return result;
	}
	
	public static String[] intersection(String[] sent1, String[] sent2)
	{
		if(sent1 == null || sent1.length == 0 || sent2 == null || sent2.length == 0)
			return new String[0];
		
		List<String> sent1List = new ArrayList<String>(Arrays.asList(sent1));
		List<String> sent2List = new ArrayList<String>(Arrays.asList(sent2));
			
		sent1List.retainAll(sent2List);
		
		String[] intersect = sent1List.toArray(new String[0]);
		
		return intersect;
	}
	
	public static String formatSentence(String sentence)
	{
		return sentence;
	}

	public static String getBestsentenceFromParagraph(String paragraph)
	{
		String[] sentences = splitToSentences(formatSentence(paragraph));
		if(sentences == null || sentences.length <= 2)
			return "";
		
		float[][] intersectionMatrix = getSentenceIntersectionMatrix(sentences);
		
		float[] sentenceScores = getSentenceScores(sentences, intersectionMatrix);
		
		return getBestSentence(sentences,  sentenceScores);
	}
	public static float[][] getSentenceIntersectionMatrix(String[] sentences)
	{
		//Split the content in to sentences
		
		
		int n = sentences.length;
		
		float[][] intersectionMatrix= new float[n][n];
		
		for(int i = 0; i< n; i++)
		{
			for(int j = 0; j< n; j++)
			{
				try
				{
					if(i == j)
						continue;
					
				intersectionMatrix[i][j] = sentenceIntersection(sentences[i], sentences[j]);	
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		
		//Build the sentence dictionary
		//The score of a sentence is the sum of all its intersections
		
		return intersectionMatrix;
	}
	
	public static float[] getSentenceScores(String[] sentences, float[][] scores)
	{
		float[] scoresReturn = new float[sentences.length];
		
		for(int i=0; i<sentences.length; i++)
		{
			int sentenceScore = 0;
			for(int j=0; j<scores[i].length; j++)
			{
				sentenceScore += scores[i][j];				
			}
			scoresReturn[i] = sentenceScore;
		}
		
		return scoresReturn;
	}
	
	public static String getBestSentence(String[] sentences, float[] scores)
	{	
		
		return sentences[getMaxIndex(scores)];
		
	}
	
	public static int getMaxIndex(float[] array)
	{
		int maxIndex = 0;
		float max = -1;
		for(int i=0; i<array.length; i++)
		{
			if(array[i]>max)
			{
				max = array[i];
				maxIndex = i;
			}
			
		}
		return maxIndex;
	}
	
	public static TokenizerME tokenizer;
	public static SentenceDetectorME sentenceDetect;
	public static SummaryTool Instance;
	
	public SummaryTool()
	{
		initialize();
	}
	
	public void initialize()
	{
		

		try 
		{	
			InputStream sentenceModelIS = new ClassPathResource("Data/en-sent.bin").getInputStream(); //new FileInputStream("src/Data/en-sent.bin");
			SentenceModel model;
			model = new SentenceModel(sentenceModelIS);
			sentenceDetect = new SentenceDetectorME(model);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		try 
		{	
			InputStream tokenizerModelIS = new ClassPathResource("Data/en-token.bin").getInputStream();
			TokenizerModel tokenModel;
			tokenModel = new TokenizerModel(tokenizerModelIS);
		    tokenizer = new TokenizerME(tokenModel);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) 
	{
		String title = "Swayy is a beautiful new dashboard for discovering and curating online content [Invites]";
		String content = "   Lior Degani, the Co-Founder and head of Marketing of Swayy, pinged me last week when I was in California to tell me about his startup and give me beta access. I heard his pitch and was skeptical. I was also tired, cranky and missing my kids – so my frame of mind wasn’t the most positive.I went into Swayy to check it out, and when it asked for access to my Twitter and permission to tweet from my account, all I could think was, “If this thing spams my Twitter account I am going to bitch-slap him all over the Internet.” Fortunately that thought stayed in my head, and not out of my mouth.One week later, I’m totally addicted to Swayy and glad I said nothing about the spam (it doesn’t send out spam tweets but I liked the line too much to not use it for this article). I pinged Lior on Facebook with a request for a beta access code for TNW readers. I also asked how soon can I write about it. It’s that good. Seriously. I use every content curation service online. It really is That Good.What is Swayy? It’s like Percolate and LinkedIn recommended articles, mixed with trending keywords for the topics you find interesting, combined with an analytics dashboard that shows the trends of what you do and how people react to it. I like it for the simplicity and accuracy of the content curation. Everything I’m actually interested in reading is in one place – I don’t have to skip from another major tech blog over to Harvard Business Review then hop over to another major tech or business blog. It’s all in there. And it has saved me So Much TimeAfter I decided that I trusted the service, I added my Facebook and LinkedIn accounts. The content just got That Much Better. I can share from the service itself, but I generally prefer reading the actual post first – so I end up sharing it from the main link, using Swayy more as a service for discovery.I’m also finding myself checking out trending keywords more often (more often than never, which is how often I do it on Twitter.com).The analytics side isn’t as interesting for me right now, but that could be due to the fact that I’ve barely been online since I came back from the US last weekend. The graphs also haven’t given me any particularly special insights as I can’t see which post got the actual feedback on the graph side (however there are numbers on the Timeline side.) This is a Beta though, and new features are being added and improved daily. I’m sure this is on the list. As they say, if you aren’t launching with something you’re embarrassed by, you’ve waited too long to launch.It was the suggested content that impressed me the most. The articles really are spot on – which is why I pinged Lior again to ask a few questions:How do you choose the articles listed on the site? Is there an algorithm involved? And is there any IP?Yes, we’re in the process of filing a patent for it. But basically the system works with a Natural Language Processing Engine. Actually, there are several parts for the content matching, but besides analyzing what topics the articles are talking about, we have machine learning algorithms that match you to the relevant suggested stuff. For example, if you shared an article about Zuck that got a good reaction from your followers, we might offer you another one about Kevin Systrom (just a simple example).Who came up with the idea for Swayy, and why? And what’s your business model?Our business model is a subscription model for extra social accounts (extra Facebook / Twitter, etc) and team collaboration.The idea was born from our day-to-day need to be active on social media, look for the best content to share with our followers, grow them, and measure what content works best.Who is on the team?Ohad Frankfurt is the CEO, Shlomi Babluki is the CTO and Oz Katz does Product and Engineering, and I [Lior Degani] do Marketing. The four of us are the founders. Oz and I were in 8200 [an elite Israeli army unit] together. Emily Engelson does Community Management and Graphic Design.If you use Percolate or read LinkedIn’s recommended posts I think you’ll love Swayy.➤ Want to try Swayy out without having to wait? Go to this secret URL and enter the promotion code thenextweb . The first 300 people to use the code will get access.Image credit: Thinkstock";
//		String content = null;
		Instance = new SummaryTool();
		
//		 try {
//			 
//			 content = new Scanner(new File("/home/hsiehpinghan/workspace/opennlp-assistant/src/main/resources/Data/text-sample.txt")).useDelimiter("\\Z").next();
//			
//		 }
//		 catch (FileNotFoundException e) {
//		        e.printStackTrace();
//		    }		 
		
		String[] paragraphs = splitToParagraphs(content);
		StringBuilder summary = new StringBuilder();
		
		for(String p : paragraphs)
		{
			String bestSent = getBestsentenceFromParagraph(p);
			if(bestSent != null && bestSent.length() > 0)
				summary.append(bestSent);
		}
		
		System.out.println(summary);
	}
}
package webscrapingfundamentals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

class Scraper
{

	ArrayList<String> pageContents = new ArrayList<String>();
	String[] happyWords =
	{ "love", "loved", "like", "liked", "awesome", "amazing", "good", "great",
			"excellent" };
	String[] sadWords =
	{ "hate", "hated", "dislike", "disliked", "awful", "terrible", "bad",
			"painful", "worst" };
	URL url; // use url, not uri for type
	ArrayList<String> comments = new ArrayList<String>();

	Scraper(URL url)
	{
		this.url = url;
	}
	
	/** Scrape page
	 * 
	 */
	void parseWebpage()
	{

		InputStreamReader inputReader = null;
		BufferedReader reader = null;
		String input;
		try
		{
			inputReader = new InputStreamReader(url.openStream());
			reader = new BufferedReader(inputReader);
			while ((input = reader.readLine()) != null)
			{
				// System.out.println(input);
				pageContents.add(input);
			}
			reader.close();
		}
		catch (Exception ex)
		{
			throw new Error("Unable to read from URL");
		}

	}
	
	/** Add scraped data from page to a single string
	 * 
	 */
	void retrievePageContents()
	{
		StringBuilder longString = new StringBuilder();
		for (int i = 0; i < pageContents.size(); i++)
		{
			longString.append(pageContents.get(i));
		}
		String pageMarkup = longString.toString().toLowerCase();
		extractComments(pageMarkup);

	}
	
	/** Parse string containing page data for comments
	 * 
	 * @param longString all parsed page data combined into single string
	 */
	void extractComments(String longString)
	{
		longString = longString.toLowerCase();
		String openingCommentTag = "<div class=\"CT\">".toLowerCase();
		String endingCommentTag = "</div>".toLowerCase();

		for (int i = 0; i < longString.length(); i++)
		{
			int openIndex = longString.indexOf(openingCommentTag, i);
			if (openIndex != -1)
			{
				int endIndex = longString.indexOf(endingCommentTag, openIndex);
				// System.out.println("openIndex: " + openIndex + "\n" +
				// "endIndex: " + endIndex);

				comments.add(longString.substring(
						openIndex + openingCommentTag.length(), endIndex));
				i = endIndex;

			}
		}
		return;
	}
	
	/** Find amount of happy and sad words within individual comments and for video overall
	 * 
	 */
	void printData()
	{
		int totalNumberofComments = comments.size();
		int videoHappyCommentCount = 0;
		int videoSadCommentCount = 0;

		for (String comment : comments)
		{
			int sadWordsInComment = retrieveNumberOfSadKeywords(comment);
			int happyWordsInComment = retrieveNumberOfHappyKeywords(comment);
			String generalSentiment = null;

			generalSentiment = (sadWordsInComment >= happyWordsInComment) ? "sad"
					: "happy";
			System.out.println("_________________________________________________________________");
			System.out.println(comment);
			System.out.println("From a sample size of " + totalNumberofComments
					+ " persons, " + "\n" + "This sentence is mostly "
					+ generalSentiment);
			System.out.println("It contained " + happyWordsInComment
					+ " happy keywords and " + sadWordsInComment
					+ " sad keywords");
			System.out.println("_________________________________________________________________");
			if (generalSentiment.equalsIgnoreCase("sad"))
			{
				if(sadWordsInComment > 0)
				{
					videoSadCommentCount++;
				}
			}
			else if (generalSentiment.equalsIgnoreCase("happy"))
			{
				if(happyWordsInComment > 0)
				{
					videoHappyCommentCount++;
				}
			}

		}

		String videoFeelingCommentary = "";
		if(videoHappyCommentCount > videoSadCommentCount)
		{
			videoFeelingCommentary = "The general feelings towards this video were happy";
		}
		else if(videoHappyCommentCount < videoSadCommentCount)
		{
			videoFeelingCommentary = "The general feelings towards this video were sad";
		}
		else if(videoHappyCommentCount == videoSadCommentCount)
		{
			videoFeelingCommentary = "The general feelings towards this video were neutral";
		}
		
		System.out.println(videoFeelingCommentary);

	}
	
	/** Retrieve amount of happy words in comment
	 * 
	 * @param comment comment by user
	 * @return number of happy words within comment
	 */
	int retrieveNumberOfHappyKeywords(String comment)
	{
		int numberOfHappyWordsInComment = 0;

		for (String happyWord : happyWords)
		{
			if (comment.indexOf(happyWord) != -1)
			{
				numberOfHappyWordsInComment++;
			}
		}
		return numberOfHappyWordsInComment;
	}
	
	/** Retrieve amount of sad words in comment
	 * 
	 * @param comment comment by user
	 * @return number of sad words within comment
	 */
	int retrieveNumberOfSadKeywords(String comment)
	{
		int numberOfSadWordsInComment = 0;

		for (String sadWord : sadWords)
		{
			if (comment.indexOf(sadWord) != -1)
			{
				numberOfSadWordsInComment++;
			}
		}
		return numberOfSadWordsInComment;
	}

}

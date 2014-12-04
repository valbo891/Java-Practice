package webscrapingfundamentals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

class Main
{

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		System.out
				.println("Please enter the url of the youtube page you want to scrape");
		String userSpecifiedURL = s.next();
		s.close();
		String template = "https://plus.googleapis.com/u/0/_/widget/render/comments?first_party_property=YOUTUBE&href="
				.concat(userSpecifiedURL);
		URL url;
		try
		{
			url = new URL(template);
		}
		catch (MalformedURLException ex)
		{
			throw new Error("Invalid URL value");
		}

		Scraper scraper = new Scraper(url);
		// scraper.requestInput();
		scraper.parseWebpage();
		scraper.retrievePageContents();
		scraper.printData();

	}

}

package WebCrawler;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList <WebCrawl> bot =new ArrayList<>();
		bot.add(new WebCrawl("https://www.wikipedia.org/",1));
		bot.add(new WebCrawl("https://timesofindia.indiatimes.com/",2));
		bot.add(new WebCrawl("https://www.cricbuzz.com/",3));
		
		for (WebCrawl w: bot)
		{
			try {
				w.getThread().join();
			
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}

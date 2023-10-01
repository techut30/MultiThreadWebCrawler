package WebCrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebCrawl implements Runnable {

	private static final int MAX_DEPTH=5;
	private Thread thread;
	private String firstlink;
	private ArrayList<String>visited=new ArrayList<String>();
	private int ID;
	
	public WebCrawl(String link, int num) {
		System.out.println("WebCrawler Created Successfully");
		firstlink=link;
		ID=num;
		
		thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		crawl(1,firstlink);
	}
	
	private void crawl(int level, String url)
	{
		if(level<=MAX_DEPTH)
		{
			Document doc = request(url);
			if(doc!=null)
			{
				for(Element e : doc.select("a[href]")) {
					String nextlink=e.absUrl("href");
					if(visited.contains(nextlink)==false) {
						crawl(++level,nextlink);
					}
				}
			}
		}
	}
	
	private Document request (String url) {
		try {
			Connection con=Jsoup.connect(url);
			Document doc = con.get();
			if(con.response().statusCode()==200) {
				System.out.println("/nBot ID: "+ID+" Recieved webpage link at : "+url);
				String title = doc.title();
				System.out.println(title);
				visited.add(url);
				return doc;
			}
			return null;
		}
		catch (IOException e)
		{
			return null;
		}
	}
	
	public Thread getThread() {
		return thread;
	}
}

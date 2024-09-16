# Java WebCrawler

This is a multithreaded web crawler written in Java that uses the JSoup library to scrape websites. The program takes a URL as input and crawls through web pages up to a maximum depth of 5, printing out the title of each web page it visits. Multiple web crawlers run in parallel using threads to speed up the process.

## Features

- **Multithreaded**: Each web crawler runs on its own thread, allowing for parallel web scraping.
- **Depth-limited crawling**: The crawler stops after reaching a specified depth of 5 to avoid infinite loops.
- **Page Title Extraction**: The crawler prints the title of each web page it visits.
- **Unique URL Visits**: The program ensures that no URL is visited more than once in a given session.

## Project Structure

The project contains two main classes:

1. **`WebCrawl`**: Implements the crawling logic. Each instance runs on its own thread and follows links up to a depth of 5.
2. **`Main`**: The entry point of the program. It creates multiple web crawlers and manages their execution.

## Installation

### Prerequisites

- Java Development Kit (JDK) version 8 or higher
- Maven or Gradle (optional, for dependency management)
- [JSoup Library](https://jsoup.org/), version 1.13.1 or higher

### Steps

1. **Clone the repository or copy the source code**:
    ```bash
    git clone https://github.com/yourusername/JavaWebCrawler.git
    ```
2. **Download JSoup**:
    If using Maven, add the following dependency in your `pom.xml` file:
    ```xml
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.13.1</version>
    </dependency>
    ```

    If using Gradle, add this line to your `build.gradle`:
    ```groovy
    implementation 'org.jsoup:jsoup:1.13.1'
    ```

    Alternatively, download the JSoup JAR manually and add it to your project’s classpath.

3. **Compile and run the program**:
    ```bash
    javac -cp jsoup-1.13.1.jar WebCrawler/*.java
    java -cp .:jsoup-1.13.1.jar WebCrawler.Main
    ```

## How It Works

### WebCrawl Class

- **Constructor**: 
  - Takes the starting URL and a unique identifier for the crawler.
  - Starts a new thread to run the crawling process.
  
- **crawl(int level, String url)**: 
  - Recursively crawls through the links found on the given URL until the maximum depth (5) is reached.
  
- **request(String url)**: 
  - Fetches the document from the given URL using JSoup.
  - Prints the status code, title, and adds the URL to the visited list if the page was successfully fetched.

### Main Class

- **Main Method**:
  - Initializes multiple web crawlers with different starting URLs.
  - Manages the execution of each web crawler by calling `join()` to ensure the main thread waits for each crawler to finish before exiting.

## Example

In the `Main` class, three instances of `WebCrawl` are created, each starting at different websites:
- `https://www.wikipedia.org/`
- `https://timesofindia.indiatimes.com/`
- `https://www.cricbuzz.com/`

Each crawler will explore up to a depth of 5 and print the titles of the web pages it visits.

### Output Example:
```text
WebCrawler Created Successfully
WebCrawler Created Successfully
WebCrawler Created Successfully

Bot ID: 1 Recieved webpage link at : https://www.wikipedia.org/
Wikipedia

Bot ID: 1 Recieved webpage link at : https://www.wikibooks.org/
Wikibooks

Bot ID: 2 Recieved webpage link at : https://timesofindia.indiatimes.com/
Times of India

Bot ID: 3 Recieved webpage link at : https://www.cricbuzz.com/
Cricbuzz


```

## Customization

To customize the starting URLs or add more web crawlers:

1. Open the `Main.java` file.
2. Add more instances of `WebCrawl` with the desired URLs:
    ```java
    bot.add(new WebCrawl("https://example.com/", 4));
    ```

## Limitations

- The current implementation does not handle loops or duplicate links across different web crawlers.
- The program may run into issues with sites that block web crawlers (like CAPTCHA or IP rate-limiting).
- External links (URLs outside the base domain) are not filtered.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [JSoup Library](https://jsoup.org/) for easy HTML parsing and web scraping in Java.


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class CrawlerMain
{
    public static void main (String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: java CrawlerMain <domain_name>");
            System.exit(0);
        }
        String domainName = args[0];
        if (!URLValidator.validate(domainName)) {
            System.out.println("Validation Failure: domainName " + domainName + " is invalid.");
            System.exit(0);
        }

        System.out.println("Starting crawler for domain " + domainName);
        WebPage webPage = new WebPage(domainName);
        Crawler crawler = new Crawler();
        crawler.crawl(webPage);
        int indent = 3; // not used
        crawler.generateSiteMap(webPage, indent);
    }
}

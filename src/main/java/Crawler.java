import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class Crawler
{
    List<String> crawled = new ArrayList<>();
    List<String> generated = new ArrayList<>();

    public void crawl(WebPage webPage) {
        System.out.println("Now crawling ..." + webPage.getWebPageName());
        Connection c = Jsoup.connect(webPage.getWebPageName());
        try {
            Connection.Response response = c.execute();
            Document document = null;
            if (response.statusCode() == 200) {
                document = c.get();
                Elements allLinks = document.select("a[href]");
                // Set to give us uniqueness
                Set<WebPage> allInternalLinks = new LinkedHashSet<>();
                Set<String> allExternalLinks = new LinkedHashSet<>();
                for(Element webpagelink : allLinks) {
                    //System.out.println("Link url is " + webpagelink.absUrl("href"));
                    // ignore url if # present
                    String fullUrl;
                    String url = webpagelink.absUrl("href");
                    if (!url.startsWith("http")) {
                        continue;
                    }
                    if (!url.contains("#")) {
                        fullUrl = url;
                    } else {
                        fullUrl = url.substring(0, url.indexOf("#"));
                    }
                    if (fullUrl.endsWith("/")) {
                        fullUrl = fullUrl.substring(0, fullUrl.length()-1);
                    }
                    //System.out.println("FULL URL is " + fullUrl);

                    // extract top level domain to compare if external link or not
                    String[] paths = fullUrl.split("//");
                    String tld;
                    if (paths.length > 1 && paths[1].contains("/")) {
                        tld = paths[1].split("/")[0];
                    } else {
                        tld = paths[1];
                    }
                    if (!webPage.getWebPageName().contains(tld)) {
                        allExternalLinks.add(fullUrl);
                    } else {
                        if (!fullUrl.equals(webPage.getWebPageName())) {
                            WebPage wp = new WebPage(fullUrl);
                            allInternalLinks.add(wp);
                        }
                    }
                }
                Set<String> allImages = new LinkedHashSet<String>();
                Elements imgElements = document.getElementsByTag("img");
                for(Element e: imgElements) {
                    //System.out.println("Link url is " + e.attr("src"));
                    allImages.add(e.attr("src"));
                }
                webPage.setAllExternalLinks(allExternalLinks);
                webPage.setAllInternalLinks(allInternalLinks);
                webPage.setAllImages(allImages);

                crawled.add(webPage.getWebPageName());

                webPage.getAllInternalLinks()
                        .stream()
                        .filter(candidateWebPage -> !crawled.contains(candidateWebPage.getWebPageName()))
                        .forEach(this::crawl);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            e.printStackTrace();
        }
    }

    public void generateSiteMap(WebPage webPage, int indent) {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Generating site map for " + webPage.getWebPageName());
        System.out.println(" *** Images *** ");
        Set<String> images = webPage.getAllImages();
        images.forEach(System.out::println);
        System.out.println(" *** External Links *** ");
        Set<String> externalLinks = webPage.getAllExternalLinks();
        externalLinks.forEach(System.out::println);
        System.out.println(" *** Internal Links *** ");
        Set<WebPage> internalLinks = webPage.getAllInternalLinks();
        internalLinks.forEach(webpage -> System.out.println(webpage.getWebPageName()));
        generated.add(webPage.getWebPageName());
        internalLinks
                .stream()
                .filter(candidateWebPage -> !generated.contains(candidateWebPage.getWebPageName()))
                .forEach(webpage -> generateSiteMap(webpage, indent));
    }
}

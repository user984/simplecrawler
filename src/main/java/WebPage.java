import java.util.LinkedHashSet;
import java.util.Set;


public class WebPage
{
    private String webPageName;

    // All external links will go here
    private Set<String> allExternalLinks = new LinkedHashSet<String>();

    private Set<String> allImages = new LinkedHashSet<String>();

    //  Since internal links need be crawled themselves, created a set of webpages and not strings
    private Set<WebPage> allInternalLinks = new LinkedHashSet<WebPage>();

    public WebPage(String domainName) {
        this.webPageName = domainName;
    }

    public String getWebPageName() {
        return webPageName;
    }

    public void setWebPageName(String webPageName) {
        this.webPageName = webPageName;
    }

    public Set<String> getAllExternalLinks() {
        return allExternalLinks;
    }

    public void setAllExternalLinks(Set<String> allExternalLinks) {
        this.allExternalLinks = allExternalLinks;
    }

    public Set<String> getAllImages() {
        return allImages;
    }

    public void setAllImages(Set<String> allImages) {
        this.allImages = allImages;
    }

    public Set<WebPage> getAllInternalLinks() {
        return allInternalLinks;
    }

    public void setAllInternalLinks(Set<WebPage> allInternalLinks) {
        this.allInternalLinks = allInternalLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebPage)) return false;

        WebPage webPage = (WebPage) o;

        return webPageName.equals(webPage.webPageName);
    }

    @Override
    public int hashCode() {
        return webPageName.hashCode();
    }
}

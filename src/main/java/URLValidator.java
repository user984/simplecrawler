import org.apache.commons.validator.routines.UrlValidator;


public class URLValidator
{
    public static boolean validate(final String url) {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(url)) {
            return true;
        }
        return false;
    }
}

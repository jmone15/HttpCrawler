package gr.media24.httpcrawler.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jmone15
 */
public class CommonUtils {

    private static final Pattern ARTICLE_URL = Pattern.compile("(.*)\\.(\\d+)(\\.html||\\.amp\\.html)");

    public static boolean isContained(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    public static String getArticleId(String path) {
        Matcher m = ARTICLE_URL.matcher(path);
        String arId = "0";
        if (m.find()) {
            arId = m.group(2);

        }
        return arId;
    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        System.out.println("Using System Property: " + os);
        return os;
    }

    public static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        } // If there was an Exception
        // while creating URL object
        catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }
    
    public static void showLuke(){
        System.out.println("              _,.");
        System.out.println("            ,''   `.     __....__ ");
        System.out.println("          ,'        >.-''        ``-.__,)");
        System.out.println("        ,'      _,''           _____ _,'");
        System.out.println("       /      ,'           _.:':::_`:-._ ");
        System.out.println("      :     ,'       _..-''  \\`'.;.`-:::`:. ");
        System.out.println("      ;    /       ,'  ,::'  .\\,'`.`. `\\::)`");
        System.out.println("    /    /      ,'        \\   `. '  )  )/ ");
        System.out.println("   /    /      /:`.     `--`'   \\     '`");
        System.out.println("  `-._/      /::::)             )");
        System.out.println("     /      /,-.:(   , _   `.-' ");
        System.out.println("    ;      :(,`.`-' ',`.     ;");
        System.out.println("    :       |:\\`' )      `-.._\\ _");
        System.out.println("   |         `:-(             `)``-._ ");
        System.out.println("    |           `.`.        /``'      ``:-.-__,");
        System.out.println("    :           / `:\\ .     :                ` \\`-");
        System.out.println("    \\        ,'   '}  `.   |");
        System.out.println("   _..-`.    ,'`-.   }   |`-'  ");
        System.out.println(" ,'__    `-'' -.`.'._|   | ");
        System.out.println("     ```--..,.__.(_|.|   |::._");
        System.out.println("      __..','/ ,' :  `-.|::)_`.");
        System.out.println("      `..__..-'   |`.      __,' ");
        System.out.println("                  :  `-._ `  ;");
        System.out.println("                   \\ \\   )  /");
        System.out.println("                   .\\ `.   /");
        System.out.println("                    ::    /");
        System.out.println("                    :|  ,'");
        System.out.println("                    :;,' ");
        System.out.println("                   `'");
        System.out.println("########################### HTTP LUKE ###########################");
    }
}

package gr.media24.httpcrawler.generators;

import gr.media24.httpcrawler.model.Link;
import gr.media24.httpcrawler.utils.CSVUtil;
import gr.media24.httpcrawler.utils.CommonUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author jmone15
 */
public class UrlCrawler {

    private static final Pattern EMBED_URL = Pattern.compile("(http://)([^:^/]*)(:\\d*)?(.*)?");
    private HashSet<Link> links;
    private int counter;
    private FileWriter fileWriter;

    public List<String> parseDocument(String filePath, String limit, String isMobile) {
        List<String> urls = new ArrayList<>();
        Document sitemap = null;
        try {
            System.out.println("START CHECKER");
            int limiter = 0;
            if (!limit.isEmpty()) {
                limiter = Integer.parseInt(limit);
            }
            if (CommonUtils.isValid(filePath)) {
                sitemap = Jsoup.connect(filePath).parser(Parser.xmlParser()).timeout(10 * 1000).get();
            } else {
                File input = new File(filePath);
                sitemap = Jsoup.parse(input, "UTF-8");
            }
            Elements urlEntries = sitemap.select("url");
            if (CommonUtils.getOperatingSystem().startsWith("Windows")) {
                File fileDir = new File(File.separator + "httpcrawler");
                fileDir.mkdir();
                fileWriter = new FileWriter(File.separator + "httpcrawler" + File.separator + "links.csv");
            } else {
                File fileDir = new File(File.separator + "tmp" + File.separator + "httpcrawler");
                fileDir.mkdir();
                fileWriter = new FileWriter(File.separator + "tmp" + File.separator + "httpcrawler" + File.separator + "links.csv");
            }
            CSVUtil.writeLine(fileWriter, Arrays.asList("Type", "URL", "Article Id"));
            List<String> duplicateDetector = new ArrayList<>();
            int count = 0;
            for (Element urlEntry : urlEntries) {
                if (limiter == 0 || limiter > count) {
                    //urls.add(urlEntry.getElementsByTag("loc").text());
                    List<Link> foundList;
                    if (isMobile.equalsIgnoreCase("N")) {
                        foundList = crawlArticles(urlEntry.getElementsByTag("loc").text().replace("http://", "https://"));
                    } else {
                        foundList = crawlArticles(urlEntry.getElementsByTag("loc").text().replace("http://www.", "https://m."));
                    }
                    if (foundList != null) {
                        for (Link lnk : foundList) {
                            List<String> linksList = new ArrayList<>();
                            linksList.add(lnk.getTypeName());
                            linksList.add(lnk.getUrl());
                            linksList.add(lnk.getArticleId());
                            if (!duplicateDetector.contains(lnk.getUrl())) {
                                CSVUtil.writeLine(fileWriter, linksList);
                                duplicateDetector.add(lnk.getUrl());
                            }
                        }
                    }
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UrlCrawler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            fileWriter.flush();
            fileWriter.close();
            duplicateDetector.clear();
            System.out.println("Total Articles: " + count);
        } catch (IOException ex) {
            Logger.getLogger(UrlCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urls;
    }

    public List<Link> crawlArticles(String articleLink) {
        links = new HashSet<>();

        try {
            counter++;
            System.out.println(counter + " STARTING URL PARSING FOR " + articleLink);
            Document article
                    = Jsoup.connect(articleLink)
                            .header("Accept-Encoding", "gzip, deflate")
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
                            .maxBodySize(0)
                            .timeout(600000)
                            .get();

            Elements lnks = article.select("a[href]");
            Elements media = article.select("[src]");
            Elements imports = article.select("link[href]");
            Elements scripts = article.select("script");

            for (Element link : lnks) {
                if (CommonUtils.isContained(link.attr("href"), "http")) {
                    links.add(new Link(1, "Links", link.attr("href"), CommonUtils.getArticleId(articleLink)));
                }
            }

            for (Element med : media) {
                if (CommonUtils.isContained(med.attr("src"), "http")) {
                    links.add(new Link(2, "Media", med.attr("src"), CommonUtils.getArticleId(articleLink)));
                }
            }

            for (Element imp : imports) {
                if (CommonUtils.isContained(imp.attr("href"), "http")) {
                    links.add(new Link(3, "Imports", imp.attr("href"), CommonUtils.getArticleId(articleLink)));
                }
            }

            for (Element scp : scripts) {
                Matcher m = EMBED_URL.matcher(scp.data());
                if (m.find()) {
                    links.add(new Link(3, "Js", m.group(0), CommonUtils.getArticleId(articleLink)));
                }
            }

            List<Link> sortedList = links.stream().sorted(Comparator.comparing(Link::getTypeId)).collect(Collectors.toList());
            return sortedList;

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}

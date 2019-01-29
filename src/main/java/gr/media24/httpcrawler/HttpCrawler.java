/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.media24.httpcrawler;

import gr.media24.httpcrawler.generators.UrlCrawler;
import gr.media24.httpcrawler.utils.CommonUtils;
import java.util.Scanner;

/**
 *
 * @author jmone15
 */
public class HttpCrawler {

    public static void main(String[] args) {

        CommonUtils.showLuke();
        
        try (Scanner scanner = new Scanner(System.in)) {
            UrlCrawler uc = new UrlCrawler();
            while (true) {

                System.out.print("Enter the sitemap (local or URL) path you wish to crawl (press q to exit) : ");
                String input = scanner.nextLine();
                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    break;
                }

                System.out.print("How many articles should be crawled ? (press q to exit) : ");
                String limit = scanner.nextLine();
                if ("q".equals(limit)) {
                    System.out.println("Exit!");
                    break;
                }
                
                System.out.print("Would you like to check the mobile version (Y|N) ? (press q to exit) : ");
                String isMobile = scanner.nextLine();
                if ("q".equals(isMobile)) {
                    System.out.println("Exit!");
                    break;
                }
                
                uc.parseDocument(input, limit, isMobile);

                System.out.println("input : " + input);
                System.out.println("-----------\n");
            }
        }       
    }
}

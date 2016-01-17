package com.sainsburys.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsburys.interview.products.Products;
import com.sainsburys.interview.utils.SainsburysScraperService;


/**
 * Created by rkotecha on 16/01/2016.
 */
public class MainApp {

    public static void main(String[] args) {

        SainsburysScraperService scraper = new SainsburysScraperService();
        Products products = null;
        try {

            products  = scraper.getProducts("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
            if (products == null ) {
                System.out.println("No products found");
                return;
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            System.out.println(mapper.writeValueAsString(products));

        } catch (Exception e) {
            System.out.println("An error has occurred");
        }

    }

}

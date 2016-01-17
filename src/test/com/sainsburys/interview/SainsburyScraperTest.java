package com.sainsburys.interview;

import com.sainsburys.interview.products.Products;
import com.sainsburys.interview.utils.SainsburysScraperService;
import junit.framework.TestCase;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 *
 * Test class to test the functionality of the Sainsbury Scaper test
 */
public class SainsburyScraperTest extends TestCase {


    /*
     * Test case to ensure no products are returned if the URL passed is null
     */
    public void testNullURL() {
        SainsburysScraperService service = new SainsburysScraperService();
        Products products  = service.getProducts(null);
        assertTrue(products == null);
    }



    public void testValidURL() {
        String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
        SainsburysScraperService service = new SainsburysScraperService();
        Products products = service.getProducts(url);
        assertTrue(products.getProducts().size() == 7);

    }

    public void testInValidURL() {
        String url = "http://hiring-tests.s333-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
        SainsburysScraperService service = new SainsburysScraperService();
        Products products = service.getProducts(url);
        assertTrue(products == null);

    }



}

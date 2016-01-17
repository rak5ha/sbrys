package com.sainsburys.interview;

import com.sainsburys.interview.products.Products;
import com.sainsburys.interview.utils.SainsburysScraperService;
import junit.framework.TestCase;

/**
 * Created by rkotecha on 17/01/2016.
 */
public class TestSainsburysIntegration extends TestCase {


    /* integration tests*/
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

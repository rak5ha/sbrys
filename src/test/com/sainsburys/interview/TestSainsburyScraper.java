package com.sainsburys.interview;

import com.sainsburys.interview.products.Product;
import com.sainsburys.interview.products.Products;
import com.sainsburys.interview.utils.SainsburysScraperService;
import junit.framework.TestCase;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * Test class to test the functionality of the Sainsbury Scaper test
 * Using Mocks to mock out the end call/
 *
 */
public class TestSainsburyScraper extends TestCase {


    // test to check the products return is 7 from valid.
    public void testProductsSize() {
        SainsburysScraperService service = new SainsburysScraperService();
        SainsburysScraperService spy = Mockito.spy(service);

        Document doc = readDocumentFromFile("valid.html");
        Document childDoc = readDocumentFromFile("Details.html");

        try {
            Mockito.doReturn(doc).when(spy).getDocumentHelper(Mockito.anyString());
            Mockito.doReturn(childDoc).when(spy).getDocumentHelper((Connection.Response)(Mockito.any()));
            Mockito.doReturn("28kb").when(spy).getSizeInKB((Connection.Response)Mockito.any());
            Mockito.doReturn(null).when(spy).execute(Mockito.anyString());

            Products products = spy.getProducts("");
            assertEquals(products.getProducts().size(), 7);

        } catch (Exception io) {
            assertTrue(false);
        }
    }


    public void testProductDetailsDescription() {

        SainsburysScraperService service = new SainsburysScraperService();
        SainsburysScraperService spy = Mockito.spy(service);

        Document doc = readDocumentFromFile("valid.html");
        Document childDoc = readDocumentFromFile("Details.html");

        try {
            Mockito.doReturn(doc).when(spy).getDocumentHelper(Mockito.anyString());
            Mockito.doReturn(childDoc).when(spy).getDocumentHelper((Connection.Response)(Mockito.any()));
            Mockito.doReturn("28kb").when(spy).getSizeInKB((Connection.Response)Mockito.any());
            Mockito.doReturn(null).when(spy).execute(Mockito.anyString());

            Products products = spy.getProducts("");
            List<Product> returnedProducts = products.getProducts();

            for (Product product : returnedProducts) {

                if (!product.getDescription().equalsIgnoreCase("Apricots")) {
                    assertTrue(false);
                }

            }

            assertEquals(products.getProducts().size(), 7);

        } catch (Exception io) {
            assertTrue(false);
        }

    }

    private static Document readDocumentFromFile(String resourceName) {
        try {
            File file = new File(Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(resourceName)
                    .getPath());
            return Jsoup.parse(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

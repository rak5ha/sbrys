package com.sainsburys.interview.utils;

import com.sainsburys.interview.products.Product;
import com.sainsburys.interview.products.Products;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by rkotecha on 14/01/2016.
 */
public class SainsburysScraperService {

    private static final String PRODUCT_LISTER_ID = "productLister";
    private static final String PRODUCT_PRICE_PER_UNIT_CLASS = ".pricePerUnit";
    private static final String PAGINATION_CLASS = ".paginationBottom";
    private static final String PRODUCTS_INNER = ".productInner";
    private static final String PRODUCT_INFO_H3 = ".productInfo h3";
    private static final String PRODUCT_LINK_TEXT = ".productText p";
    private static final int KB_BYTES = 1024;


    public Products getProducts(String url){

        Products products = null;
        String title = null;
        String description = null;
        String size = null;
        String price = null;
        BigDecimal aPrice = null;

        // if URL is empty
        if (url == null) return null;

        try {
            Document document = getDocumentHelper(url);

            Element productLister = getProductListElement(document); //getElementById(ProductLister)

            if (productLister == null ) return null;

            Elements paginationSectionList = getPaginationElement(productLister); //select(.paginationBottom)

            Elements productInnerList = getProductInfoIncPriceElements(productLister); //select(.ProductInner)
            products = new Products();


            for (Element productInner : productInnerList) {
                //get the title
                Elements productInfo = getProductInfo(productInner); //select(.productInfo h3)
                title = productInfo.text();

                //get the linked detailed product page
                String detailsLink = getLinkedDocumentURL(productInfo);
                Connection.Response response = execute(detailsLink);
                size = getSizeInKB(response);
                description = getDetailedDescription(response);

                //get the price information
                price = getPricePerUnit(productInner).substring(6); //select(.pricePerUnit).first.text()
                aPrice = new BigDecimal(price);
                Product product = new Product(title, description, size, aPrice);
                products.addProduct(product);
            }

            // do we have more products to get
            String nextPageURl = getNextPage(paginationSectionList);
            getProducts(nextPageURl);

        } catch ( IOException io) {
            System.out.println("Exception - could not retrieve products" + io.getStackTrace());
            products = null;
        }
        return products;

    }


    // assumption that the first returned element is the description
    private String getDetailedDescription (Connection.Response response) throws  IOException{
        Document doc = getDocumentHelper(response);
        Element el = doc == null ? null : doc.select(PRODUCT_LINK_TEXT).first();
        return el == null ? null : el.ownText();
    }

    //assumption that the next class will always have a URL if there are more pages
    private String getNextPage(Elements pagination) {
        Element nextPageEl = pagination.select("a").first();
        return nextPageEl == null ? null : nextPageEl.attr("href");
    }



    // assumption in that there is only one attribute with the link that is to the details page
    private String getLinkedDocumentURL (Elements productInfo) {
        Element linkedDoc = productInfo.select("a").first();
        return linkedDoc == null ? null : linkedDoc.attr("href");

    }

    private Document getDocument(Connection.Response response) throws IOException {
        return response.parse();
    }

    public Connection.Response execute(String url) throws  IOException{
        return Jsoup.connect(url).execute();
    }

    public String getSizeInKB (Connection.Response response) {
        byte [] bytes = response.bodyAsBytes();
        return bytes == null ? null : bytes.length/KB_BYTES + "kb";
    }

    private Elements getProductInfo(Element productInner) {
        return productInner.select(PRODUCT_INFO_H3);
    }

    private String getPricePerUnit(Element productInner) {
       Element pricePerUnit = productInner.select(PRODUCT_PRICE_PER_UNIT_CLASS).first();
       return pricePerUnit == null ? null : pricePerUnit.ownText();
    }

    private Elements getProductInfoIncPriceElements(Element productLister) {
        return productLister.select(PRODUCTS_INNER);
    }

    private Elements getPaginationElement(Element productLister) {
        return productLister.select(PAGINATION_CLASS);
    }

    private Element getProductListElement(Document doc) {
       return doc.getElementById(PRODUCT_LISTER_ID);
    }

    // helper class for testing purposes
    public Document getDocumentHelper(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public Document getDocumentHelper(Connection.Response response) throws IOException {
        return response.parse();
    }



}

Overview: 
I have made several assumptions including pagination. 


Building

Maven is the build tool. 
   mvn clean compile assembly:single
   
Execute with:

    java -jar target/Sainsburys-Scraper-1.0-SNAPSHOT-jar-with-dependencies.jar

Dependencies

 * Junit
 * JSoup
 * Jackson Faster XML
 * Mockito to mock the classes for non integration tests

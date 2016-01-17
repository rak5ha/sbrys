Overview: 
I have made several assumptions including pagination. 
The class needs to be refactored to be able to test using PowerMockito but I ran out of time so only basic integration testing has been done.

Building

Maven is the build tool. 
   mvn clean compile assembly:single
    mvn test

	
Execute with:

    java -jar target/Sainsburys-Scraper-1.0-SNAPSHOT-jar-with-dependencies.jar

Dependencies

 * Junit
 * JSoup
 * Jackson Faster XML

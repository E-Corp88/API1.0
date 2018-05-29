package SAX_Parser;
import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXTest {
	
	static final String FILEPATH="src/SAX_Parser/Order.xml";
	
	public static void main(String[] args) {
	    try
	    {
	    	SAXParserFactory factory = SAXParserFactory.newInstance();
	    	SAXParser saxParser = factory.newSAXParser();

	    	DefaultHandler handler = new ProductHandler();
	    	saxParser.parse(new File(FILEPATH), handler);
	    }
	    catch(SAXException se)
	    {
	      System.out.println("SAX-Fehler: " + se.getMessage());
	    }
	    catch(Exception e)
	    {
	      System.out.println("Unbekannter Fehler: " + e.getMessage());
	    }
	 		
	}

}

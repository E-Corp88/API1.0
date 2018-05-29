package SAX_Parser;

import java.util.Arrays;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProductHandler extends DefaultHandler {

	StringBuffer characters = new StringBuffer();
	double preis;
	int menge;
	double productsum;
	double productsum2;

	public void startElement(String namespaceURI, String localValue, String qName, Attributes atts)
			throws SAXException {

		switch (qName) {
		case "Order":
			System.out.println(qName);
			for (int x = 0; x < atts.getLength(); x++) {
				System.out.println(atts.getQName(x) + ": " + atts.getValue(x));
			}

			break;

		case "OrderID":
			System.out.print(qName + ": ");

			break;

		case "Productname":
			System.out.print(qName + ": ");
			break;

		case "Price":
			System.out.print(qName + ": ");
			break;

		case "Quantity":
			System.out.print(qName + ": ");
			break;

		case "Totalsum":
			System.out.print(qName + ": ");
			break;
		}

	}

	public void characters(char[] ch, int start, int length) throws SAXException {

		characters.append(Arrays.copyOfRange(ch, start, start + length));
	}

	public void endElement(String namespaceURI, String localValue, String qName) throws SAXException {
		switch (characters.toString()) {

		case "4711":
			System.out.println(characters);
			System.out.println("");
			break;

		case "Zuendkerzen A111":
			System.out.println(characters);
			break;

		case "10.00":
			System.out.println(characters);
			preis = Double.parseDouble(characters.toString());
			break;

		case "300":
			System.out.println(characters);
			menge = Integer.parseInt(characters.toString());
			productsum = preis * menge;
			System.out.println("Product Sum: " + productsum);
			System.out.println("");
			break;

		case "ABS X111":
			System.out.println(characters);
			break;

		case "1000.00":
			System.out.println(characters);
			preis = Double.parseDouble(characters.toString());
			break;

		case "50":
			System.out.println(characters);
			menge = Integer.parseInt(characters.toString());
			productsum2 = preis * menge;
			System.out.println("Product Sum: " + productsum2);
			System.out.println("");
			System.out.println("Totalsum: " + (productsum + productsum2));
			break;

		}

		characters = new StringBuffer();
	}
}

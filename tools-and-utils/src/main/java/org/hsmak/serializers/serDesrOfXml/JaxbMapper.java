package org.hsmak.serializers.serDesrOfXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;

public class JaxbMapper {

    public static String XML_BOOK =
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                    "<book id=\"1\">\n" +
                    "    <title>Book1</title>\n" +
                    "    <date>2016-11-12T11:25:12.227+07:00</date>\n" +
                    "</book>";

    public static void main(String[] args) throws JAXBException {
        Book book = unmarshallBook();
        System.out.println(book);
    }

    /**
     * Observation:
     * - JAXBContext is Threadsafe
     * - Marshallers/Unmarshallers are NOT Threadsafe
     *
     * @return
     * @throws JAXBException
     */
    public static JAXBContext getJaxbContextOfBook() throws JAXBException {
        return JAXBContext.newInstance(Book.class);
    }

    public static Book unmarshallBook() throws JAXBException {
        return (Book) getJaxbContextOfBook().createUnmarshaller()
                .unmarshal(new ByteArrayInputStream(XML_BOOK.getBytes()));
    }

}

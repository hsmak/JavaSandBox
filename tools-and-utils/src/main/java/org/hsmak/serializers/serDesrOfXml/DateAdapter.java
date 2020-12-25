package org.hsmak.serializers.serDesrOfXml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    private static final ThreadLocal<DateTimeFormatter> threadLocalDTF = new ThreadLocal<DateTimeFormatter>() {

        @Override
        protected DateTimeFormatter initialValue() {
            return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
    };

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v, threadLocalDTF.get());
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return threadLocalDTF.get().format(v);
    }
}
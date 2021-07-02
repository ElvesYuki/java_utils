package com.elvesyuki.javautils.normal.utils;

import com.elvesyuki.javautils.normal.dto.XmoException;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author ：luohuan
 * @date ：Created in 2021/7/2 上午10:28
 * @description：
 * @modified By：
 */
@Component
public class HashXmlUtils {

    /**
     * This marshal method will traverse the provided object checking for field annotations in order
     * to compose the XML data.
     */
    public static String marshal(Object source) throws XmoException {
        try {
            Serializer serializer = new Persister(new AnnotationStrategy(), new Format(0));
            StringWriter writer = new StringWriter();
            serializer.write(source, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new XmoException(e);
        }
    }

    /**
     * This unmarshal method will read the contents of the XML document from the provided source and
     * populate the object with the values deserialized.
     */
    public static <T> T unmarshal(Class<? extends T> type, Reader source) throws XmoException {
        try {
            Serializer serializer = new Persister(new AnnotationStrategy());
            return serializer.read(type, source);
        } catch (Exception e) {
            throw new XmoException(e);
        }
    }

    /**
     * This unmarshal method will read the contents of the XML document from the provided source and
     * populate the object with the values deserialized.
     */
    public static <T> T unmarshal(Class<? extends T> type, String source) throws XmoException {
        try {
            Serializer serializer = new Persister(new AnnotationStrategy());
            return serializer.read(type, new StringReader(source));
        } catch (Exception e) {
            throw new XmoException(e);
        }
    }

    /**
     * This validate method will validate the contents of the XML document against the specified XML
     * class schema.
     */
    public static boolean validate(Class type, String source) throws XmoException {
        try {
            Serializer serializer = new Persister(new AnnotationStrategy());
            return serializer.validate(type, source);
        } catch (Exception e) {
            throw new XmoException(e);
        }
    }

}

package com.emc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
 
import org.apache.lucene.document.Document;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
 
public class PDFParse {
 
    public static void main(String[] args){
        InputStream is = null;
 
        try {
            is = new BufferedInputStream(new FileInputStream(new File("State Bank of India.pdf")));
            
            Document doc = new Document();
            
            
            Parser parser = new AutoDetectParser();
            String content="";
            ContentHandler handler = new BodyContentHandler();
            
            
            
            
            
            

            Metadata metadata = new Metadata();
            
            System.out.println("Parser starts");
            parser.parse(is, handler, metadata, new ParseContext());
            
            ContentHandlerDecorator cd = new ContentHandlerDecorator(handler);
            content = cd.toString();
            
            
            
           
           // String content = parser.toString();
            System.out.println("the content is \t" + content);
            System.out.println("Parser ends");
            for (String name : metadata.names()) {
                String value = metadata.get(name);
 
                if (value != null) {
                    System.out.println("Metadata Name:  " + name);
                    System.out.println("Metadata Value: " + value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
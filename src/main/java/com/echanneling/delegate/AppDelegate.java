package com.echanneling.delegate;

import com.echanneling.model.Constants;
import com.echanneling.model.ExceptionDetails;
import com.echanneling.service.support.CatchException;
import com.echanneling.service.support.MailInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author shalithasenanayaka on 2019-08-10 using IntelliJ IDEA
 */
public class AppDelegate {

    /**
     * All delegate methods
     * */

    //private static final Logger LOGGER = Logger.getLogger( AppDelegate.class.getName() );
    final static Logger logger = LogManager.getLogger(AppDelegate.class);
    public static final Properties properties = new Properties();

    private static HashMap<String,String> QueryParams=new HashMap<String, String>();

    public static ServletContext sContext;

    static {
        try {

            // Read the property only once when load the class
            InputStream inputStream = AppDelegate.class.getResourceAsStream(Constants.PROPERTY_FILE);
            properties.load(inputStream);

        } catch (IOException e) {
            CustomExceptionHandle("IOException",e);
        }
    }

    public static void Init(ServletContext context){

        //run all delegates
        sContext = context;
        System.out.println(AppParams.InitParam  +" the App delegate");
        InstantiateXMLFile();


    }

    //write log files
    public static void CustomExceptionHandle(String type, Exception e){
        if(logger.isDebugEnabled()){
            logger.debug("This is debug");
        }
        //logs an error message with parameter
        logger.error("This is error : " + e.getMessage());

        final String message = e.getMessage();
        final String stackTrace = e.getStackTrace().toString();
        final String ExceptionType = type;

        //execute mail in background
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.submit(new Runnable() {
            public void run() {

                try{
                    DateFormat dateFormat = new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT);
                    Date date = new Date();

                    //create exception object
                    ExceptionDetails exceptionDetails = new ExceptionDetails(dateFormat.format(date), ExceptionType , message, stackTrace);

                    //initialize and send the mail
                    MailInitializer.InitAndSendErrorMessage(exceptionDetails);

                }catch (AddressException e){
                    logger.error("This is error : " + e);
                }
                catch (MessagingException e){
                    logger.error("This is error : " + e);
                }

            }
        });
    }
    //read all  system config xml file
    public static void InstantiateXMLFile() {
        try{
            String webRoot = sContext.getRealPath("");

            File xmlFile = new File(webRoot + "/" + Constants.CONFIG_XML);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList Queries = doc.getElementsByTagName(Constants.XML_ROOT_NODE_QURIES);
            NodeList QueryList = Queries.item(0).getChildNodes();

            for(int i = 0; i < QueryList.getLength(); i++) {
                Node current = QueryList.item(i);
                //Only want stuff from ELEMENT nodes
                if(current.getNodeType() == Node.ELEMENT_NODE) {
                    QueryParams.put(((Element) current).getAttribute("id"), current.getTextContent());
                }
            }

        }catch (ParserConfigurationException | IOException| SAXException e){
            CatchException.Handle(e);
        }
    }

    //get sql query by id
    public static String GetSQLQuery(String key){
        return QueryParams.get(key);
    }



}

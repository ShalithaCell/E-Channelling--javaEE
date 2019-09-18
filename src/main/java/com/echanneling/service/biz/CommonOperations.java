package com.echanneling.service.biz;

import com.echanneling.model.Operations;
import com.echanneling.model.structure.TempUser;
import org.javatuples.Triplet;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author shalithasenanayaka on 2019-08-11 using IntelliJ IDEA
 */
public class CommonOperations extends Operations {

    /**
     *  All common logical operations are listed here
     */

    //get file content from path
    public static String readString(InputStream inputStream) throws IOException {

        ByteArrayOutputStream into = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        for (int n; 0 < (n = inputStream.read(buf));) {
            into.write(buf, 0, n);
        }
        into.close();
        return new String(into.toByteArray(), "UTF-8"); // Or whatever encoding
    }

    public static String InitSQLProcedure(String procedureName, Triplet<String, Object ,Boolean>[] paramSet){
        StringBuilder strBuilder = new StringBuilder("{call " + procedureName + " (");

        for(int i = 0; i < paramSet.length ; i++){

            if(i == (paramSet.length - 1))
                strBuilder.append("?");
            else
                strBuilder.append("?,");
        }

        strBuilder.append(")}");

        return strBuilder.toString();
    }

    public static String GetSaltString(int size) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static Date getCurrentLocalDateTime(){
        Date date = new Date();

        return date;
    }

    public static String getURLQueryValue(String URL, String key){

        String returnString = "";

        Map<String, String> queryParams = getQueryMap(URL);

        if(queryParams.containsKey(key)){
            returnString = queryParams.get(key);
        }

        return  returnString;
    }

    public static Map<String, String> getQueryMap(String query)
    {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public static List<Object> PopulateDataListFromJTable(Object objectInstance, JTable table) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        /**
         *  objectInstance - object's class attributes must be public
         */

        List<Object> list = new ArrayList<>();
        Class  aClass = objectInstance.getClass();

        for(int row = 0; row < table.getRowCount(); row++){

            Object newObject = objectInstance.getClass().newInstance();

            for(int column = 0; column < table.getColumnCount(); column++){
                String columnName = table.getColumnName(column);
                Object jtableValue = table.getValueAt( row, table.getColumn(columnName).getModelIndex() );


                try{
                    Field field = aClass.getField(columnName);


                    Object value = jtableValue;

                    field.set(newObject, value);

                }catch (Exception e){
                    continue;
                }

            }

            list.add(newObject);
        }
        return  list;


    }

}

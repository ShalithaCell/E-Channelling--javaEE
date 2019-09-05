package com.echanneling.service.biz;

import com.echanneling.model.Operations;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Types;

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

}

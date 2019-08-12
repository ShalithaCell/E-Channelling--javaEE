package com.echanneling.service.biz;

import com.echanneling.model.Operations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
}

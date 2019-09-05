package com.echanneling.service.biz;

import com.echanneling.DAL.CDataAccess;
import org.javatuples.Quartet;
import org.javatuples.Triplet;


import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

/**
 * @author shalithasenanayaka on 2019-09-03 using IntelliJ IDEA
 */
public class UserManagementService {

    public static boolean checkUserEmailIsExists(String email){

        Triplet<String, Object, Boolean>[] paramSet = new Triplet[2];
        Triplet<String, Object, Boolean> paramSet1 = new Triplet<String, Object, Boolean>("UserEmail", "shalithax@gmail.com", false);
        Triplet<String, Object, Boolean> paramSet2 = new Triplet<String, Object, Boolean>("result", "5", true);
        paramSet[0] = paramSet1;
        paramSet[1] = paramSet2;


        try {
            HashMap<String, String> params = CDataAccess.ExecuateProcedure("SP_CHECK_USER_EMAIL_EXISTS", paramSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

}

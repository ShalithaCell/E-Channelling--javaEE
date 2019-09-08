package com.echanneling.model;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shalithasenanayaka on 2019-09-05 using IntelliJ IDEA
 */
public class ProcedureParams {
    private int counter = 0;
    private List<Triplet<String, Object, Boolean>> paramList = new ArrayList<>();

    public ProcedureParams() {
        paramList.clear();
    }

    public void setParamSet(String paramName, Object value, Boolean isOutPut){
        Triplet<String, Object, Boolean> newTriplet = new Triplet<>(paramName, value, isOutPut);
        paramList.add(newTriplet);
        counter++;
    }

    public Triplet<String, Object, Boolean>[] getParamSet(){
        Triplet<String, Object, Boolean>[] paramSet = new Triplet[counter];
        int count = 0;
        for (Triplet<String, Object, Boolean> item: paramList) {
            Triplet<String, Object, Boolean> newTriplet = new Triplet<>(item.getValue0(), item.getValue1(), item.getValue2());
            paramSet[count++] = newTriplet;
        }

        return paramSet;
    }
}

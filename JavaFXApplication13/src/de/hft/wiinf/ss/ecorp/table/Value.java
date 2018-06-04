/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hft.wiinf.ss.ecorp.table;

import javafx.beans.property.SimpleStringProperty;

public class Value {

    public final SimpleStringProperty time;
    public final SimpleStringProperty value1;
    public final SimpleStringProperty value2;
    public final SimpleStringProperty value3;

    public Value(String Xtime, String Xvalue1, String Xvalue2, String Xvalue3) {
        this.time = new SimpleStringProperty(Xtime);
        this.value1 = new SimpleStringProperty(Xvalue1);
        this.value2 = new SimpleStringProperty(Xvalue2);
        this.value3 = new SimpleStringProperty(Xvalue3);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String fName) {
        time.set(fName);
    }

    public String getValue1() {
        return value1.get();
    }

    public void setValue1(String fName) {
        value1.set(fName);
    }

    public String getValue2() {
        return value2.get();
    }

    public void setValue2(String fName) {
        value2.set(fName);
    }

    public String getValue3() {
        return value3.get();
    }

    public void setValue3(String fName) {
        value3.set(fName);
    }

}

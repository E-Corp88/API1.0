
package de.hft.wiinf.ss.ecorp.table;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * 
 */
public class Value {

    /**
     * value which is needed to fill a table
     */
    public final SimpleStringProperty time;

    /**
     * value which is needed to fill a table
     */
    public final SimpleStringProperty value1;

    /**
     * value which is needed to fill a table
     */
    public final SimpleStringProperty value2;

    /**
     *value which is needed to fill a table
     */
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

    public void setTime(String value) {
        time.set(value);
    }

    public String getValue1() {
        return value1.get();
    }

    public void setValue1(String value) {
        value1.set(value);
    }

    public String getValue2() {
        return value2.get();
    }

    public void setValue2(String value) {
        value2.set(value);
    }

    public String getValue3() {
        return value3.get();
    }

    public void setValue3(String value) {
        value3.set(value);
    }

    @Override
    public String toString() {
        return "Value{" + "time=" + time + ", value1=" + value1 + ", value2=" + value2 + ", value3=" + value3 + '}';
    }

}

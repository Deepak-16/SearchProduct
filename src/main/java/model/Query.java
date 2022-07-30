package model;

import java.util.ArrayList;
import java.util.List;

public class Query {

    String key;
    String value;

    ///

    //inner list contains AND conditions, outer list denotes OR
    //Only leaf will contain key, value.
    public Query left;
    public Query right;
    public QueryType queryType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

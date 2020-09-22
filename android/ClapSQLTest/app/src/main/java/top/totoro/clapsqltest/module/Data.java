package top.totoro.clapsqltest.module;

import top.totoro.sql.clap.SQLBean;

public class Data extends SQLBean {

    public String value;

    public Data() {
    }

    public Data(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isSame(Object another) {
        return true;
    }
}

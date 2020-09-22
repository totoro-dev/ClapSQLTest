package top.totoro.clapsqltest.module;

import com.google.gson.Gson;

import top.totoro.sql.clap.SQLBatch;
import top.totoro.sql.clap.SQLService;

public class DataSQL extends SQLService<Data> {

    public static final String DB_NAME = "test";
    public static final String TABLE_NAME = "data";
    private static final Gson gson = new Gson();

    private static DataSQL instance;
    private SQLBatch<Data> batch;

    public synchronized static DataSQL getInstance() {
        if (instance == null) {
            synchronized (DataSQL.class) {
                if (instance == null) {
                    instance = new DataSQL(DB_NAME);
                    instance.createTable(TABLE_NAME);
                }
            }
        }
        return instance;
    }

    public SQLBatch<Data> getBatch() {
        return batch;
    }


    public DataSQL(String dbName) {
        super(dbName);
        batch = new SQLBatch<>(this);
    }

    @Override
    public String encoderRow(Data bean) {
        return gson.toJson(bean);
    }

    @Override
    public Data decoderRow(String line) {
        return gson.fromJson(line, Data.class);
    }
}

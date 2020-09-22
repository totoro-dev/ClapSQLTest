package top.totoro.clapsqltest.presenter;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import top.totoro.clapsqltest.module.Data;
import top.totoro.clapsqltest.module.DataListAdapter;
import top.totoro.clapsqltest.module.DataSQL;
import top.totoro.clapsqltest.view.MainView;

public class MainPresenter {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private MainView mainView;
    private DataListAdapter dataListAdapter;
    private List<Data> dataList = Collections.emptyList();
    private Data data = new Data();

    @Inject
    public MainPresenter(MainView mainView) {
        Log.d(TAG, "MainPresenter: new");
        this.mainView = mainView;
    }

    public void onCreate() {
        dataListAdapter = new DataListAdapter(this);
    }

    public DataListAdapter getDataListAdapter() {
        return dataListAdapter;
    }

    public void setDataList() {
        dataListAdapter.setDataList(dataList);
    }

    // 请求数据，也就是获取当前数据库的内容
    public void requestData() {
        // 同步模式
//        List<Data> list = DataSQL.getInstance().selectAll(DataSQL.TABLE_NAME);
//        dataListAdapter.setDataList(list);

        // 批处理模式
        DataSQL.getInstance().getBatch()
                .selectBatch(DataSQL.TABLE_NAME
                        , data -> true
                        , list -> {
                            dataList = list;
                            mainView.refreshView();
                        });
    }

    public void insertData() {
//        DataSQL.getInstance().insert(DataSQL.TABLE_NAME, data);
//        requestData();

        DataSQL.getInstance().getBatch()
                .insertBatch(DataSQL.TABLE_NAME
                        , Collections.singletonList(data)
                        , b -> requestData());
    }

    public void updateData() {
//        DataSQL.getInstance().updateByKey(DataSQL.TABLE_NAME, data);
//        requestData();

        DataSQL.getInstance().getBatch()
                .updateBatch(DataSQL.TABLE_NAME
                        , data -> data.key.equals(this.data.key)
                        , data -> {
                            data.setValue(this.data.value);
                            return data;
                        }
                        , b -> requestData());
    }

    public void deleteData() {
//        DataSQL.getInstance().deleteByKey(DataSQL.TABLE_NAME, data.key);
//        requestData();

        Log.d(TAG, "deleteData: data = " + data.key);
        DataSQL.getInstance().getBatch().deleteBatch(DataSQL.TABLE_NAME
                , data -> data.key.equals(this.data.key)
                , (b) -> requestData());
    }

    public void showUpdateDialog() {
        mainView.showUpdateDialog(data);
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}

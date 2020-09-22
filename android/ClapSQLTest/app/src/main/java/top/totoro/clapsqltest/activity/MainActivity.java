package top.totoro.clapsqltest.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.totoro.clapsqltest.R;
import top.totoro.clapsqltest.component.DaggerMainActivityComponent;
import top.totoro.clapsqltest.dialog.InsertDialog;
import top.totoro.clapsqltest.dialog.UpdateDialog;
import top.totoro.clapsqltest.module.Data;
import top.totoro.clapsqltest.module.MainModule;
import top.totoro.clapsqltest.presenter.MainPresenter;
import top.totoro.clapsqltest.view.MainView;

/**
 * 使用 Dagger2 + ButterKnife + ClapSQL + MVP 的项目测试
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.rv_data_list)
    public RecyclerView dataList;

    @Inject
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // 注入MainPresenter
        DaggerMainActivityComponent
                .builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        mainPresenter.onCreate();

        dataList.setLayoutManager(new LinearLayoutManager(this));
        dataList.setAdapter(mainPresenter.getDataListAdapter());
        mainPresenter.requestData();

        findViewById(R.id.insert).setOnClickListener(v -> {
            showInsertDialog();
        });
    }

    @Override
    public void refreshView() {
        // 可能是在批处理的子线程中触发的界面刷新
        runOnUiThread(() -> {
            mainPresenter.setDataList();
        });
    }

    @Override
    public void showInsertDialog() {
        InsertDialog insertDialog = new InsertDialog(mainPresenter);
        insertDialog.show(getSupportFragmentManager(), "INSERT_DATA_DIALOG");
    }

    @Override
    public void showUpdateDialog(Data oldData) {
        Bundle bundle = new Bundle();
        bundle.putString("key", oldData.key);
        bundle.putString("value", oldData.value);
        UpdateDialog updateDialog = new UpdateDialog(mainPresenter, bundle);
        updateDialog.show(getSupportFragmentManager(), "UPDATE_DATA_DIALOG");
    }

    @Override
    public void inserted() {

    }

    @Override
    public void updated() {

    }

    @Override
    public void deleted() {

    }

}
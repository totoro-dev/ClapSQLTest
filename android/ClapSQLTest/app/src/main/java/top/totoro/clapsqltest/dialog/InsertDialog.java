package top.totoro.clapsqltest.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.totoro.clapsqltest.R;
import top.totoro.clapsqltest.module.Data;
import top.totoro.clapsqltest.presenter.MainPresenter;

public class InsertDialog extends DialogFragment {

    private MainPresenter mainPresenter;

    @BindView(R.id.data_key)
    public EditText key;
    @BindView(R.id.data_value)
    public EditText value;

    public InsertDialog(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_data_insert, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.cancel})
    public void cancel(View v) {
        dismiss();
    }

    @OnClick({R.id.confirm})
    public void confirm(View v) {
        mainPresenter.setData(new Data(key.getText().toString(), value.getText().toString()));
        mainPresenter.insertData();
        dismiss();
    }
}

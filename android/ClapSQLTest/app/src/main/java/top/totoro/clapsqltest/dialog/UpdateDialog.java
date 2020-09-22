package top.totoro.clapsqltest.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.totoro.clapsqltest.R;
import top.totoro.clapsqltest.module.Data;
import top.totoro.clapsqltest.presenter.MainPresenter;

public class UpdateDialog extends DialogFragment {

    private MainPresenter mainPresenter;

    @BindView(R.id.data_key)
    public TextView key;
    @BindView(R.id.data_value)
    public EditText value;

    private String keyText = "";
    private String valueText = "";

    public UpdateDialog(MainPresenter mainPresenter, Bundle bundle) {
        this.mainPresenter = mainPresenter;
        if (bundle != null) {
            keyText = bundle.getString("key");
            valueText = bundle.getString("value");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_data_update, container, false);
        ButterKnife.bind(this, view);
        key.setText(keyText);
        value.setText(valueText);
        return view;
    }

    @OnClick({R.id.cancel})
    public void cancel(View v) {
        dismiss();
    }

    @OnClick({R.id.confirm})
    public void confirm(View v) {
        mainPresenter.setData(new Data(key.getText().toString(), value.getText().toString()));
        mainPresenter.updateData();
        dismiss();
    }
}

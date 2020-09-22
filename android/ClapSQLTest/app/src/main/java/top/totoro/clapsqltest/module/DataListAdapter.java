package top.totoro.clapsqltest.module;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.totoro.clapsqltest.R;
import top.totoro.clapsqltest.presenter.MainPresenter;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {

    private static final String TAG = DataListAdapter.class.getSimpleName();
    private List<Data> dataList;
    private MainPresenter mainPresenter;

    public DataListAdapter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, String.format("onCreateViewHolder: viewType = %d", viewType));
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_data_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, String.format("onBindViewHolder: position = %d", position));
        Data data = dataList.get(position);
        holder.key.setText(data.getKey());
        holder.value.setText(data.getValue());
        holder.update.setOnClickListener((v) -> {
            mainPresenter.setData(new Data(holder.key.getText().toString(), holder.value.getText().toString()));
            mainPresenter.showUpdateDialog();
        });
        holder.delete.setOnClickListener((v) -> {
            mainPresenter.setData(new Data(holder.key.getText().toString(), holder.value.getText().toString()));
            mainPresenter.deleteData();
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, String.format("getItemCount: count = %d", (dataList == null ? 0 : dataList.size())));
        return dataList == null ? 0 : dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.data_key)
        public TextView key;
        @BindView(R.id.data_value)
        public TextView value;
        @BindView(R.id.data_update)
        public Button update;
        @BindView(R.id.data_delete)
        public Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

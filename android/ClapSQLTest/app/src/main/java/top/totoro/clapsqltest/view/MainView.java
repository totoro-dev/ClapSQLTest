package top.totoro.clapsqltest.view;

import top.totoro.clapsqltest.module.Data;

public interface MainView {

    void refreshView();

    void showInsertDialog();

    void showUpdateDialog(Data oldData);

    void inserted();

    void updated();

    void deleted();

}

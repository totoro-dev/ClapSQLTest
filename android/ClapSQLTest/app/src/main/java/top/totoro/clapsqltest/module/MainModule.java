package top.totoro.clapsqltest.module;

import dagger.Module;
import dagger.Provides;
import top.totoro.clapsqltest.presenter.MainPresenter;
import top.totoro.clapsqltest.view.MainView;

@Module
public class MainModule {

    private MainView mainView;

    public MainModule(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter(mainView);
    }
}

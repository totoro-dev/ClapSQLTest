package top.totoro.clapsqltest.component;

import dagger.Component;
import top.totoro.clapsqltest.activity.MainActivity;
import top.totoro.clapsqltest.module.MainModule;

@Component(modules = {MainModule.class})
public interface MainActivityComponent {
    void inject(MainActivity activity);
}

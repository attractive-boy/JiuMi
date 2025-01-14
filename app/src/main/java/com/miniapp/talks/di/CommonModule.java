package com.miniapp.talks.di;

import com.jess.arms.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    public CommonModule() {

    }

    @Provides
    @ActivityScope
    static List<String> provideUserList() {
        return new ArrayList<>();
    }


}

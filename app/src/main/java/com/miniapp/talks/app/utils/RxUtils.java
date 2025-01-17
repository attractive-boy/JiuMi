/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.miniapp.talks.app.utils;

import android.app.Activity;

import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.RxLifecycleUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * ================================================
 * 放置便于使用 RxJava 的一些工具方法
 * <p>
 * Created by JessYan on 11/10/2016 16:39
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class RxUtils {

    private RxUtils() {
    }

    public static <T> ObservableTransformer<T, T> applySchedulers(final IView view) {
        return new ObservableTransformer<T, T>() {
            @Override
            public Observable<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(@NonNull Disposable disposable) throws Exception {
                                view.showLoading();//显示进度条
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally(new Action() {
                            @Override
                            public void run() {
                                view.hideLoading();//隐藏进度条
                            }
                        }).compose(RxLifecycleUtils.bindToLifecycle(view));
            }
        };
    }

    /**
     * 此方法已废弃
     *
     * @param view
     * @param <T>
     * @return
     * @see RxLifecycleUtils 此类可以实现 {@link RxLifecycle} 的所有功能, 使用方法和之前一致
     * @deprecated Use {@link RxLifecycleUtils#bindToLifecycle(IView)} instead
     */
    @Deprecated
    public static <T> LifecycleTransformer<T> bindToLifecycle(IView view) {
        return RxLifecycleUtils.bindToLifecycle(view);
    }

    /**
     * 公用带加载框的网络请求方法
     *
     * @param observable
     * @param view
     * @param <T>
     * @return
     */
    public static <T> Observable<T> loading(Observable<T> observable, final IView view) {
        Activity context = AppManager.getAppManager().getTopActivity();
//        CommonDialog loadingDialog = CommonDialog.loading(context);
        return observable
                .subscribeOn(Schedulers.io())
                //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
//                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    try {
//                        if (loadingDialog != null && loadingDialog.getContext() != null) {
//                            loadingDialog.show();
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    try {
//                        if (loadingDialog != null && loadingDialog.getContext() != null) {
//                            loadingDialog.cancel();
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(view));
    }

    /**
     * 不绑定生命周期
     * @param observable
     * @param <T>
     * @return
     */
    public static <T> Observable<T> loading(Observable<T> observable) {
        Activity context = AppManager.getAppManager().getTopActivity();
        return observable
                .subscribeOn(Schedulers.io())
                //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
//                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 同步调用
     * @param observable
     * @param view
     * @param <T>
     * @return
     */
//    public static <T> Observable<T> loadingTongbu(Observable<T> observable, final IView view) {
//        Observable.fromCallable(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        }).compose(RxLifecycleUtils.bindToLifecycle(view));
//        return observable
//                .subscribeOn(Schedulers.io())
//                //遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
////                .retryWhen(new RetryWithDelay(3, 2))
//                .doOnSubscribe(disposable -> {
//
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doFinally(() -> {
//
//                })
//                .compose(RxLifecycleUtils.bindToLifecycle(view));
//    }
}

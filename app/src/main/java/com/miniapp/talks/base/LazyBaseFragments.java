package com.miniapp.talks.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 懒加载fragment基类
 */
public abstract class LazyBaseFragments extends BaseFragment {

    /**
     * 是否为可见状态
     */
    protected boolean isVisible;
    /**
     * 是否初始视图完成
     */
    private boolean isPrepared;

    View mRootView;

    Unbinder unbinder;

//    private  boolean mIsFragmentVisible;
//    private  boolean mIsRuseView;
//    private  boolean mIsFirstVisible;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initVariable();
//    }
//
//    private void initVariable() {
//        mIsFirstVisible = true;
//        mIsFragmentVisible = false;
//        mIsRuseView = true;
//        mRootView = null;
//    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView ==null){
            mRootView = getLayoutView(inflater, container, savedInstanceState);
//            if(getUserVisibleHint()){
//                if(mIsFirstVisible){
//                    onFragmentFirstVisible();
//                    mIsFirstVisible = false;
//                }
//                onFragmentVisibleChange(true);
//                mIsFragmentVisible = true;
//            }
        }
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
//        return initView(inflater, container, savedInstanceState);
    }

//    protected void onFragmentFirstVisible(){
//
//    }
//
//    protected void onFragmentVisibleChange(boolean isVisible){
//
//    }


    public  abstract View getLayoutView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        if(mRootView ==null){
//            mRootView = view;
//            unbinder = ButterKnife.bind(this, mRootView);
//            if(getUserVisibleHint()){
//                if(mIsFirstVisible){
//                    visibleToLoadData();
//                    mIsFirstVisible = false;
//                }
//                onFragmentVisibleChange(true);
//                mIsFragmentVisible = true;
//            }
//        }
//        super.onViewCreated(mIsRuseView?mRootView:view, savedInstanceState);
        if(mRootView == null){
            mRootView = view;
        }
        unbinder = ButterKnife.bind(this, mRootView);
        super.onViewCreated(view, savedInstanceState);
        isPrepared = true;
        onVisible();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        initVariable();
//    }

    /**
     * setUserVisibleHint是在onCreateView之前调用的
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if(mRootView == null){
//            return;
//        }
//
//        if(mIsFirstVisible && isVisibleToUser){
//            visibleToLoadData();
//            mIsFirstVisible = false;
//        }
//        if(isVisibleToUser){
//            onFragmentVisibleChange(true);
//            mIsFragmentVisible = true;
//            return;
//        }
//        if(mIsFragmentVisible){
//            mIsFragmentVisible = false;
//            onFragmentVisibleChange(false);
//
//        }
        if (isVisibleToUser) {
//            if(getActivity() != null){
                isVisible = true;
                onVisible();
//            }
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见做懒加载
     */
    private void onVisible() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isVisible && isPrepared) {
            visibleToLoadData();
            //数据加载完毕,恢复标记,防止重复加载
            isVisible = false;
            isPrepared = false;
        }
    }

        /**
         * 不可见-做一些销毁工作
         */
    protected void onInvisible() {


    }

    /**
     * 延迟加载
     */
    protected abstract void visibleToLoadData();


}

package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.TixianContract;
import com.pft.quicktouch.mvp.model.TixianModelImpl;

/**
 * 提现流水P层
 */

public class TixianPresenter extends BasePresenter<TixianContract.TixianView> {

    private TixianModelImpl mTixianModel;

    public TixianPresenter() {
        //实例化M
        mTixianModel = new TixianModelImpl();
    }

    /**
     * 提现流水
     */

    public void getTx(String url) {
        mTixianModel.getTx(url, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().txSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().txError(msg);
            }
        });
    }
}

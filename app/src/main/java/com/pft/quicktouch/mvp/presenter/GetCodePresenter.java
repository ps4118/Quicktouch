package com.pft.quicktouch.mvp.presenter;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.contract.GetCodeContract;
import com.pft.quicktouch.mvp.model.GetCodeModelImpl;

public class GetCodePresenter extends BasePresenter<GetCodeContract.GetCodeView> {

    private GetCodeModelImpl mGetCodeModel;


    public GetCodePresenter() {
        mGetCodeModel = new GetCodeModelImpl();
    }

    public void vofifyCode(String url, String phone, String code) {
        mGetCodeModel.vififyCode(url, phone, code, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().virifySuccess();
            }

            @Override
            public void onError(String msg) {
                getView().virifyError(msg);
            }
        });

    }

    public void getcode(String url, String phone) {
        getView().getCodeStart();
        mGetCodeModel.getCode(url, phone, new ResultCallBack() {
            @Override
            public void onSuccess(String msg) {
                getView().codeSuccess(msg);
            }

            @Override
            public void onError(String msg) {
                getView().codeError(msg);
            }
        });

    }
}

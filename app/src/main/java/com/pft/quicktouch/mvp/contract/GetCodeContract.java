package com.pft.quicktouch.mvp.contract;

import com.pft.quicktouch.interfaces.ResultCallBack;
import com.pft.quicktouch.mvp.view.BaseView;

/**
 * 获取验证码
 */
public class GetCodeContract {
    public interface GetCodeView extends BaseView {
        //开始获取验证码
        void getCodeStart();

        //验证码获取成功
        void codeSuccess(String code);

        //验证码获取失败
        void codeError(String msg);

        //验证成功
        void virifySuccess();

        //验证失败
        void virifyError(String msg);
    }

    public interface GetCodeModel {
        void getCode(String url, String phone, ResultCallBack callBack);

        void vififyCode(String url, String phone, String code, ResultCallBack callBack);
    }
}

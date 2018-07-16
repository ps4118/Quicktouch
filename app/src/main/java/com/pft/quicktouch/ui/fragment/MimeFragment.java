package com.pft.quicktouch.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.dialog.v2.SelectDialog;
import com.pft.quicktouch.R;
import com.pft.quicktouch.interfaces.DialogListener;
import com.pft.quicktouch.tool.SpTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.ui.activity.LoginActivity;
import com.pft.quicktouch.ui.activity.MessageActivity;
import com.pft.quicktouch.ui.activity.TixianActivity;
import com.pft.quicktouch.view.CustomAlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class MimeFragment extends Fragment {
    View view;
    @BindView(R.id.avater)
    CircleImageView avater;
    @BindView(R.id.store)
    TextView store;
    @BindView(R.id.infoRl)
    RelativeLayout infoRl;
    @BindView(R.id.setpassRl)
    RelativeLayout setpassRl;
    @BindView(R.id.posRl)
    RelativeLayout posRl;
    @BindView(R.id.fendanRl)
    RelativeLayout fendanRl;
    @BindView(R.id.tixianRl)
    RelativeLayout tixianRl;
    @BindView(R.id.aboutRl)
    RelativeLayout aboutRl;
    @BindView(R.id.logout)
    TextView logout;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, null);
        }
        initview();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 获取对象
     *
     * @return
     */
    public static MimeFragment newInstance() {
        MimeFragment fragment = new MimeFragment();
        return fragment;

    }

    /**
     * 初始化控件
     */
    private void initview() {

    }

    @OnClick(R.id.avater)
    public void intentInfo() {
        ToastTool.showToast(getContext(), "跳转到个人资料界面");
    }

    @OnClick(R.id.infoRl)
    public void toInfo() {
        ToastTool.showToast(getContext(), "跳转到个人资料界面");
    }

    /**
     * 设置密码
     */
    @OnClick(R.id.setpassRl)
    public void toPass() {

        Intent intent = new Intent(getContext(), MessageActivity.class);
        intent.putExtra("action", "modify");
        getContext().startActivity(intent);
    }

    @OnClick(R.id.posRl)
    public void toPos() {
        ToastTool.showToast(getContext(), "跳转到打印机设置界面");
    }

    @OnClick(R.id.fendanRl)
    public void toFendan() {
        ToastTool.showToast(getContext(), "跳转到分单设置界面");
    }

    @OnClick(R.id.tixianRl)
    public void toTixian() {
        startActivity(new Intent(getContext(), TixianActivity.class));
    }

    @OnClick(R.id.aboutRl)
    public void toAbout() {
        ToastTool.showToast(getContext(), "跳转到关于点得快界面");
    }

    @OnClick(R.id.logout)
    public void logout() {

        SelectDialog.show(getContext(), "退出登录", "确定退出该账户？", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SpTool.clearLoginCount();
                //跳转到登录界面
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

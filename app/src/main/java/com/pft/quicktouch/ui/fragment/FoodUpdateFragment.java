package com.pft.quicktouch.ui.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.kongzue.dialog.listener.InputDialogOkButtonClickListener;
import com.kongzue.dialog.v2.InputDialog;
import com.kongzue.dialog.v2.SelectDialog;
import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.bean.FoodType;
import com.pft.quicktouch.interfaces.BottomDialogListener;
import com.pft.quicktouch.interfaces.DialogListener;
import com.pft.quicktouch.interfaces.FoodUpdateItemListener;
import com.pft.quicktouch.mvp.contract.FoodManagerContract;
import com.pft.quicktouch.mvp.contract.FoodManagerUpdateContract;
import com.pft.quicktouch.mvp.presenter.FoodManagerUpdatePresenter;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.SpTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.ui.activity.AddFoodActivity;
import com.pft.quicktouch.ui.activity.LoginActivity;
import com.pft.quicktouch.ui.adapter.FoodTypeAdapter;
import com.pft.quicktouch.ui.adapter.FoodUpdateAdapter;
import com.pft.quicktouch.view.CustomAlertDialog;
import com.pft.quicktouch.view.CustomBottomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FoodUpdateFragment extends BaseFragment<FoodManagerUpdateContract.FoodManagerUpdateView, FoodManagerUpdatePresenter>
        implements BottomDialogListener, FoodManagerUpdateContract.FoodManagerUpdateView, FoodUpdateItemListener {
    List<FoodType> typeList;
    List<Food> foodList;

    @BindView(R.id.typeLv)
    ListView typeLv;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.addFood)
    TextView addFood;
    @BindView(R.id.foodLv)
    PullToRefreshListView foodLv;
    //操作的菜品下标
    private int choosePosition = 0;
    FoodTypeAdapter mFoodTypeAdapter;
    FoodUpdateAdapter mFoodUpdateAdapter;

    @Override
    protected FoodManagerUpdatePresenter createPresenter() {
        return new FoodManagerUpdatePresenter();
    }

    @Override
    public void initview() {
        mFoodTypeAdapter = new FoodTypeAdapter(getContext(), typeList);
        mFoodUpdateAdapter = new FoodUpdateAdapter(getContext(), foodList, this);
        typeLv.setAdapter(mFoodTypeAdapter);
        foodLv.setAdapter(mFoodUpdateAdapter);
        mFoodTypeAdapter.notifyDataSetChanged();
        mFoodUpdateAdapter.notifyDataSetChanged();
//        typeLv.setSelector(R.color.titilebar_blue);
        typeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeTv.setText(typeList.get(position).getName());
                //设置选中的下标
                mFoodTypeAdapter.setSelectPosition(position);
                //更新视图
                mFoodTypeAdapter.notifyDataSetInvalidated();
            }
        });

        foodLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //设置下拉刷新，上拉加载
        foodLv.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        foodLv.getLoadingLayoutProxy(true, true).setLoadingDrawable(getActivity().getResources().getDrawable(R.drawable.refresh_icon));

        //设置空数据视图
        TextView view = new TextView(getContext());
        view.setText("暂无数据");
        view.setGravity(Gravity.CENTER);
        foodLv.setEmptyView(view);
        foodLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                foodLv.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                foodLv.onRefreshComplete();
            }
        });

    }

    /**
     * 新增类别
     */
    @OnClick(R.id.addType)
    public void addType() {
        InputDialog.show(getContext(), "新增类别", "请输出类别名", new InputDialogOkButtonClickListener() {
            @Override
            public void onClick(Dialog dialog, String inputText) {
                FoodType type = new FoodType();
                type.setName(inputText);
                typeList.add(type);
                mFoodTypeAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }

    /**
     * 新增菜品
     */
    @OnClick(R.id.addFood)
    public void addFood() {
        Intent intent = new Intent(getContext(), AddFoodActivity.class);
        intent.putExtra("action", "add");
        startActivity(intent);

    }

    @Override
    public void initdata() {
        typeList = new ArrayList<>();
        foodList = new ArrayList<>();
        FoodType type = new FoodType();
        type.setId(1);
        type.setName("推荐");
        typeList.add(type);
        FoodType type2 = new FoodType();
        type2.setId(1);
        type2.setName("烧菜");
        typeList.add(type2);
        FoodType type3 = new FoodType();
        type3.setId(1);
        type3.setName("炒菜");
        typeList.add(type3);


        for (int i = 0; i < 5; i++) {
            Food food = new Food();
            food.setName("菜名");
            food.setPrice("单价");
            foodList.add(food);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_food_update;
    }

    /**
     * 获取fragment对象
     *
     * @return
     */
    public static FoodUpdateFragment newInstance() {
        FoodUpdateFragment fragment = new FoodUpdateFragment();
        return fragment;


    }


    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }


    @Override
    public void del(int position) {
        choosePosition = position;
//        CustomAlertDialog alertDialog = new CustomAlertDialog(getContext());
        SelectDialog.show(getContext(), "删除", "是否删除该菜品？", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }

    CustomBottomDialog dialog;

    /**
     * @param position
     */
    @Override
    public void update(int position) {
        Intent intent = new Intent(getContext(), AddFoodActivity.class);
        intent.putExtra("action", "modify");
        getContext().startActivity(intent);
//        dialog = new CustomBottomDialog(getContext(), this);
//        dialog.init();
//        dialog.showDialog();


    }

    @Override
    public void addSale(int position) {
        //先获取当前账户已设置的数量是否已到上限
        if (NetTool.checkNetandWifi(getContext())) {
            //请求当前设置数量
            String url = "http://www.baidu.com";
            mPresenter.addSale(url);

        } else {
            ToastTool.showToast(getContext(), "请检查网络设置");
        }
    }

    @Override
    public void getType(String msg) {

    }

    @Override
    public void delSuccess(String msg) {
        ToastTool.showToast(getContext(), "删除菜品成功");
        //删除数据源对应数据
        foodList.remove(choosePosition);
        //更新视图
        mFoodUpdateAdapter.notifyDataSetChanged();

    }

    @Override
    public void delError(String msg) {
        ToastTool.showToast(getContext(), msg);
    }

    @Override
    public void getFoods(String msg) {

    }

    @Override
    public void getSaleCount(String msg) {
        //如果没有达到上限就商品，
    }

    @Override
    public void getError(String msg) {
        ToastTool.showToast(getContext(), msg);
    }

    //弹窗的操作
    @Override
    public void handle(String action) {
        switch (action) {
            case "加推菜品":
                break;
            case "打折":
                break;
            case "编辑菜品":
                Intent intent = new Intent(getContext(), AddFoodActivity.class);
                intent.putExtra("action", "modify");
                getContext().startActivity(intent);
                break;
        }
    }

    //弹窗的取消
    @Override
    public void cancle() {
        dialog.disDialog();
    }
}

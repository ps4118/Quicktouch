package com.pft.quicktouch.ui.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.pft.quicktouch.R;
import com.pft.quicktouch.bean.Food;
import com.pft.quicktouch.interfaces.FoodManagerItemListener;
import com.pft.quicktouch.mvp.contract.FoodManagerContract;
import com.pft.quicktouch.mvp.presenter.FoodManagerPresenter;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.ui.adapter.FoodManagerAdapter;
import com.pft.quicktouch.view.CustomEmptyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class FoodManagerFragment extends BaseFragment<FoodManagerContract.FoodManagerView, FoodManagerPresenter> implements FoodManagerItemListener, FoodManagerContract.FoodManagerView {
    View view;
    List<Food> list = new ArrayList<Food>();
    @BindView(R.id.typeSpanner)
    Spinner typeSpanner;
    @BindView(R.id.statusSpanner)
    Spinner statusSpanner;
    @BindView(R.id.listview)
    PullToRefreshListView listview;
    Unbinder unbinder;
    public FoodManagerAdapter mAdapter;
    @BindView(R.id.search)
    TextView search;
    Unbinder unbinder1;


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        if (view == null) {
//            view = inflater.inflate(R.layout.fragment_food_manager, null);
//        }
//        unbinder = ButterKnife.bind(this, view);
//        return view;
//    }

    @Override
    protected FoodManagerPresenter createPresenter() {
        return new FoodManagerPresenter();
    }

    @Override
    public void initview() {
        //初始化适配器
        mAdapter = new FoodManagerAdapter(getContext(), list, this, 1);
        listview.setAdapter(mAdapter);
        //设置下拉刷新，上拉加载
        listview.setMode(PullToRefreshBase.Mode.BOTH);
        //设置图标
        listview.getLoadingLayoutProxy(true, true).setLoadingDrawable(getActivity().getResources().getDrawable(R.drawable.refresh_icon));

        //设置空数据视图
//        TextView view = new TextView(getContext());
//        view.setText("暂无数据");
//        view.setGravity(Gravity.CENTER);
        CustomEmptyView view = new CustomEmptyView(getContext());
        listview.setEmptyView(view);
        //视图的点击事件（重新加载数据）
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //设置监听
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新
                listview.onRefreshComplete();
                list.clear();
                mAdapter.notifyDataSetChanged();



            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //上拉加载更多
                listview.onRefreshComplete();
            }
        });
        //设置item点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //类别监听
        typeSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //根据position获取对应选择类别，重新获取数据
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //状态监听
        statusSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //根据position获取对应选择状态，重新获取数据
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initdata() {

//        mPresenter.getList();
        // 模拟数据
        for (int i = 0; i < 3; i++) {
            Food food = new Food();
            food.setName("回锅肉" + i);
            food.setPrice("￥12.99");
            food.setTodayCount(i);
            food.setSum(i * 100);
            food.setStatus(i);
            list.add(food);
        }
        for (int i = 0; i < 3; i++) {
            Food food = new Food();
            food.setName("牛肉面" + i);
            food.setPrice("￥13.99");
            food.setTodayCount(i);
            food.setSum(i * 100);
            food.setStatus(i);
            list.add(food);
        }
        for (int i = 0; i < 3; i++) {
            Food food = new Food();
            food.setName("冰淇淋" + i);
            food.setPrice("￥8.99");
            food.setTodayCount(i);
            food.setSum(i * 100);
            food.setStatus(i);
            list.add(food);
        }


    }

    /**
     * 搜索菜品
     */
    @OnClick(R.id.search)
    public void searchFood() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_food_manager;
    }

    /**
     * 获取fragmen对象
     *
     * @return
     */
    public static FoodManagerFragment newInstance() {
        FoodManagerFragment fragment = new FoodManagerFragment();
        return fragment;

    }

    @Override
    public void getList(String msg) {

    }

    @Override
    public void noMoreData() {

    }

    @Override
    public void updateSuccess(String msg) {

    }

    @Override
    public void updateError(String msg) {

    }

    @Override
    public void onstart() {

    }

    @Override
    public void onfinish() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void youhuo(int position) {
        ToastTool.showToast(getContext(), "点击了" + position + "项的有货");
    }

    @Override
    public void shouqing(int position) {
        ToastTool.showToast(getContext(), "点击了" + position + "项的售罄");
    }

    @Override
    public void xiajia(int position) {
        ToastTool.showToast(getContext(), "点击了" + position + "项的下架");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}

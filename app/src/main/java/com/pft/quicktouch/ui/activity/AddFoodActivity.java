package com.pft.quicktouch.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.pft.quicktouch.R;
import com.pft.quicktouch.mvp.contract.FoodContract;
import com.pft.quicktouch.mvp.presenter.FoodPresenter;
import com.pft.quicktouch.tool.NetTool;
import com.pft.quicktouch.tool.PictureTool;
import com.pft.quicktouch.tool.ToastTool;
import com.pft.quicktouch.view.TitleBar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增菜品页面
 */
public class AddFoodActivity extends BaseActivity<FoodContract.FoodView, FoodPresenter> implements TitleBar.TitleBarClickListener, FoodContract.FoodView {
    //选取照片需要的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @BindView(R.id.addimage)
    ImageView addimage;
    @BindView(R.id.foodname)
    EditText foodname;
    @BindView(R.id.foodprice)
    EditText foodprice;
    @BindView(R.id.typeSpanner)
    Spinner typeSpanner;
    @BindView(R.id.foodintro)
    EditText foodintro;

    @BindView(R.id.titlebar)
    TitleBar titlebar;
    //图片路径
    String imgUrl = "";
    //判断是新增还是更改
    String Action = "add";
    @BindView(R.id.button)
    Button button;

    /**
     * 点击新增菜品按钮执行方法
     *
     * @param view
     */
    public void addFood(View view) {
        String name = foodname.getText().toString().trim();
        String price = foodprice.getText().toString().trim();
        String intro = foodintro.getText().toString();
        if (name.equals("") || price.equals("") || intro.equals("") || imgUrl.equals("")) {
            ToastTool.showToast(AddFoodActivity.this, "请设置完整的信息");
        } else if (NetTool.checkNetandWifi(AddFoodActivity.this) == false) {
            ToastTool.showToast(AddFoodActivity.this, "请检查网络设置");
        } else {
            //提交新增请求
        }
    }

    @Override
    protected FoodPresenter createPresenter() {
        return new FoodPresenter();
    }

    @OnClick(R.id.addimage)
    public void addImage() {
        isPermissions();
    }

    /**
     * 判断是否已经获取到了权限
     */
    public void isPermissions() {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            ActivityCompat.requestPermissions(this, permissions, 321);


        } else {

            //小于23时，自动获取所有权限，跳转至系统相册选取图片
            PictureTool.doPickPhotoFromGallery(AddFoodActivity.this);
        }


    }

    @Override
    protected void initview() {
        Action = getIntent().getStringExtra("action");
        //设置标题和按钮文字
        titlebar.setTitle(Action.equals("add") ? "新增产品" : "编辑菜品");
        button.setText(Action.equals("add") ? "新增" : "更改");
        titlebar.setTopBarClickListener(this);
        typeSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] types = getResources().getStringArray(R.array.foodType_add);
                ToastTool.showToast(AddFoodActivity.this, types[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_food;
    }

    @Override
    public void addSuccess(String msg) {

    }

    @Override
    public void addError(String msg) {

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
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }


    File file;
    public String PHOTO_FILE_NAME = "foodimage.jpg";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureTool.PHOTO_PICKED_WITH_DATA:
                    PictureTool.crop(AddFoodActivity.this, data.getData());
                    break;
                case PictureTool.CAMERA_WITH_DATA:
//                    crop(imageUri);
                    break;
                case PictureTool.PHOTO_CROP:
                    Bundle bundle = data.getExtras();
                    Bitmap myBitmap = (Bitmap) bundle.get("data");
                    // 设置要上传的数据
                    file = new File(PictureTool.PHOTO_DIR, PHOTO_FILE_NAME);
                    imgUrl = file.getPath();
                    PictureTool.saveMyBitmap(myBitmap, file);
                    addimage.setImageBitmap(myBitmap);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
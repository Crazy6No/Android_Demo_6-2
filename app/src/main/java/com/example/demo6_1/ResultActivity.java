package com.example.demo6_1;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.Window;

import androidx.fragment.app.FragmentActivity;

import com.example.demo6_1.R;
import com.example.demo6_1.fragment.SchoolRecruitTrendFragment;



public class ResultActivity extends FragmentActivity {
    private Intent intent;//Intent 对象
    private Bundle bundle;//封装数据的 Bundle 对象
    private String fragmentString;//要显示的 fragment 的名称

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除标题栏
        setContentView(R.layout.activity_result);
        intent = getIntent();//获取传递的 Intent
        fragmentString = intent.getStringExtra("fragment");//获取传递的 fragment 的

        bundle = intent.getBundleExtra("bundle");//获取 Intent 中的数据集
        if (fragmentString.equals("SchoolRecruitTrendFragment")) {//根据 fragment

            SchoolRecruitTrendFragment enrollTrendFragment = new
                    SchoolRecruitTrendFragment();
            enrollTrendFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.resultContent, enrollTrendFragment).commit();
        }
    }

    public void goBack(View view) {//返回按钮的单击事件处理
        this.finish();
    }
}

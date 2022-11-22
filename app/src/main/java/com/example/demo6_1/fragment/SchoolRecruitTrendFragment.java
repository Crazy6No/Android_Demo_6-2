package com.example.demo6_1.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo6_1.R;
import com.example.demo6_1.bean.SchoolRecruit;
import com.example.demo6_1.view.TrendView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class SchoolRecruitTrendFragment extends Fragment {
    private TextView titleView;// 显示标题的文本显示框
    private Gson gson = new Gson();
    private String schoolName;// 学校名称
    private String result;// 录取线查询结果
    private String batchName;// 批次
    private String categoryName;// 类别
    private String studentAreaName;// 生源地
    private List<SchoolRecruit> schoolRecruits;
    private TrendView trendView;//自定义趋势图控件

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trendview, container, false);
        trendView = (TrendView) view.findViewById(R.id.trend);//获取自定义趋势图控件
        trendView.setSchoolRecruits(schoolRecruits);
        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();// 获取传递过来的参数
        if (bundle != null) {
            schoolName = bundle.getString("schoolName");// 从参数中获取学校名称
            result = bundle.getString("schoolRecruitsResult");// 从参数中获取查询结果
            batchName = bundle.getString("batchName");//获取批次
            categoryName = bundle.getString("categoryName");//获取类别
            studentAreaName = bundle.getString("studentAreaName");//获取生源地
            schoolRecruits = gson.fromJson(result,
                    new TypeToken<List<SchoolRecruit>>() {
                    }.getType());//获取学校录取线信息
        }
    }
}

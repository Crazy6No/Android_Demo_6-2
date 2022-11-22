package com.example.demo6_1.fragment;

import com.example.demo6_1.ResultActivity;
import com.google.gson.Gson;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo6_1.R;
import com.example.demo6_1.adapter.Major_recruitAdapter;
import com.example.demo6_1.adapter.School_recruitAdapter;
import com.example.demo6_1.bean.MajorRecruit;
import com.example.demo6_1.bean.School;
import com.example.demo6_1.bean.SchoolRecruit;
import com.example.demo6_1.util.GetSQLite;
import com.example.demo6_1.util.GetService;

import java.util.ArrayList;
import java.util.List;

public class page2Fragment extends Fragment {

    private Spinner categorySpinner, batchSpinner, sourceAreaSpinner,
            yearSpinner, schoolSpinner, schoolAreaSpinner;
    //批次、类别、生源地、年份、学校、学校省份列表
    private RadioButton isSearchByMajor;
    private Button searchBtn;
    private Button trendBtn;
    private TextView titleView;
    private LinearLayout schoolRecruitResultLayout;
    private LinearLayout majorRecruitResultLayout;
    private ListView enrollResultView;

    private String[] categories = new String[]{"文史", "理工", "综合", "艺术", "体育"};
    private String[] batches = new String[]{"一本", "二本", "三本", "专科", "提前"};
    private String[] years = new String[]{"2015", "2014", "2013", "2012", "2011", "2010"};

    private List<String> provinces = new ArrayList<String>();
    private List<School> schoolList;
    private List<String> schools = new ArrayList<String>();

    private int sourceAreaId = 2;
    private int schoolId = 2;
    private int schoolAreaId = 2;

    private int batchId = 1;
    private int categoryId = 1;
    private int yearId = 1;

    private List<SchoolRecruit> schoolRecruits;
    private List<MajorRecruit> majorRecruits;


    private  String schoolName;
    private Gson gson = new Gson();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page2, container, false);

        schoolAreaSpinner = (Spinner) view.findViewById(R.id.schoolAreaSpinner);
        schoolSpinner = (Spinner) view.findViewById(R.id.schoolSpinner);
        sourceAreaSpinner = (Spinner) view.findViewById(R.id.areaSpinner);
        categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        batchSpinner = (Spinner) view.findViewById(R.id.batchSpinner);
        yearSpinner = (Spinner) view.findViewById(R.id.yearSpinner);

        searchBtn = (Button) view.findViewById(R.id.search);

        isSearchByMajor = (RadioButton) view.findViewById(R.id.yes);

        titleView = (TextView) view.findViewById(R.id.title_view);
        trendBtn = (Button) view.findViewById(R.id.trendBtn);

        enrollResultView = (ListView) view.findViewById(R.id.enrollResultList);
        schoolRecruitResultLayout = (LinearLayout) view.findViewById(R.id.schoolEnrollResultLayout);
        majorRecruitResultLayout = (LinearLayout) view.findViewById(R.id.majorEnrollResultLayout);
        majorRecruitResultLayout = (LinearLayout) view.findViewById(R.id.majorEnrollResultLayout);
        majorRecruitResultLayout = (LinearLayout) view.findViewById(R.id.majorEnrollResultLayout);
        majorRecruitResultLayout = (LinearLayout) view.findViewById(R.id.majorEnrollResultLayout);
        majorRecruitResultLayout = (LinearLayout) view.findViewById(R.id.majorEnrollResultLayout);


        GetSQLite getSQLite = new GetSQLite();
        provinces = getSQLite.setProvince();

        ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item_year, provinces);
        sourceAreaSpinner.setAdapter(areaAdapter);
        schoolAreaSpinner.setAdapter(areaAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item_category, categories);
        categorySpinner.setAdapter(categoryAdapter);


        ArrayAdapter<String> batchAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item_batch, batches);
        batchSpinner.setAdapter(batchAdapter);
        batchSpinner.setSelection(batchId - 1);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item_year, years);
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(years.length - 1);

        MyItemSelectedListener myItemSelectedListener = new MyItemSelectedListener();
        schoolAreaSpinner.setOnItemSelectedListener(myItemSelectedListener);
        schoolSpinner.setOnItemSelectedListener(myItemSelectedListener);
        categorySpinner.setOnItemSelectedListener(myItemSelectedListener);
        batchSpinner.setOnItemSelectedListener(myItemSelectedListener);
        yearSpinner.setOnItemSelectedListener(myItemSelectedListener);
        sourceAreaSpinner.setOnItemSelectedListener(myItemSelectedListener);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearchByMajor.isChecked())
                    showMajorRecruitsResult();
                else
                    showSchoolRecruits();
            }
        });
        trendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToShowTrend();
            }

        });


        return view;
    }

    public void goToShowTrend() {// 跳转到显示趋势图的页面

             Bundle bundle = new Bundle();
             String schoolRecruitsResult = gson.toJson(schoolRecruits);
             bundle.putString("schoolRecruitsResult", schoolRecruitsResult);// 学校录取线

             bundle.putString("schoolName", schoolName);// 学校名称
             bundle.putString("batchName",batches[batchId]);// 批次信息
             bundle.putString("categoryName",categories[categoryId]);// 类别信息
             bundle.putString("studentAreaName", provinces.get(sourceAreaId));// 生源地
             Intent intent = new Intent(getActivity(), ResultActivity.class);
             intent.putExtra("bundle", bundle);// 传递数据
             intent.putExtra("fragment", "SchoolRecruitTrendFragment");
             startActivity(intent);// 跳转页面

    }


    public void showMajorRecruitsResult() {
        trendBtn.setVisibility(View.GONE);
        schoolRecruitResultLayout.setVisibility(View.GONE);
        majorRecruitResultLayout.setVisibility(View.VISIBLE);
        titleView.setText(Html.fromHtml("<font color = blue><b>" +
                schools.get(schoolId)
                + "</b></font><font color=reb><b>" + years[yearId]
                + "</b><font>年在<br/><font color=red><b>"
                + provinces.get(sourceAreaId) + "</b></font>地区各专业录取线"));


        GetService getService = new GetService();
        majorRecruits = getService.setMajorRecruits();
        Major_recruitAdapter major_recruitAdapter = new Major_recruitAdapter(this.getActivity(), majorRecruits);
        enrollResultView.setAdapter(major_recruitAdapter);
    }

    public void showSchoolRecruits() {
        titleView.setText(Html.fromHtml("<font color = blue><b>" +
                schools.get(schoolId)
                + "</b></font><font color = red><b>"
                + batches[batchId]
                + "</b></font><><font color = blue><b>"
                + categories[categoryId]
                + "</b></font>类<br/>在<font color = red><b>"
                + provinces.get(sourceAreaId) + "</b></font>地区历年录取线"));
        schoolRecruitResultLayout.setVisibility(View.VISIBLE);
        majorRecruitResultLayout.setVisibility(View.GONE);
        trendBtn.setVisibility(View.VISIBLE);

        GetService getService = new GetService();
        schoolRecruits = getService.setSchoolRecruit();
        School_recruitAdapter school_recruitAdapter = new School_recruitAdapter(this.getActivity(), schoolRecruits);
        enrollResultView.setAdapter(school_recruitAdapter);
    }


    public void getSchool(String areaName) {
        GetSQLite getSQLite = new GetSQLite();
        schoolList = getSQLite.setSchoolList();
        schools.clear();
        for (School school : schoolList) {
            if (school.getAreaName().equals(areaName)) {
                schools.add(school.getSchoolName());
            }
        }
        ArrayAdapter<String> schoolAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item_year, schools);
        schoolSpinner.setAdapter(schoolAdapter);
    }


    public class MyItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.schoolAreaSpinner:
                    schoolAreaId = position;
                    getSchool(provinces.get(schoolAreaId));
                    break;
                case R.id.areaSpinner:
                    sourceAreaId = position;
                    break;
                case R.id.categorySpinner:
                    categoryId = position;
                    break;
                case R.id.batchSpinner:
                    batchId = position;
                    break;
                case R.id.schoolSpinner:
                    schoolId = position;
                    break;
                case R.id.yearSpinner:
                    yearId = position;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

}

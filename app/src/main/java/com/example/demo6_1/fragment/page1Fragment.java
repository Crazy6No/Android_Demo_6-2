package com.example.demo6_1.fragment;


import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demo6_1.R;
import com.example.demo6_1.adapter.ControlLineAdapter;
import com.example.demo6_1.bean.ControlLine;
import com.example.demo6_1.util.GetSQLite;
import com.example.demo6_1.util.GetService;

import java.util.ArrayList;
import java.util.List;

public class page1Fragment extends Fragment {

    private Spinner yearSpinner, provinceSpinner, categorySpinner, batchSpinner;
    private String[] years = new String[]{"2014", "2013", "2012", "2011", "2010"};
    private List<String> provinces = new ArrayList<String>();
    private String[] batches = new String[]{"不限", "一本", "二本", "三本", "专科", "提前"};
    private String[] categories = new String[]{"不限", "文史", "理工", "综合类", "艺术类", "体育"};
    private int sourceAreaId, yearId, categoryId, batchId;
    private TextView controlLineTitle;
    private List<ControlLine> controlLineList;
    private ListView controlLineListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page1, container, false);


        yearSpinner = (Spinner) view.findViewById(R.id.yearSpinner);
        provinceSpinner = (Spinner) view.findViewById(R.id.areaSpinner);
        categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        batchSpinner = (Spinner) view.findViewById(R.id.batchSpinner);
        controlLineTitle = (TextView) view.findViewById(R.id.controlLineTitle);
        controlLineListView = (ListView) view.findViewById(R.id.controlLineList);


        GetSQLite getSQLite = new GetSQLite();
        provinces = getSQLite.setProvince();

        ArrayAdapter<String> provincesAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item_year, provinces
        );
        provinceSpinner.setAdapter(provincesAdapter);
        provinceSpinner.setSelection(sourceAreaId);

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item_year, years
        );
        yearSpinner.setAdapter(yearAdapter);
        yearSpinner.setSelection(yearId);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item_category, categories
        );
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setSelection(categoryId);

        ArrayAdapter<String> batchAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item_batch, batches
        );
        batchSpinner.setAdapter(batchAdapter);
        batchSpinner.setSelection(batchId);

        GetService getService = new GetService();
        controlLineList = getService.setControlLineList();
        ControlLineAdapter controlLineAdapter = new ControlLineAdapter(this.getActivity(), controlLineList);
        controlLineListView.setAdapter(controlLineAdapter);

        MyItemSelectedListener itemSelectedListener = new MyItemSelectedListener();
        yearSpinner.setOnItemSelectedListener(itemSelectedListener);
        provinceSpinner.setOnItemSelectedListener(itemSelectedListener);
        categorySpinner.setOnItemSelectedListener(itemSelectedListener);
        batchSpinner.setOnItemSelectedListener(itemSelectedListener);




        return view;
    }

private class MyItemSelectedListener implements AdapterView.OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.areaSpinner:
                sourceAreaId = position;
                break;

            case R.id.yearSpinner:
                yearId = position;
                break;

            case R.id.categorySpinner:
                categoryId = position;
                break;

            case R.id.batchSpinner:
                batchId = position;
                break;
            default:
                break;
        }
        controlLineTitle.setText(Html.fromHtml("<font color=red><b>" + provinces.get(sourceAreaId) + "</b></font>地区<font color=blue><b>" + years[yearId] + "</b></font>年省控线"));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    }
}

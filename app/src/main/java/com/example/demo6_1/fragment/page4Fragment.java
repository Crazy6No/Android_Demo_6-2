package com.example.demo6_1.fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.demo6_1.R;

public class page4Fragment extends Fragment {
    private Spinner categorySpinner, batchSpinner, sourceAreaSpinner, targetAreaSpinner;
    private String[] years = new String[]{"2014", "2013", "2012",
            "2011", "2010"};
    private String[] provinces = new String[]{"上海", "浙江", "江西", "北京"};
    private String[] batches = new String[]{"不限", "一本", "二本", "三本", "专科", "提前"};
    private String[] categories = new String[]{"不限", "文史", "理工", "综合类", "艺术类", " 体育"};
    public static int sourceAreaId = 2;
    public static int targetAreaId = 2;
    public static int batchId = 1;
    public static int categoryId = 1;
    private SharedPreferences selfInfoPreferences;
    private TextView scoreText, rankText, categoryText, batchText, sourceAreaText, targetAreaText;
    private int score, rank, category, batch;
    private Button edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page4, container, false);

        //获取组件id
        scoreText = (TextView) view.findViewById(R.id.scoreText);
        rankText = (TextView) view.findViewById(R.id.rankText);
        categoryText = (TextView) view.findViewById(R.id.categoryText);
        batchText = (TextView) view.findViewById(R.id.batchText);
        sourceAreaText = (TextView) view.findViewById(R.id.sourceAreaText);
        targetAreaText = (TextView) view.findViewById(R.id.targetAreaText);


        selfInfoPreferences = getContext().getSharedPreferences("selfInfo", Context.MODE_PRIVATE);
        score = selfInfoPreferences.getInt("score", -1);
        rank = selfInfoPreferences.getInt("rank", -1);
        category = selfInfoPreferences.getInt("categoryId", 0);
        batch = selfInfoPreferences.getInt("batchId", 0);
        sourceAreaId = selfInfoPreferences.getInt("sourceAreaId", 0);
        targetAreaId = selfInfoPreferences.getInt("targetAreaId", 0);
        boolean isFirst = selfInfoPreferences.getBoolean("isFirst", true);


        edit = (Button) view.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();//调用对话框方法
            }
        });
        show();
        return view;
    }


    private void show() {
        if (score != -1) {
            scoreText.setText(Html.fromHtml("高考成绩：<b><font color='red'>" + score + "</font></b>"));
        } else {
            scoreText.setText(Html.fromHtml("高考成绩：<b><font color='red'>暂未提供 </font ></b>"));
        }
        if (rank != -1) {
            rankText.setText(Html.fromHtml("高考排名：<b><font color='red'> " + rank + "</font></b>"));
        } else {
            rankText.setText(Html.fromHtml("高考排名：<b><font color='red'>暂未提供 </font></b>"));
        }
        categoryText.setText(Html.fromHtml("考生类别：<b><font color = 'red'>" + categories[categoryId] + "</font></b>"));
        batchText.setText(Html.fromHtml(
                "录取批次：<b><font color='red'>" + batches[batchId] + "</font></b>"));
        sourceAreaText.setText(Html.fromHtml(
                "生源地：<b><font color = 'red'>" + provinces[sourceAreaId] + "</font></b>"));
        targetAreaText.setText(Html.fromHtml("意向省份：<b><font color = 'red'>" + provinces[targetAreaId] + "</font></b >"));
    }

    public void showDialog() {//弹出对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("个人基本信息填写");

        View view = this.getLayoutInflater().inflate(R.layout.dialog_self_info_edit, new LinearLayout(this.getActivity()));
        sourceAreaSpinner = (Spinner) view.findViewById(R.id.sourceAreaSpinner);
        categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        batchSpinner = (Spinner) view.findViewById(R.id.batchSpinner);
        targetAreaSpinner = (Spinner) view.findViewById(R.id.targetAreaSpinner);
        final EditText scoreText = (EditText) view.findViewById(R.id.score);

        final EditText rankText = (EditText) view.findViewById(R.id.rank);//高考排名
        if (score != -1) {
            scoreText.setText("" + score);
        }
        if (rank != -1) {
            rankText.setText("" + rank);
        }
        ArrayAdapter<String> areaAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item_year, provinces);
        sourceAreaSpinner.setAdapter(areaAdapter);
        targetAreaSpinner.setAdapter(areaAdapter);
        sourceAreaSpinner.setSelection(sourceAreaId);
        targetAreaSpinner.setSelection(targetAreaId);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(

                this.getActivity(), R.layout.spinner_item_category, categories);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setSelection(categoryId);// 类型默认为理工
        ArrayAdapter<String> batchAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item_batch, batches);
        batchSpinner.setAdapter(batchAdapter);
        batchSpinner.setSelection(batchId);// 批次默认为二本

        MyItemSelectedListener myItemSelectedListener = new
                MyItemSelectedListener();

        sourceAreaSpinner.setOnItemSelectedListener(myItemSelectedListener);
        categorySpinner.setOnItemSelectedListener(myItemSelectedListener);
        batchSpinner.setOnItemSelectedListener(myItemSelectedListener);
        targetAreaSpinner.setOnItemSelectedListener(myItemSelectedListener);
        builder.setView(view);
        builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = selfInfoPreferences.edit();
                editor.putBoolean("isFirst", false);
                String scoreget = scoreText.getText().toString().trim();
                if ("".equals(scoreget)) {
                    editor.putInt("score", -1);
                    score = -1;
                } else {
                    editor.putInt("score", Integer.parseInt(scoreget));
                    score = Integer.parseInt(scoreget);
                }
                String rankget = rankText.getText().toString().trim();
                if ("".equals(rankget)) {
                    editor.putInt("rank", -1);
                    rank = -1;
                } else {
                    editor.putInt("rank", Integer.parseInt(rankget));
                    rank = Integer.parseInt(rankget);
                }
                editor.putInt("categoryId", categoryId);//类别 ID
                editor.putInt("batchId", batchId);//批次 ID
                editor.putInt("sourceAreaId", sourceAreaId);//生源地 ID
                editor.putInt("targetAreaId", targetAreaId);//意向省市 ID
                editor.commit();
                show();
            }
        });
        builder.create().show();//创建并显示对话框
    }


    private class MyItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (parent.getId()) {
                case R.id.sourceAreaSpinner:// 如果是选择生源地，保存省份的序号，
                    sourceAreaId = position;
                    break;
                case R.id.categorySpinner:// 如果是选择文理科
                    categoryId = position;
                    break;
                case R.id.batchSpinner:// 如果是选择批次
                    batchId = position;
                    break;
                case R.id.targetAreaSpinner://如果是选择意向省份
                    targetAreaId = position;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
package com.example.demo6_1.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.demo6_1.R;
import com.example.demo6_1.adapter.ConsultAdapter;
import com.example.demo6_1.bean.Question;
import com.example.demo6_1.util.GetService;

import java.util.List;

public class page3Fragment extends Fragment {


    private ListView questionListView;
    private Button submit, search;
    private String questionString;
    private EditText contentView;
    private EditText keywordView;
    private List<Question> questionList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page3, container, false);


        questionListView = (ListView) view.findViewById(R.id.questionList);
        contentView = (EditText) view.findViewById(R.id.content);
        submit = (Button) view.findViewById(R.id.submit);
        search = (Button) view.findViewById(R.id.search);
        keywordView = (EditText) view.findViewById(R.id.keyword);

        GetService getService = new GetService();
        questionList = getService.setQuestionList();
        ConsultAdapter consultAdapter = new ConsultAdapter(
                this.getActivity(), questionList
        );
        questionListView.setAdapter(consultAdapter);


        questionListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateQuestion();
                return false;
            }
            }
        );
        return view;
    }


    public void updateQuestion(){
        AlertDialog.Builder builder = new
                AlertDialog.Builder(this.getActivity());
        builder.setTitle("我来回答");
        final EditText answerText = new EditText(this.getActivity());
        builder.setView(answerText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
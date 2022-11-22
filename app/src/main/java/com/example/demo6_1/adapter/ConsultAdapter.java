package com.example.demo6_1.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo6_1.R;
import com.example.demo6_1.bean.Question;
import com.example.demo6_1.bean.Question;

import java.util.List;

public class ConsultAdapter extends BaseAdapter {
    private Context context;
    private List<Question> questionList;

    public ConsultAdapter
            (Context context,List<Question> questionList){
        this.context = context;
        this.questionList = questionList;
    }
    @Override
    public int getCount(){
        return questionList.size();
    }

    @Override
    public Object getItem(int position){
        return questionList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = new ViewHolder();
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item_question,null, false);
            holder.contentView = (TextView) convertView.findViewById(R.id.questionContent);
            holder.timeView = (TextView) convertView.findViewById(R.id.questionTime);
            holder.answerView = (TextView) convertView.findViewById(R.id.answer);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.contentView.setText(position + 1 + "."
                + questionList.get(position).getQuestionContent());
        holder.timeView.setText("提问时间: " +
                questionList.get(position).getQuestionTime());

        if("".equals(questionList.get(position).getAnswerTime())
                ||null == questionList.get(position).getAnswerTime()){
            holder.answerView.setText("暂无回复");
        }else {
            String [] answers = questionList.get(position).getAnswerContent().split("#");
            String [] answerTimes = questionList.get(position).getAnswerTime().split("#");
            String answerResult = "";
            for (int i = 0; i < answers.length; i++) {
                answerResult += "<p><big>" + answers[i]
                        + "</big></p><i><small>回复时间： " + answerTimes[i]
                        + "</small></i>";
            }
            holder.answerView.setText(Html.fromHtml(answerResult));
        }
        return convertView;
    }

    public final class ViewHolder{
        public TextView contentView;
        public TextView timeView;
        public TextView answerView;
    }
}
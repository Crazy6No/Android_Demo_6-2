package com.example.demo6_1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo6_1.R;
import com.example.demo6_1.bean.SchoolRecruit;

import java.util.List;

public class School_recruitAdapter extends BaseAdapter {
    private Context context;
    private List<SchoolRecruit> schoolRecruits;

    public School_recruitAdapter(Context context, List<SchoolRecruit> schoolRecruits) {
        this.context = context;
        this.schoolRecruits = schoolRecruits;
    }

    @Override
    public int getCount() {
        return schoolRecruits.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolRecruits;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_item_school_recruit,null,false);
            if (position%2==0){
                convertView.setBackgroundColor(Color.BLACK);//设置单行背景颜色为黑色
                //设置相对应的文字的字体颜色为白色
             convertView.setBackgroundColor(Color.argb(200,220,230,240));
            }else{
                convertView.setBackgroundColor(Color.argb(51,170,187,204));
            }
            holder.year = (TextView) convertView.findViewById(R.id.year);
            holder.controlLine = (TextView) convertView.findViewById(R.id.controlLine);
            holder.enterLine = (TextView) convertView.findViewById(R.id.enterLine);
            holder.averageScore = (TextView) convertView.findViewById(R.id.averageScore);
            holder.maxScore = (TextView) convertView.findViewById(R.id.maxScore);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.year.setText(schoolRecruits.get(position).getsRecruitYear()+"");
        holder.controlLine.setText(schoolRecruits.get(position).getControlLine()+"");
        holder.enterLine.setText(schoolRecruits.get(position).getEnterLine()+"");
        holder.averageScore.setText(schoolRecruits.get(position).getsRecruitAScore()+"");
        holder.maxScore.setText(schoolRecruits.get(position).getsRecruitHScore()+"");
        return convertView;
    }

    public final class ViewHolder{
        public TextView year;
        public TextView controlLine;
        public TextView enterLine;
        public TextView averageScore;
        public TextView maxScore;
    }
}

package com.example.demo6_1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo6_1.R;
import com.example.demo6_1.bean.MajorRecruit;

import java.util.List;

public  class Major_recruitAdapter extends BaseAdapter {
    private Context context;
    private List<MajorRecruit> majorRecruits;

    public Major_recruitAdapter(Context context, List<MajorRecruit> majorRecruits) {
        this.context = context;
        this.majorRecruits = majorRecruits;
    }

    @Override
    public int getCount() {
        return majorRecruits.size();
    }

    @Override
    public Object getItem(int i) {
        return majorRecruits.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(
                    R.layout.list_item_major_recruit,null,false);
            if(position%2==0){
                convertView.setBackgroundColor(Color.argb(200,220,230,240));//设置单行背景颜色为黑色



            }else{
                convertView.setBackgroundColor(Color.argb(51,170,187,204));
            }
            holder.major = (TextView) convertView.findViewById(R.id.major);
            holder.category = (TextView) convertView.findViewById(R.id.category);
            holder.batch = (TextView) convertView.findViewById(R.id.batch);
            holder.averageScore = (TextView) convertView.findViewById(R.id.averageScore);
            holder.maxScore = (TextView) convertView.findViewById(R.id.maxScore);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.major.setText(majorRecruits.get(position).getMajorName());
        holder.category.setText(majorRecruits.get(position).getCategoryName());
        holder.batch.setText(majorRecruits.get(position).getBatchName());
        holder.averageScore.setText(majorRecruits.get(position).getmReruitAScore()+"");
        holder.maxScore.setText(majorRecruits.get(position).getmReruitHScore()+"");
        return convertView;

    }

    public final class ViewHolder{
        public TextView major;
        public TextView category;
        public TextView batch;
        public TextView averageScore;
        public TextView maxScore;
    }
}

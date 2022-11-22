package com.example.demo6_1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.demo6_1.bean.SchoolRecruit;

import java.util.ArrayList;
import java.util.List;

public class TrendView extends View {
    public TrendView(Context context) {
        super(context);
    }

    public TrendView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Paint xyPaint;
    private Paint textPaint;
    private Paint linePaint;
    private Paint pointPaint, numPaint;
    private int width, height;
    private int arrawSpace = 8;


    private List<SchoolRecruit> schoolRecruits;
    private int x[];
    private int xSpace;
    private int year;

    private int NO_DATA = -1024;
    private int maxData;
    private int minData;
    private int midData;


    private List<Integer> firstDatas;
    private List<Integer> secondDatas;
    private List<Integer> thirdDatas;


    private int ySpace = 2;
    private int centerHeight;
    private float textHeight;
    private boolean isDrawLine = false;


    public void setSchoolRecruits(List<SchoolRecruit> schoolRecruits) {
        this.schoolRecruits = schoolRecruits;
        init();
        isDrawLine = true;
        invalidate();
    }


    public void init() {
        xyPaint = new Paint();
        xyPaint.setAntiAlias(true);
        xyPaint.setStrokeWidth(3);
        xyPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20);

        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.FILL);

        numPaint = new Paint();
        numPaint.setAntiAlias(true);
        numPaint.setColor(Color.BLACK);
        numPaint.setTextSize(18);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.BLACK);

        x = new int[schoolRecruits.size()];
        year = schoolRecruits.get(schoolRecruits.size() - 1).getsRecruitYear();

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textHeight = fontMetrics.bottom - fontMetrics.top;

        initDatas();

    }


    public int getMaxData(List<Integer> datas) {
        int max = datas.get(0);
        for (int i = 0; i < datas.size(); i++) {
            if (max < datas.get(i)) {
                max = datas.get(i);
            }
        }
        return max;
    }


    public int getMinData(List<Integer> datas) {
        int min = 1024;
        for (int i = 0; i < datas.size(); i++) {
            int temp = datas.get(i);
            if (temp != -1024 && temp < min) {
                min = temp;
            }
        }
        return min;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = this.getWidth();
        height = this.getHeight();
        xSpace = (width - 200) / (x.length - 1);
        ySpace = (height - 160) / Math.abs(maxData - minData);// 上下各留 65
        for (int i = 0; i < x.length; i++) {// 初始化各个点的 X 轴坐标，起始点偏移 60

            x[i] = 60 + i * xSpace;// 左边偏移 40 个像素
        }
        centerHeight = height / 2 - 10;// 获取控件的 Y 轴中线，中线为中间数据，比该

        drawXY(canvas);
        if (firstDatas != null) {// 绘制录取最高分


            linePaint.setColor(Color.RED);
            canvas.drawText("录取最高分", width - 105, 20, linePaint);
            canvas.drawLine(width - 130, 15, width - 110, 15, linePaint);
            drawLine(firstDatas, canvas, linePaint, true);
        }
        if (secondDatas != null) {// 绘制录取平均分
            linePaint.setColor(Color.BLUE);
            canvas.drawText("录取平均分", width - 105, 45, linePaint);
            canvas.drawLine(width - 130, 40, width - 110, 40, linePaint);
            drawLine(secondDatas, canvas, linePaint, true);
        }
        if (thirdDatas != null) {// 绘制投档线
            linePaint.setColor(Color.YELLOW);
            canvas.drawText("录取投档线", width - 105, 70, linePaint);
            canvas.drawLine(width - 130, 65, width - 110, 65, linePaint);
            drawLine(thirdDatas, canvas, linePaint, true);
        }
    }


    public void initDatas() {
        firstDatas = new ArrayList<Integer>();
        secondDatas = new ArrayList<Integer>();
        thirdDatas = new ArrayList<Integer>();
        for (int i = schoolRecruits.size() - 1; i >= 0; i--) {
            int sRecruitHScore = schoolRecruits.get(i).getsRecruitHScore();
            int controlLine = schoolRecruits.get(i).getControlLine();
            int enterLine = schoolRecruits.get(i).getEnterLine();
            int sRecruitAScore = schoolRecruits.get(i).getsRecruitAScore();
            if (controlLine <= 0) {
                firstDatas.add(NO_DATA);
                secondDatas.add(NO_DATA);
                thirdDatas.add(NO_DATA);
            } else {
                if (sRecruitHScore <= 0) {
                    firstDatas.add(NO_DATA);
                } else {
                    firstDatas.add(sRecruitHScore - controlLine);
                }
                if (sRecruitAScore <= 0) {
                    secondDatas.add(NO_DATA);
                } else {
                    secondDatas.add(sRecruitAScore - controlLine);
                }
                if (enterLine <= 0) {
                    thirdDatas.add(NO_DATA);
                } else {
                    thirdDatas.add(enterLine - controlLine);
                }
            }
        }
        List<Integer> datas = new ArrayList<Integer>();
        datas.addAll(firstDatas);
        datas.addAll(secondDatas);
        datas.addAll(thirdDatas);
        maxData = getMaxData(datas);
        minData = getMinData(datas);
        midData = (maxData + minData) / 2;
    }


    public void drawLine(List<Integer> datas, Canvas canvas, Paint linePaint, boolean isTextUp) {
        for (int i = 0; i < datas.size(); i++) {
            int data = datas.get(i);
            if (data != NO_DATA) {
                float point = (-(data - midData)) * ySpace;
                canvas.drawCircle(x[i], centerHeight + point, 5, pointPaint);
                if (isTextUp) {
                    canvas.drawText(data + "", x[i] - (data + "").length() * 6,
                            centerHeight + point - textHeight / 2, numPaint);
                } else {
                    canvas.drawText(data + "", x[i] - (data + "").length() * 6,
                            centerHeight + point + textHeight, numPaint);
                }
                if (i != (datas.size() - 1) && datas.get(i + 1) != NO_DATA) {
                    int nextData = (Integer) datas.get(i + 1);
                    float pointNext = (-(nextData - midData)) * ySpace;
                    canvas.drawLine(x[i], centerHeight + point, x[i + 1],
                            centerHeight + pointNext, linePaint);
                }
            }
        }
    }

    public void drawXY(Canvas canvas) {
        canvas.drawLine(20, height - 50, width - 30,
                height - 50, xyPaint);
        canvas.drawLine(20, height - 50, 20, 50,
                xyPaint);
        canvas.drawLine(width - 30 - arrawSpace, height - 50 - arrawSpace,
                width - 30, height - 50, xyPaint);
        canvas.drawLine(width - 30 - arrawSpace, height - 50 + arrawSpace,
                width - 30, height - 50, xyPaint);
        canvas.drawLine(20 + arrawSpace, 50 + arrawSpace, 20, 50,
                xyPaint);
        canvas.drawLine(20 - arrawSpace, 50 + arrawSpace, 20, 50,
                xyPaint);


        canvas.drawText("年份", width - 40, height - 50 - arrawSpace - 5,
                textPaint);
        canvas.drawText("分值与省控线差值", 5 + arrawSpace, 40, textPaint);
        for (int i = 0; i < x.length; i++) {
            canvas.drawText((year + i) + "", x[i] - 20, height - 20, numPaint);
            canvas.drawCircle(x[i], height - 50, 5, pointPaint);
        }

    }
}



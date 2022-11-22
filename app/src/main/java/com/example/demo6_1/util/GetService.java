package com.example.demo6_1.util;

import android.util.Log;

import com.example.demo6_1.bean.ControlLine;
import com.example.demo6_1.bean.MajorRecruit;
import com.example.demo6_1.bean.Question;
import com.example.demo6_1.bean.SchoolRecruit;

import java.util.ArrayList;
import java.util.List;

public class GetService {

    private String[] batchString = new String[]{"一本","一本","二本","专科"};
    private int[] scoreString = new int[]{524,526,390,230};
    private int[] year = new int[]{2014,2014,2014,2014};


    private String[] categoryString  = new String[]{"理工","文史","理工","文史"};
    private String[] areaNameString = new String[]{"江西","江西","江西","江西"};

    public List<ControlLine> setControlLineList(){
        List<ControlLine> list = new ArrayList<ControlLine>();
        for (int i = 0;i<4;i++){
            ControlLine controlLine = new ControlLine();

            controlLine.setControlYear(year[i]);
            controlLine.setCategoryName(categoryString[i]);
            controlLine.setBatchName(batchString[i]);
            controlLine.setControlLine(scoreString[i]);
            controlLine.setAreaName(areaNameString[i]);
            list.add(controlLine);
        }
        return list;
    }


    private int[] yearString= new int[]{2014,2013,2012,2011,2010};
    private int[] controlLineSreing= new int[]{524,532,533,511,510};//2015年的省控线555
    private int[] enterLinerString= new int[]{521,522,523,501,510};//2015年的投档线520
    private int[] averageScoreString= new int[]{528,532,543,521,520};//2015年的平均分566
    private int[] maxScoreString= new int[]{548,552,553,541,540};//2015年的最好分588

    public List<SchoolRecruit> setSchoolRecruit(){
        List<SchoolRecruit> schoolRe = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Log.d("tag1",yearString[i]+"");
            SchoolRecruit schoolRecruit = new SchoolRecruit();
            schoolRecruit.setsRecruitYear(yearString[i]);
            schoolRecruit.setControlLine(controlLineSreing[i]);
            schoolRecruit.setEnterLine(enterLinerString[i]);
            schoolRecruit.setsRecruitAScore(averageScoreString[i]);
            schoolRecruit.setsRecruitHScore(maxScoreString[i]);
            schoolRe.add(schoolRecruit);
        }
        return schoolRe;
    }

    private String[] majorString = new String[]{"行政管理","护理学","电器信息","历史学","管理科学","临床医学"};
    private String[] categoryString2 = new String[]{"理工","理工","理工","文史","理工","文史"};
    private String[] batchString2 = new String[]{"一本","一本","二本","三本","专科","一本"};
    private int[] averageScoreStringMaj = new int[]{528,532,543,521,520,533};
    private int[] maxScoreStringMaj = new int[]{548,552,553,541,540,566};

    public List<MajorRecruit> setMajorRecruits(){
        List<MajorRecruit> majorRe = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MajorRecruit majorRecruit = new MajorRecruit();
            majorRecruit.setMajorName(majorString[i]);
            majorRecruit.setCategoryName(categoryString2[i]);
            majorRecruit.setBatchName(batchString2[i]);
            majorRecruit.setmReruitAScore(averageScoreStringMaj[i]);
            majorRecruit.setmReruitHScore(maxScoreStringMaj[i]);
            majorRe.add(majorRecruit);
        }
        return majorRe;
    }


    private String[] questionContent = new String[]{"555分高考哪个学校好？","江西哪个学校最好？","今年理工560分能考进江西财经大学吗？"};

    private String[] questionTime = new String[]{"2016-1-25 03:55","2016-2-2 13:50","2015-1-1 18:30"};

    private String[] answerContent = new String[]{"江西财经大学金融学可以的","江西财经大学录取分数最高#南昌大学是211","差不多有些风险#最好的专业进不了"};

    private String[] answerTime = new String[]{"2016-1-25 06:55","2016-2-2 16:31#2016-2-3 18:30","2015-1-1 16:31#2015-1-2 13:22"};

    public List<Question> setQuestionList(){
        List<Question> questionList = new ArrayList<>();

        for (int i = 0; i < questionContent.length; i++) {
            Question question = new Question();
            question.setQuestionContent(questionContent[i]);
            question.setQuestionTime(questionTime[i]);
            question.setAnswerContent(answerContent[i]);
            question.setAnswerTime(answerTime[i]);
            questionList.add(question);
        }



        return questionList;
    }
}

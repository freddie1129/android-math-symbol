package com.fredroid.mathsymbol;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;


public class QuizeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SECTION_NUMBER = "section_number";
    public TextView tvQuestion;
    public TextView tvIndex;
    public int mQuestionNum;
    public String mstrQuestion, mstrAnswerA, mstrAnswerB, mstrAnswerC, mstrAnswerD, mstrNum;
    Button butAnswerA, butAnswerB, butAnswerC, butAnswerD;
    ProgressBar pbarIndex;
    ArrayList<Integer> mquestionList = new ArrayList<Integer>();
    ArrayList<Integer> mansList = new ArrayList<Integer>();
    String[][] data;
    ArrayList<Integer> mRadomQuestionIndx = new ArrayList<Integer>();
    int mnpos = 0;
    String ans;
    private String mParam2;
    private boolean mbFristEnter = true;
    private int mQuestionIndex = 0;
    private int mAnsIndex = 0;
    private int[] index;

    public QuizeFragment() {
        // Required empty public constructor
    }


    public static QuizeFragment newInstance(int sectionNumber) {
        QuizeFragment fragment = new QuizeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mnpos = getArguments().getInt(ARG_SECTION_NUMBER) + 1;
            switch (mnpos) {
                case MetaItem.BASIC:
                    data = MetaItem.mathBasic;
                    break;
                case MetaItem.GEOMETRY:
                    data = MetaItem.mathGeometry;
                    break;
                case MetaItem.ALGEBRA:
                    data = MetaItem.mathAlgebra;
                    break;
                case MetaItem.PROBABILITY:
                    data = MetaItem.mathProbability;
                    break;
                case MetaItem.SET:
                    data = MetaItem.mathSet;
                    break;
                case MetaItem.LOGIC:
                    data = MetaItem.mathLogic;
                    break;
                case MetaItem.CALCULUS:
                    data = MetaItem.mathCalculus;
                    break;
                default:
                    data = MetaItem.mathBasic;
                    break;
            }
            mQuestionNum = data.length;
            mquestionList.clear();
            mRadomQuestionIndx.clear();
            for (int i = 0; i < mQuestionNum; i++) {
                mquestionList.add(i);
                mRadomQuestionIndx.add(i);

            }
            if (mbFristEnter) {

                for (int i = 0; i < 4; i++) {
                    mansList.add(i);
                }
                Collections.shuffle(mRadomQuestionIndx);
                mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
                mbFristEnter = false;

            }
        }

    }

    public void refreshUI() {
        if (mstrQuestion.equals("fig")) {
            tvQuestion.setText("");
            String figname = ans.replace(" ", "_").toLowerCase();
            int resourcesID = getActivity().getResources().getIdentifier(figname, "drawable", getActivity().getPackageName());
            tvQuestion.setBackgroundResource(resourcesID);
        } else {
            tvQuestion.setText(mstrQuestion);
            tvQuestion.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        }


        mstrAnswerA = "A. " + data[index[mansList.get(0)]][1];
        butAnswerA.setText(mstrAnswerA);
        mstrAnswerB = "B. " + data[index[mansList.get(1)]][1];
        butAnswerB.setText(mstrAnswerB);
        mstrAnswerC = "C. " + data[index[mansList.get(2)]][1];
        butAnswerC.setText(mstrAnswerC);
        mstrAnswerD = "D. " + data[index[mansList.get(3)]][1];
        butAnswerD.setText(mstrAnswerD);
        mstrNum = String.format("%02d/%02d", mQuestionIndex + 1, data.length);
        tvIndex.setText(mstrNum);
        pbarIndex.setProgress(mQuestionIndex);
    }

    public int CreateQuestion(int qindex) {
        Collections.shuffle(mansList);
        index = generateQuestion(qindex);
        mstrQuestion = data[qindex][0];
        ans = data[qindex][1];

        int answer = mansList.indexOf(3);
        return answer;
    }

    //Create a question randomly, the first three are wrong, and the last one is always the right answer
    public int[] generateQuestion(int quesIndex) {
        int[] questionCollection = new int[4];
        Collections.shuffle(mquestionList);
        for (int i = 0; i < 3; i++) {
            if (mquestionList.get(i) == quesIndex)
                Collections.swap(mquestionList, i, 3);
        }
        for (int i = 0; i < 3; i++) {
            questionCollection[i] = mquestionList.get(i);
        }
        questionCollection[3] = quesIndex;      //Last one always is the index of question's answer
        return questionCollection;
    }


    public void nextQuestion(int Answer, String txt) {
        if (mAnsIndex == Answer) {
            if (mQuestionIndex == mQuestionNum - 1) {
                mQuestionIndex = 0;
                Collections.shuffle(mRadomQuestionIndx);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Notice");
                alertDialog.setMessage("Quize finished, start a new round.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Once Again",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alertDialog.show();
            } else {

                mQuestionIndex = mQuestionIndex + 1;

            }


            mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
            refreshUI();

        } else {

            Context context = getActivity().getApplicationContext();
            CharSequence text = "Incorrect";
            int duration = Toast.LENGTH_SHORT;


            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", 123);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_quize, container, false);
        TextView tv = view.findViewById(R.id.textView_question);
        tv.setText(String.format("%02d", mnpos));


        butAnswerA = view.findViewById(R.id.button_answer_a);
        butAnswerB = view.findViewById(R.id.button_answer_b);
        butAnswerC = view.findViewById(R.id.button_answer_c);
        butAnswerD = view.findViewById(R.id.button_answer_d);
        tvQuestion = view.findViewById(R.id.textView_question);
        tvIndex = view.findViewById(R.id.textView_Count);
        pbarIndex = view.findViewById(R.id.progressBar);
        pbarIndex.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);


        refreshUI();

        pbarIndex.setMax(data.length);


        butAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(0, butAnswerA.getText().toString());
            }
        });
        butAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(1, butAnswerB.getText().toString());
            }
        });
        butAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(2, butAnswerC.getText().toString());
            }
        });
        butAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(3, butAnswerC.getText().toString());
            }
        });


        return view;

    }


}

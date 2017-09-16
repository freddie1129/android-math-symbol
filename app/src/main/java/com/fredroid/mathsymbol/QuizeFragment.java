package com.fredroid.mathsymbol;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.os.VibrationEffect;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean mbFristEnter = true;


    Button butAnswerA,butAnswerB,butAnswerC,butAnswerD;
    public TextView tvQuestion;
    public TextView tvIndex;
    ProgressBar pbarIndex;
    private int mQuestionIndex = 0;
    private int mAnsIndex = 0;
    ArrayList<Integer> mquestionList = new ArrayList<Integer>();
    ArrayList<Integer> mansList = new ArrayList<Integer>();
    String [][] data;
    ArrayList<Integer> mRadomQuestionIndx = new ArrayList<Integer>();

    public int mMode;
    public int mQuestionNum;
   // AdView  mAdView;
   // AdRequest mAdRequest;


    public String mstrQuestion,mstrAnswerA,mstrAnswerB,mstrAnswerC,mstrAnswerD,mstrNum;


    private static final String ARG_SECTION_NUMBER = "section_number";
    int mnpos = 0;
    public QuizeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizeFragment.
     */
    // TODO: Rename and change types and number of parameters
 /*   public static QuizeFragment newInstance(String param1, String param2) {
        QuizeFragment fragment = new QuizeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

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
            for(int i = 0; i < mQuestionNum; i++)
            {
                mquestionList.add(i);
                mRadomQuestionIndx.add(i);

            }
         //   mansList.clear();

           // mQuestionIndex = 0;
          //  mAnsIndex = 0;
            if (mbFristEnter) {

                for (int i = 0; i < 4; i++)
                {
                    mansList.add(i);
                }
                Collections.shuffle(mRadomQuestionIndx);
                mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
                mbFristEnter = false;

            }



            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    public void FreshUI()
    {
        if(mstrQuestion.equals("fig"))
        {
            tvQuestion.setText("");
            String figname = ans.replace(" ","_").toLowerCase();
            int resourcesID=getActivity().getResources().getIdentifier(figname,"drawable",getActivity().getPackageName());
            tvQuestion.setBackgroundResource(resourcesID);
        }
        else
        {
            tvQuestion.setText(mstrQuestion);
            //
            // int resourceID = getActivity().getResources().get  @drawable/common_google_signin_btn_icon_dark_normal_background
            tvQuestion.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        }


        // tvQuestion.setText(mstrQuestion);





        mstrAnswerA = "A. " + data[index[mansList.get(0)]][1];
        butAnswerA.setText(mstrAnswerA);
        mstrAnswerB = "B. " + data[index[mansList.get(1)]][1];
        butAnswerB.setText(mstrAnswerB);
        mstrAnswerC = "C. " + data[index[mansList.get(2)]][1];
        butAnswerC.setText(mstrAnswerC);
        mstrAnswerD = "D. " + data[index[mansList.get(3)]][1];
        butAnswerD.setText(mstrAnswerD);
        mstrNum = String.format("%02d/%02d",mQuestionIndex + 1,data.length);
        tvIndex.setText(mstrNum);
        pbarIndex.setProgress(mQuestionIndex);
       // int answer = mansList.indexOf(3);
    }


    private int [] index;
    String ans;
    public int CreateQuestion(int qindex)
    {
        Collections.shuffle(mansList);
        index = generateQuestion(qindex);
        mstrQuestion = data[qindex][0];
        ans = data[qindex][1];

   /*     if(mstrQuestion.equals("fig"))
        {
            tvQuestion.setText("");
            String figname = ans.replace(" ","_").toLowerCase();
            int resourcesID=getActivity().getResources().getIdentifier(figname,"drawable",getActivity().getPackageName());
            tvQuestion.setBackgroundResource(resourcesID);
        }
        else
        {
            tvQuestion.setText(mstrQuestion);
            //
            // int resourceID = getActivity().getResources().get  @drawable/common_google_signin_btn_icon_dark_normal_background
            tvQuestion.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background);
        }


       // tvQuestion.setText(mstrQuestion);





        mstrAnswerA = "A. " + data[index[mansList.get(0)]][1];
        butAnswerA.setText(mstrAnswerA);
        mstrAnswerB = "B. " + data[index[mansList.get(1)]][1];
        butAnswerB.setText(mstrAnswerB);
        mstrAnswerC = "C. " + data[index[mansList.get(2)]][1];
        butAnswerC.setText(mstrAnswerC);
        mstrAnswerD = "D. " + data[index[mansList.get(3)]][1];
        butAnswerD.setText(mstrAnswerD);
        mstrNum = String.format("%02d/%02d",mQuestionIndex + 1,data.length);
        tvIndex.setText(mstrNum);
        pbarIndex.setProgress(mQuestionIndex);*/
        int answer = mansList.indexOf(3);
        return answer;
    }

    //Create a question randomly, the first three are wrong, and the last one is always the right answer
    public int [] generateQuestion(int quesIndex)
    {
        int [] questionCollection = new int[4];
        Collections.shuffle(mquestionList);
        for (int i = 0; i < 3; i++)
        {
            if (mquestionList.get(i) == quesIndex)
                Collections.swap(mquestionList,i,3);
        }
        for (int i = 0; i < 3; i++)
        {
            questionCollection[i] = mquestionList.get(i);
        }
        questionCollection[3] = quesIndex;      //Last one always is the index of question's answer
        return  questionCollection;
    }


    public int getRadomQuestionIndex(int escape,int max)
    {
        int ans;
        while (true)
        {
            ans = (int)Math.random() * max;
            if (ans != escape)
            {
                return ans;
            }
        }
    }

    public void nextQuestion(int Answer,String txt)
    {
        if (mAnsIndex == Answer)
        {
            //answer correctly

            if (mQuestionIndex == mQuestionNum - 1)
            {

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
            }
            else
            {

                mQuestionIndex = mQuestionIndex + 1;

            }



            mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
            FreshUI();


           // String strAudioPath = txt.subSequence(2,(txt.length())).toString();
           // strAudioPath = strAudioPath.replace(" ","_");
           // int drawableResourceId = this.getResources().getIdentifier(strAudioPath, "raw", getPackageName());
           // MediaPlayer mediaPlayer = MediaPlayer.create(this,drawableResourceId);
           // mediaPlayer.start();
        }
        else
        {

          //  Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
           // v. vibrate(20);
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
    public void onDestroyView() {
        super.onDestroyView();
        int a = 1;
        a = a+ 1 ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.layout_quize, container, false);
        TextView tv = view.findViewById(R.id.textView_question);
        tv.setText(String.format("%02d",mnpos));
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            int a = savedInstanceState.getInt("curChoice", 0);
            a = a + 1;
        }

        butAnswerA = (Button)view.findViewById(R.id.button_answer_a);
        butAnswerB = (Button)view.findViewById(R.id.button_answer_b);
        butAnswerC = (Button)view.findViewById(R.id.button_answer_c);
        butAnswerD = (Button)view.findViewById(R.id.button_answer_d);
        tvQuestion = (TextView)view.findViewById(R.id.textView_question);
        tvIndex = (TextView)view.findViewById(R.id.textView_Count);
        pbarIndex = (ProgressBar)view.findViewById(R.id.progressBar);
        pbarIndex.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);


      //  public String mstrQuestion,mstrAnswerA,mstrAnswerB,mstrAnswerC,mstrAnswerD,mstrNum;


        FreshUI();

     //   if (!mbFristEnter) {
         //   butAnswerA.setText(mstrAnswerA);
          //  butAnswerB.setText(mstrAnswerB);
         //   butAnswerC.setText(mstrAnswerC);
          //  butAnswerD.setText(mstrAnswerD);
          //  tvQuestion.setText(mstrQuestion);
          //  tvIndex.setText(mstrNum);
      //  }
     //   else
      //  {*/

        //    mQuestionIndex = 0;
         //   mAnsIndex = 0;
         //   Collections.shuffle(mRadomQuestionIndx);
         //   mAnsIndex = CreateQuestion(mRadomQuestionIndx.get(mQuestionIndex));
            pbarIndex.setMax(data.length);
           // mbFristEnter = false;


        butAnswerA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                nextQuestion(0,butAnswerA.getText().toString());
            }
            });
        butAnswerB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                nextQuestion(1,butAnswerB.getText().toString());
            }
        });
        butAnswerC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                nextQuestion(2,butAnswerC.getText().toString());
            }
        });
        butAnswerD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                nextQuestion(3,butAnswerC.getText().toString());
            }
        });


        return view;

    }




}

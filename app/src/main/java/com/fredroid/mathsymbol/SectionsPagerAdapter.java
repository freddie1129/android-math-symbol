package com.fredroid.mathsymbol;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jackttc on 15/8/17.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {



    Context mcontext;
    public FragmentManager mFragmentManager;
    public Fragment mFragmentAtPos0;
   /// CalendarPageListener listener = new CalendarPageListener();

    public static int TAB_NUM = 7;
    public Fragment mPosFragment0,mPosFragment1,mPosFragment2,mPosFragment3,mPosFragment4,mPosFragment5,mPosFragment6;
    public Fragment []mShowFragmentArray = {null,null,null,null,null,null,null};
    public Fragment []mQuizeFragmentArray = {null,null,null,null,null,null,null};
    public Fragment [] mCurrentFragment = {null,null,null,null,null,null,null};

    public SectionsPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        mcontext = context;
        mFragmentManager = fm;
        for (int i = 0; i < TAB_NUM; i++)
        {
            mShowFragmentArray[i] = PlaceholderFragment.newInstance(i);
            mQuizeFragmentArray[i] = QuizeFragment.newInstance(i);
        }
    }

    @Override
    public Fragment getItem(int position) {

    //    if (mCurrentFragment[position] == null)
    //        mCurrentFragment[position] = mShowFragmentArray[position];
    //    return mCurrentFragment[position];
           // String a = String.valueOf(position);
            //String b = String.valueOf(position * 100);
            return ContainerFragment.newInstance(position);
     /*   switch (position) {
            case 0:
                if (mPosFragment0 == null)
                    mPosFragment0 = mShowFragmentArray[position];
                return mPosFragment0;
            case 1:
                if (mPosFragment1 == null)
                    mPosFragment1 = mShowFragmentArray[position];
                return mPosFragment1;
            case 2:
                if (mPosFragment2 == null)
                    mPosFragment2 = mShowFragmentArray[position];
                return mPosFragment2;
            case 3:
                if (mPosFragment3 == null)
                    mPosFragment3 = mShowFragmentArray[position];
                return mPosFragment3;
            case 4:
                if (mPosFragment4 == null)
                    mPosFragment4 = mShowFragmentArray[position];
                return mPosFragment4;
            case 5:
                if (mPosFragment5 == null)
                    mPosFragment5 = mShowFragmentArray[position];
                return mPosFragment5;
            case 6:
                if (mPosFragment6 == null)
                    mPosFragment6 = mShowFragmentArray[position];
                return mPosFragment6;
            default:
                return PlaceholderFragment.newInstance(position);
        }*/


    }

    public void switchMode(int position)
    {
        //mFragmentManager.beginTransaction().remove(mCurrentFragment[position]).commitNow();
        if (mCurrentFragment[position] instanceof PlaceholderFragment){
            mCurrentFragment[position] =  mQuizeFragmentArray[position];
        }else{ // Instance of NextFragment
            mCurrentFragment[position] = mShowFragmentArray[position];
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemPosition(Object object) {
       // int npos  = get
      //  if (object instanceof PlaceholderFragment && mFragmentAtPos0 instanceof QuizeFragment)
      //      return POSITION_NONE;
      //  if (object instanceof QuizeFragment && mFragmentAtPos0 instanceof PlaceholderFragment)
      //      return POSITION_NONE;
        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return TAB_NUM;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mcontext.getString(R.string.mathBasic);
            case 1:
                return mcontext.getString(R.string.mathGeometry);
            case 2:
                return mcontext.getString(R.string.mathAlgebra);
            case 3:
                return mcontext.getString(R.string.mathProbability);
            case 4:
                return mcontext.getString(R.string.mathSet);
            case 5:
                return mcontext.getString(R.string.mathLogic);
            case 6:
                return mcontext.getString(R.string.mathCalculus);
        }
        return null;
    }
}


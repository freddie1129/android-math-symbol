package com.fredroid.mathsymbol;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {


    public static int TAB_NUM = 7;
    public FragmentManager mFragmentManager;
    public Fragment[] mShowFragmentArray = {null, null, null, null, null, null, null};
    public Fragment[] mQuizeFragmentArray = {null, null, null, null, null, null, null};
    public Fragment[] mCurrentFragment = {null, null, null, null, null, null, null};
    Context mcontext;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mcontext = context;
        mFragmentManager = fm;
        for (int i = 0; i < TAB_NUM; i++) {
            mShowFragmentArray[i] = PlaceholderFragment.newInstance(i);
            mQuizeFragmentArray[i] = QuizeFragment.newInstance(i);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return ContainerFragment.newInstance(position);
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_UNCHANGED;
    }

    @Override
    public int getCount() {
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


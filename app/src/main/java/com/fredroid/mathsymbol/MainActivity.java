package com.fredroid.mathsymbol;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private  boolean mbQuiz = false;


  /*  private final class CalendarPageListener implements
            CalendarPageFragmentListener {
        public void onSwitchToNextFragment() {
            mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
                    .commit();
            if (mFragmentAtPos0 instanceof PlaceholderFragment){
                mFragmentAtPos0 = QuizeFragment.newInstance(listener);
            }else{ // Instance of NextFragment
                mFragmentAtPos0 = PlaceholderFragment.newInstance(listener);
            }
            notifyDataSetChanged();
        }
    }*/


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    public boolean onSupportNavigateUp() {


            finish();


        return  super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),this);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    //    View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
    //    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
     //           | View.SYSTEM_UI_FLAG_FULLSCREEN;
     //   decorView.setSystemUiVisibility(uiOptions);


      //  vp.setMinimumHeight(rch - navigationHeight);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

      //  fab.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {

              //  SectionsPagerAdapter sp = (SectionsPagerAdapter)mViewPager.getAdapter();
              //  ContainerFragment cf = (ContainerFragment)sp.getItem(0);
             //   cf.switchw();

         /*     int position = tabLayout.getSelectedTabPosition();

              mSectionsPagerAdapter.mFragmentManager.beginTransaction().remove(mSectionsPagerAdapter.mCurrentFragment[position]).commitNow();
                if (mSectionsPagerAdapter.mCurrentFragment[position] instanceof PlaceholderFragment){
                    mSectionsPagerAdapter.mCurrentFragment[position] =  mSectionsPagerAdapter.mQuizeFragmentArray[position];
                }else{
                    mSectionsPagerAdapter.mCurrentFragment[position] = mSectionsPagerAdapter.mShowFragmentArray[position];
                }
                mViewPager.setAdapter(mSectionsPagerAdapter);
                mSectionsPagerAdapter.notifyDataSetChanged();
                mViewPager.setCurrentItem(position);


*/


             /*           mSectionsPagerAdapter.mFragmentManager.beginTransaction().remove(mSectionsPagerAdapter.mFragmentAtPos0)
                                .commitNow();
                        if (mSectionsPagerAdapter.mFragmentAtPos0 instanceof PlaceholderFragment){
                            mSectionsPagerAdapter.mFragmentAtPos0 = QuizeFragment.newInstance(1);
                        }else{ // Instance of NextFragment
                            mSectionsPagerAdapter.mFragmentAtPos0 = PlaceholderFragment.newInstance(1);
                        }
                mSectionsPagerAdapter.notifyDataSetChanged();*/

                }


                //mViewPager.removeView(mViewPager.getChildAt(mViewPager.getCurrentItem()));  //
                //FragmentManager fm = getSupportFragmentManager();


             //           .get(0) = QuizeFragment.newInstance(1);
                //  mSectionsPagerAdapter.
              //  mViewPager.setCurrentItem(0);
           //     View q = QuizeFragment.newInstance(1);  //.getView();
            //    mViewPager.addView(q,0);
           //     mViewPager.setCurrentItem(0);
                //mViewPager.
               // mViewPager.getAdapter().destroyItem((ViewGroup) findViewById(R.id.container),1,);
              //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

   //     });
//
 //   }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("About");
            alertDialog.setMessage("When I started my Australia uni last year, math symbols' English name is a tough thing for me. I developed this app to help me remember them. \nHope it useful for you as well. \n\nfreddiechenchen@gmail.com");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    @Override
    public void onBackPressed() {


        finish();

   /*     if (mSectionsPagerAdapter.mFragmentAtPos0 instanceof PlaceholderFragment){

            finish();

        }else{ // Instance of NextFragment
            mSectionsPagerAdapter.mFragmentManager.beginTransaction().remove(mSectionsPagerAdapter.mFragmentAtPos0)
                    .commitNow();
            mSectionsPagerAdapter.mFragmentAtPos0 = PlaceholderFragment.newInstance(1);
        }
        mSectionsPagerAdapter.notifyDataSetChanged();*/

    }
}

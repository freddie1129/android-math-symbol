package com.fredroid.mathsymbol;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContainerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_POSITION = "tabposition";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int mTabPosition;

    private boolean mModeShow = true;

    private PlaceholderFragment mShowFragment = null;
    private QuizeFragment mQuizeFragment = null;


    public ContainerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContainerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContainerFragment newInstance(int pos) {
        ContainerFragment fragment = new ContainerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, pos);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTabPosition = getArguments().getInt(ARG_POSITION);
            if (mShowFragment == null)
                mShowFragment = PlaceholderFragment.newInstance(mTabPosition);
            if (mQuizeFragment == null)
                 mQuizeFragment = QuizeFragment.newInstance(mTabPosition);
        //    mParam1 = getArguments().getString(ARG_PARAM1);
          //  mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_container, container, false);
     //   TextView tv = (TextView) view.findViewById(R.id.textView_container);
     //   tv.setText(String.valueOf(mTabPosition) + "----" );

        if (mModeShow) {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction().replace(R.id.container_frame, mShowFragment);
            ft.commit();
        }
        else
        {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction().replace(R.id.container_frame, mQuizeFragment);
            ft.commit();
        }


        final Button button = (Button) view.findViewById(R.id.button_mode);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String str = button.getText().toString();
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                if (mModeShow)
                {
                    ft.replace(R.id.container_frame,mQuizeFragment);
                    Button button = v.findViewById(R.id.button_mode);
                    button.setText(getString(R.string.buttonSwitch_quize));
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else
                {
                    if (getChildFragmentManager().getBackStackEntryCount() > 0) {
                        getChildFragmentManager().popBackStack();}
                   // ft.replace(R.id.container_frame,mShowFragment);
                    Button button = v.findViewById(R.id.button_mode);
                    button.setText(getString(R.string.buttonSwitch_show));

                }
              //  ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
               // ft.addToBackStack(null);
              //  ft.commit();
                mModeShow = !mModeShow;
      /*          FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                StaffFragment elf = new StaffFragment().newInstance("abc","def");
                BossFragment  boss = new BossFragment().newInstance("abc","def");

                if(mode == 0)
                {
                    ft.replace(R.id.FrameParent, elf);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                    mode = 1;
                }
                else
                {
                    ft.replace(R.id.FrameParent, boss);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                    mode  =0;
                }
                // do something*/
            }
        });
        return  view;

    }

    public void switchw()
    {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (mModeShow)
        {
            ft.replace(R.id.container_frame,mQuizeFragment);

        }
        else
        {
            ft.replace(R.id.container_frame,mShowFragment);

        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();
        mModeShow = !mModeShow;
    }

}

package com.fredroid.mathsymbol;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class ContainerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_POSITION = "tabposition";
    private int mTabPosition;
    private boolean mModeShow = true;

    private PlaceholderFragment mShowFragment = null;
    private QuizeFragment mQuizeFragment = null;


    public ContainerFragment() {
    }


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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        if (mModeShow) {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction().replace(R.id.container_frame, mShowFragment);
            ft.commit();
        } else {
            FragmentTransaction ft = getChildFragmentManager().beginTransaction().replace(R.id.container_frame, mQuizeFragment);
            ft.commit();
        }


        final Button button = view.findViewById(R.id.button_mode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = button.getText().toString();
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                if (mModeShow) {
                    ft.replace(R.id.container_frame, mQuizeFragment);
                    Button button = v.findViewById(R.id.button_mode);
                    button.setText(getString(R.string.buttonSwitch_quize));
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    if (getChildFragmentManager().getBackStackEntryCount() > 0) {
                        getChildFragmentManager().popBackStack();
                    }
                    // ft.replace(R.id.container_frame,mShowFragment);
                    Button button = v.findViewById(R.id.button_mode);
                    button.setText(getString(R.string.buttonSwitch_show));

                }

                mModeShow = !mModeShow;

            }
        });
        return view;

    }


}

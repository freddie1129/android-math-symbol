package com.fredroid.mathsymbol;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jackttc on 15/8/17.
 */

public  class PlaceholderFragment extends ListFragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int npos = getArguments().getInt(ARG_SECTION_NUMBER) + 1;
        final PlaceholderFragment.SymbolAdapter adapter = new PlaceholderFragment.SymbolAdapter(getActivity(),getSymbolList(npos));
        setListAdapter(adapter);

    }

    //@Override
    // public View onCreateView(LayoutInflater inflater, ViewGroup container,
    //                     Bundle savedInstanceState) {
    //   View rootView = inflater.inflate(R.layout.fragment_main, container, false);
    //   TextView textView = (TextView) rootView.findViewById(R.id.section_label);
    //   textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

    //   final SymbolAdapter adapter = new SymbolAdapter(getActivity(),getSymbolList(1));
    //   setListAdapter(adapter);
    //   return super.onCreateView(inflater,container,savedInstanceState);
    //}

    public ArrayList<Symbol> getSymbolList(int nMode)
    {
        ArrayList<Symbol> symbolList = new ArrayList<Symbol>();
        Symbol s1;
        String [][] data;
        switch (nMode) {
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


        for (int i =  0; i < data.length; i++)
            symbolList.add(new Symbol(data[i][0],data[i][1]));
        return symbolList;
    }
    public class Symbol {
        public String strmark;
        public String strspell;
        public Symbol(String strmark, String strspell) {
            this.strmark = strmark;
            this.strspell = strspell;
        }
    }

    public class SymbolAdapter extends ArrayAdapter<Symbol> {

        public SymbolAdapter(Context context, ArrayList<Symbol> users) {
            super(context,0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Symbol user = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_row, parent, false);
            }

            TextView tvSymbol = (TextView) convertView.findViewById(R.id.textView_symbol);


            TextView tvWord = (TextView) convertView.findViewById(R.id.textView_mark);
            if(user.strmark.equals("fig"))
            {
                tvSymbol.setText("");
                String figname = user.strspell.replace(" ","_").toLowerCase();
                //figname.toLowerCase()
                int resourcesID=getActivity().getResources().getIdentifier(figname,"drawable",getActivity().getPackageName());
                tvSymbol.setBackgroundResource(resourcesID);
            }
            else
            {
                tvSymbol.setText(user.strmark);
               //
                // int resourceID = getActivity().getResources().get  @drawable/common_google_signin_btn_icon_dark_normal_background
                tvSymbol.setBackgroundResource(R.drawable.common_google_signin_btn_icon_dark_normal_background);
            }


            tvWord.setText(user.strspell);
            return convertView;
        }
    }
}

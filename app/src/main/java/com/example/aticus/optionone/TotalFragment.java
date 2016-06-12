package com.example.aticus.optionone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHandler.DatabaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment implements EditText.OnEditorActionListener{


    private EditText amount;
    private View view;
    boolean flag=false;
    private int counter=0;
    private DatabaseAdapter databaseAdapter;

    public TotalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_total, container, false);
        amount= (EditText) view.findViewById(R.id.inputAmount);
        amount.setOnEditorActionListener(this);

        databaseAdapter = new DatabaseAdapter(getActivity());

        /*amount.addTextChangedListener(new TextWatcher() {
            CharSequence previous;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //   Toast.makeText(getActivity(),"before char= "+s+" start="+start+" count="+count+" after="+after,Toast.LENGTH_LONG).show();

                //   Toast.makeText(getActivity(),"before char= "+s.charAt(start)+" start="+start+" count="+count+" after="+after,Toast.LENGTH_LONG).show();
                previous=s;
            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                if(s.charAt(start)=='+'||s.charAt(start)=='-')
                {
                    counter=0;
                }

                ++counter;
                if(counter>14)
                {
                    amount.setText(previous);
                    Toast.makeText(getActivity(),"Debit and Credit should be less than 14 digits",Toast.LENGTH_LONG).show();

                }
                Toast.makeText(getActivity(),counter+"",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void afterTextChanged(Editable s) {
                //   Toast.makeText(getActivity(),"AfterTextChanged char= "+s,Toast.LENGTH_LONG).show();

            }
        });*/
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return view;


    }
    @Override

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
    {
//

        if (actionId == EditorInfo.IME_ACTION_DONE)
        {

            if (amount.getText().toString().matches("") || amount.getText().toString().matches("0"))
            {

                Toast.makeText(getActivity(), "Enter Some Input", Toast.LENGTH_SHORT).show();
            }
            else if(amount.getText().toString().matches("((\\d+)|([+-]\\d+))([+-]\\d+)*"))
            {
                databaseAdapter.insert(amount.getText().toString());
                amount.setText("");
            }
            else
            {
                Toast.makeText(getActivity(), "Input Not In Proper Format", Toast.LENGTH_SHORT).show();
            }

        }
        return false;
    }



}

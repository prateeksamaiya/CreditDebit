package com.example.aticus.optionone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
            }
            else
            {
                Toast.makeText(getActivity(), "Input Not In Proper Format", Toast.LENGTH_SHORT).show();
            }

        }
        return false;
    }



}

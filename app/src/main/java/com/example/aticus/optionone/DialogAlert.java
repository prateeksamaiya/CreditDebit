package com.example.aticus.optionone;

/**
 * Created by aticus on 12-06-2016.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by aticus on 12/8/2015.
 */
public class DialogAlert extends DialogFragment
{
    private long columnId;
    CreditFragment creditFragment;
    public long getColumnId() {
        return columnId;
    }


    public void setColumnId(long columnId,CreditFragment creditFragment) {
        this.columnId = columnId;
        this.creditFragment=creditFragment;
        communicator=creditFragment;
    }

    Communicator communicator;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you what to delete ths entry");
        builder.setTitle("Delete Entry");
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                creditFragment.updateAdapter();
            }
        });
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                communicator.dialogSelection();


            }
        });
        Dialog dialog=builder.create();

        return dialog;
    }

    interface Communicator
    {
        public void dialogSelection();
    }

}
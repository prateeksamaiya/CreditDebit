package com.example.aticus.optionone;




        import android.content.Context;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Toast;

        import DatabaseHandler.DatabaseAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DebitFragment extends Fragment{


    private RecyclerView recyclerView;
    private static MyListCursorAdapter listCursorAdapteristAdapter;
    static Context context;
    private static final int VERTICAL_ITEM_SPACE = 24;
    private static DatabaseAdapter databaseAdapter;

    public DebitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        // Inflate the layout for this fragment
        databaseAdapter = new DatabaseAdapter(getActivity());
        View view=inflater.inflate(R.layout.fragment_debit, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.debit_recyclerView);
        Cursor c= databaseAdapter.showDebitRecord();
        listCursorAdapteristAdapter=new MyListCursorAdapter(getActivity(),c);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        recyclerView.setAdapter(listCursorAdapteristAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;



    }

    public static void updateAdapter()
    {
        listCursorAdapteristAdapter.changeCursor(databaseAdapter.showDebitRecord());
    }

}

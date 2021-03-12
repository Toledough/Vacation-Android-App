package com.example.ntoled3proj3app3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

public class VacationsFragment extends ListFragment {

    private ListSelectionListener mListener = null;

    //Current Index.
    int current = -1;

    //For notifying main activity when user clicks on item.
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    //Deselects/unhighlights last item/
    public void deselectLastItem(){

        if(current != -1){
            getListView().setItemChecked(current, false);
        }

        //Reset index.
        current = -1;

    }

    //Reselects/highlights last index.
    public void reselectLastItem(int index){

        if(index != -1){
            getListView().setItemChecked(index, true);
        }

        //Update current index.
        current = index;
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        current = pos;

        //Highlight clicked item.
        getListView().setItemChecked(pos, true);

        //Inform main activity.
        mListener.onListSelection(pos);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {

            // Set the listener for notifying main activity..
            mListener = (ListSelectionListener) activity;

        } catch (ClassCastException e) {

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Update current index to previously saved index.
        if(savedInstanceState != null){
            current = savedInstanceState.getInt("index");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        // Only one item selected at a time.
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set list adapter.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.vacation_item, MainActivity.mVacationTitleArray));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        //Save current index in bundle.
        outState.putInt("index", current);
        super.onSaveInstanceState(outState);
    }


}

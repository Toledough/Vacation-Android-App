package com.example.ntoled3proj3app3;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class VacationImageFragment extends Fragment {

    private ImageView mVacationImageView = null;
    private int mCurrIdx = -1;
    private int mVacationTitleArrayLen;

    //Returns current index.
    public int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Vacation image at index parameter.
    public void showImageAtIndex(int newIndex, Vacation selected) {
        if (newIndex < 0 || newIndex >= mVacationTitleArrayLen)
            return;

        //Update current index and display image.
        mCurrIdx = newIndex;
        mVacationImageView.setImageResource(selected.highRes);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Create content view for fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate layout.
        return inflater.inflate(R.layout.vacation_image, container, false);
    }

    // Set up some information about the mQuoteView TextView
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //If restoring info.
        if (savedInstanceState != null){
            //Restore previous index.
            mCurrIdx = savedInstanceState.getInt("index");

            //Show previous indexes image.
            showImageAtIndex(mCurrIdx, null);
        }

        //Get reference to vacationImage, and set length,
        mVacationImageView = (ImageView) getActivity().findViewById(R.id.vacationImage);
        mVacationTitleArrayLen = MainActivity.mVacationTitleArray.length;
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

}

package com.example.ntoled3proj3app3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements VacationsFragment.ListSelectionListener {

    //List of vacations.
    ArrayList<Vacation> vacationsList;

    //For vacation titles.
    static String[] mVacationTitleArray;

    //For keeping track of current item's index.
    int currentIndex = -1;

    //Fragment variables
    private VacationImageFragment mVacationImageFragment = new VacationImageFragment();
    private VacationsFragment mVacationsFragment = new VacationsFragment();

    //Fragment manager.
    FragmentManager mFragmentManager;

    //Frame layouts.
    private FrameLayout mVacationFrameLayout, mVacationImageFrameLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Get toolbar reference and set as action bar.
        Toolbar tBar = findViewById(R.id.tool_bar);
        setSupportActionBar(tBar);

        //Set title and icon to action bar.
        getSupportActionBar().setTitle("ntoled3Proj3App3");
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);

        //Get fragment manager.
        mFragmentManager = getSupportFragmentManager();

        // Get the string arrays with vacation titles.
        mVacationTitleArray = getResources().getStringArray(R.array.VacationTitles);

        //Get frame layout references.
        mVacationFrameLayout = (FrameLayout) findViewById(R.id.title_fragment_container);
        mVacationImageFrameLayout = (FrameLayout) findViewById(R.id.quote_fragment_container);

        //Add vacations to vacationsList.
        vacationsList = new ArrayList<Vacation>(Arrays.asList(
                new Vacation("Tokyo", "https://www.japan-guide.com/e/e2164.html", R.drawable.tokyo),
                new Vacation("New Zealand", "https://www.newzealand.com/us/", R.drawable.newzealand),
                new Vacation("Machu Picchu", "https://www.machupicchu.org/", R.drawable.machupicchu),
                new Vacation("Bali", "https://www.bali.com/", R.drawable.bali),
                new Vacation("Rio de Janiero", "https://www.riodejaneiro.com/", R.drawable.riodejaneiro)
        ));

        //Add vacationsFragment in transaction.
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mVacationsFragment = new VacationsFragment();

        fragmentTransaction.replace(
                R.id.title_fragment_container,
                mVacationsFragment, "vacationTag");

        fragmentTransaction.commit();

        //Restore state
        if (savedInstanceState != null){

            //Get previous fragment.
            Fragment previousFrag = mFragmentManager.getFragment(savedInstanceState, "vacationImageKey");

            //If previous fragment exists, set it as Image fragment.
            if(previousFrag != null){
                mVacationImageFragment = (VacationImageFragment) previousFrag;
            }

        }

        //Change layout upon backstack change.
        mFragmentManager.addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();

                    }
                });

        //Set layout.
        setLayout();

    }


    private void setLayout() {

        //Vacation Image fragment not added.
        if (!mVacationImageFragment.isAdded()) {

            //Deselect/unhighlight last item, reset current index.
            if(mVacationsFragment != null){
                currentIndex = -1;
                mVacationsFragment.deselectLastItem();
            }

            //Update fragment layout parameters (Only vacations visible).
            mVacationFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mVacationImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));

        }
        //Showing Vacation Image.
        else {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                //Update fragment layout parameters (Vacations not visible).
                mVacationFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0f));

                // Vacation Image takes up whole frame.
                mVacationImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
            }
            else {
                //Update fragment layout parameters
                //Vacations take 1/3 space.
                mVacationFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                //Vacation Image take 2/3 space.
                mVacationImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
    }


    //Called when list item clicked.
    @Override
    public void onListSelection(int index) {

        //Update currentIndex.
        currentIndex = index;

        //If VacationImage has not been addded.
        if (!mVacationImageFragment.isAdded()) {

            //Add using Fragment manager.
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            fragmentTransaction.add(R.id.quote_fragment_container,
                    mVacationImageFragment, "vacationImageTag");

            // Add to backstack.
            fragmentTransaction.addToBackStack(null);

            // Commit and execute FragmentTransaction
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }

        //Show clicked Vacation's Image.
        mVacationImageFragment.showImageAtIndex(index, vacationsList.get(index));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {


        //Retain vacationImage instance.
        mVacationImageFragment.setRetainInstance(true);

        if (mVacationImageFragment.isAdded()){

            //Store vacationImage fragment in fragment manager.
            mFragmentManager.putFragment(outState, "vacationImageKey", mVacationImageFragment);

            //Store currentshown index in bundle.
            outState.putInt("index", mVacationImageFragment.getShownIndex());
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        //Get previous vacationImage fragment.
        Fragment previousFrag = mFragmentManager.getFragment(savedInstanceState, "vacationImageKey");

        //If non-null
        if(previousFrag != null){

            //Update current index.
            currentIndex = savedInstanceState.getInt("index");

            //Reselect previous item.
            mVacationsFragment.reselectLastItem(currentIndex);

            if(currentIndex != -1){
                //Show image at current index.
                mVacationImageFragment.showImageAtIndex(currentIndex, vacationsList.get(currentIndex));
            }

        }

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Inflate options menu.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:

                //Exit App
                this.finishAffinity();

                return true;

            case R.id.option2:

                //If item is selected.
                if(currentIndex != -1){

                    //Create intent, and send ordered broadcast.
                    Intent intent = new Intent("edu.uic.cs478.f20.kaboom");
                    intent.putExtra("webpage", vacationsList.get(currentIndex).website);
                    intent.putExtra("name", vacationsList.get(currentIndex).location);
                    sendOrderedBroadcast(intent, "edu.uic.cs478.f20.kaboom");

                }
                //No item selected.
                else{
                    //Show a toast message.
                    Toast.makeText(getApplicationContext(), "No Vacation Spot Selected",
                            Toast.LENGTH_LONG).show();
                }
                return true;

            default:
                return false;
        }
    }


}
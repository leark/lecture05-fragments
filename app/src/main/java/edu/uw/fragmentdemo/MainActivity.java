package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieSelectedListener {

    private static final String TAG = "MainActivity";

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txtSearch);
        String searchTerm = text.getText().toString();

        //add a new results fragment to the page
        MoviesFragment fragment = MoviesFragment.newInstance(searchTerm);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, "MoviesFragment");
        ft.addToBackStack(null); //remember for the back button
        ft.commit();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment detailFragment = DetailFragment.newInstance(movie.toString(), movie.imdbId);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, detailFragment, null)
                .addToBackStack(null)
                .commit();
    }

    public static class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new SearchFragment;
                case 1: return new MoviesFragment;
                case 2: return new DetailFragment;
                default: return null;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}

package edu.uw.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import static android.support.v4.view.PagerAdapter.POSITION_NONE;

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnMovieClickListener,
        SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";

    private ViewPager vp;

    private SearchFragment sf;
    private MoviesFragment mf;
    private DetailFragment df;

    private PagerAdapter pa;

    public class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        TODO: need to fix
        @Override
        public Fragment getItem(int position) {
            if (position == 0) return sf;
            if (position == 1) return mf;
            if (position == 2) return df;
            return null;
        }

        @Override
        public int getCount() {
//          aftter search and movie clicked
            if (mf != null && df != null) return 3;
//          after search
            if (mf != null) return 2;
//          before search
            return 1;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sf = SearchFragment.newInstance();
        pa = new MoviePagerAdapter(getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setAdapter(pa);
    }




    @Override
    public void onMovieClick(Movie movie){

        df = DetailFragment.newInstance(movie);
        pa.notifyDataSetChanged();
        vp.setCurrentItem(2);
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        mf = MoviesFragment.newInstance(searchTerm);
        pa.notifyDataSetChanged();
        vp.setCurrentItem(1);
    }


    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

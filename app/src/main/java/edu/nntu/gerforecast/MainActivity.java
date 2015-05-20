package edu.nntu.gerforecast;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;


public class MainActivity extends ActionBarActivity
                          implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.container, MainMenuFragment.newInstance(position + 1));
                break;
            case 1:
                transaction.replace(R.id.container,  InputValueFragment.newInstance(position + 1));
                break;
            default:
                transaction.replace(R.id.container, MainMenuFragment.newInstance(position + 1));
                break;
        }
        transaction.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_main_menu);

                break;
            case 2:
                mTitle = getString(R.string.title_input_values);
                break;
            case 3:
                mTitle = getString(R.string.title_result);
                break;
            case 4:
                mTitle = getString(R.string.title_elasticity);
                break;
            case 5:
                mTitle = getString(R.string.title_variation);
                break;
            case 6:
                mTitle = getString(R.string.title_saving_project);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }

    public static abstract class PlaceholderFragment<T extends PlaceholderFragment> extends Fragment {
        protected static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}

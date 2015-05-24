package edu.nntu.gerforecast;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.util.HashMap;
import java.util.Map;

import edu.nntu.gerforecast.fragments.InputValueFragment;
import edu.nntu.gerforecast.fragments.MainMenuFragment;
import edu.nntu.gerforecast.fragments.NavigationDrawerFragment;
import edu.nntu.gerforecast.math.data.InputValues;


public class MainActivity extends ActionBarActivity
                          implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private Map<Integer, PlaceholderFragment> navigationElements = new HashMap<>();
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    private InputValues inputValue = new InputValues();

    public InputValues getInputValue() {
        return inputValue;
    }

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

        PlaceholderFragment currentFragment = navigationElements.get(position);
        if (currentFragment == null) {
            currentFragment = createFragment(position);
            navigationElements.put(position, currentFragment);
        }
        transaction.replace(R.id.container, currentFragment);
        transaction.commit();
    }

    private PlaceholderFragment createFragment(int position) {
        switch (position) {
            case 0:
                return MainMenuFragment.newInstance(position + 1);
            case 1:
                return InputValueFragment.newInstance(position + 1);
            default:
                return MainMenuFragment.newInstance(position + 1);
        }
    }

    public void onSectionAttached(int number) {
        mTitle = getSectionName(number);
    }

    private String getSectionName(int number) {
        switch (number) {
            case 1: return getString(R.string.title_main_menu);
            case 2: return getString(R.string.title_input_values);
            case 3: return getString(R.string.title_result);
            case 4: return getString(R.string.title_elasticity);
            case 5: return getString(R.string.title_variation);
            case 6: return getString(R.string.title_saving_project);
            default: return "Unknown";
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

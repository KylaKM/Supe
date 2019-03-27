package com.example.dininghall;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Dining Hall Menu";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private int[] tabIcons = {
        R.drawable.ic_action_name,
        R.drawable.ic_action_tomorrow,
        R.drawable.ic_action_week
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons(tabLayout);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(), "Today");
        adapter.addFragment(new Tab2Fragment(), "Tomorrow");
        adapter.addFragment(new Tab3Fragment(), "Week");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    Calendar calendar = Calendar.getInstance();
//    int day = calendar.get(Calendar.DAY_OF_WEEK);
//
//    switch (day) {
//        case Calendar.SUNDAY:
//            // Current day is Sunday
//            break;
//        case Calendar.MONDAY:
//            // Current day is Monday
//            break;
//        case Calendar.TUESDAY:
//            // etc.
//            break;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.today:
                Intent today = new Intent(this, MainActivity.class);
                startActivity(today);
                return true;
            case R.id.tomorrow:
                Intent tomorrow = new Intent(this, MainActivity.class);
                startActivity(tomorrow);
                return true;
            case R.id.week:
                Intent week = new Intent(this, MainActivity.class);
                startActivity(week);
                return true;
            case R.id.my_account_info:
                String url = "https://cardservices.mtu.edu";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

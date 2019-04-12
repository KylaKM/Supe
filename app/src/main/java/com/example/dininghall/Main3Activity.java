package com.example.dininghall;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Main3Activity extends AppCompatActivity {

    private static final String TAG = "DHH";
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
        setContentView(R.layout.activity_main3);
        Log.d(TAG, "onCreate: Starting");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container3);
        setupViewPager(mViewPager);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs3);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons(tabLayout);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab31Fragment(), "Today");
        adapter.addFragment(new Tab32Fragment(), "Tomorrow");
        adapter.addFragment(new Tab33Fragment(), "Week");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.Wads:
                Intent Wads = new Intent(this, MainActivity.class);
                startActivity(Wads);
                return true;
            case R.id.McNair:
                Intent McNair = new Intent(this, Main2Activity.class);
                startActivity(McNair);
                return true;
            case R.id.DHH:
                Intent DHH = new Intent(this, Main3Activity.class);
                startActivity(DHH);
                return true;
            case R.id.Cafe:
                Intent Cafe = new Intent(this, Main4Activity.class);
                startActivity(Cafe);
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
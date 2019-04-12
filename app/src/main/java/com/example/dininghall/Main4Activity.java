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

public class Main4Activity extends AppCompatActivity {

    private static final String TAG = "Cafe";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.drawable.ic_action_grill,
            R.drawable.ic_action_pizza,
            R.drawable.ic_action_grill,
            R.drawable.ic_action_drinks
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Log.d(TAG, "onCreate: Starting");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container4);
        setupViewPager(mViewPager);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(myToolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs4);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons(tabLayout);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab41Fragment(), "Grill");
        adapter.addFragment(new Tab42Fragment(), "Pizza");
        adapter.addFragment(new Tab43Fragment(), "Sides");
        adapter.addFragment(new Tab44Fragment(), "Drinks");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main4, menu);
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
package com.sortabletableview.recyclerview.exampleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.sortabletableview.recyclerview.exampleapp.customdata.CustomDataExampleFragment;
import com.sortabletableview.recyclerview.exampleapp.loaddata.LoadDataFragment;
import com.sortabletableview.recyclerview.exampleapp.searchdata.SearchDataFragment;
import com.sortabletableview.recyclerview.exampleapp.selectionhandling.SelectionHandlingFragment;
import com.sortabletableview.recyclerview.exampleapp.simpledata.SimpleDataExampleFragment;
import com.sortabletableview.recyclerview.exampleapp.sortdata.SortDataExampleFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    private static String title = "Welcome";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        final FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                // do stuff
//            }
//        });

        if (savedInstanceState == null || savedInstanceState.getBoolean(FIRST_LAUNCH, true)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, WelcomeFragment.newInstance()).commit();
        }

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setActivityTitle(title);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(FIRST_LAUNCH, false);
    }

    @Override
    public void onBackPressed() {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        switch (id) {
            case R.id.nav_simple_data:
                setActivityTitle(item.getTitle().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, SimpleDataExampleFragment.newInstance()).commit();
                break;
            case R.id.nav_custom_data:
                setActivityTitle(item.getTitle().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, CustomDataExampleFragment.newInstance()).commit();
                break;
            case R.id.nav_load_data:
                setActivityTitle(item.getTitle().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, LoadDataFragment.newInstance()).commit();
                break;
            case R.id.nav_search_data:
                setActivityTitle(item.getTitle().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, SearchDataFragment.newInstance()).commit();
                break;
            case R.id.nav_sort_data:
                setActivityTitle(item.getTitle().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, SortDataExampleFragment.newInstance()).commit();
                break;
            case R.id.nav_selection_handling:
                setActivityTitle(item.getTitle().toString());
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, SelectionHandlingFragment.newInstance()).commit();
                break;
            case R.id.nav_see_on_github:
                final Intent githubIntent = new Intent(Intent.ACTION_VIEW);
                githubIntent.setData(Uri.parse("https://github.com/ISchwarz23/SortableTableView-ExampleApp?files=1"));
                startActivity(githubIntent);
                break;
            case R.id.nav_see_homepage:
                final Intent homepageIntent = new Intent(Intent.ACTION_VIEW);
                homepageIntent.setData(Uri.parse("https://www.sortabletableview.com"));
                startActivity(homepageIntent);
                break;
            case R.id.nav_support:
                final Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:"));
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"SortableTableView Support-Team <support@sortabletableview.com>"});
                mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request from Example App");
                mailIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(Intent.createChooser(mailIntent, "Send Email to Support"));
//                startActivity(new Intent(this, SupportActivity.class));
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, WelcomeFragment.newInstance()).commit();
        }

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setActivityTitle(final String title) {
        this.title = title;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}

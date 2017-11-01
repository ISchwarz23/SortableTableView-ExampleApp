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
import android.view.Menu;
import android.view.MenuItem;
import com.sortabletableview.recyclerview.exampleapp.customdata.CustomDataExampleFragment;
import com.sortabletableview.recyclerview.exampleapp.loaddata.LoadDataFragment;
import com.sortabletableview.recyclerview.exampleapp.selectionhandling.SelectionHandlingFragment;
import com.sortabletableview.recyclerview.exampleapp.simpledata.SimpleDataExampleFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String FIRST_LAUNCH = "FIRST_LAUNCH";

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
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        getSupportActionBar().setTitle(item.getTitle());
        switch (id) {
            case R.id.nav_simple_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, SimpleDataExampleFragment.newInstance()).commit();
                break;
            case R.id.nav_custom_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, CustomDataExampleFragment.newInstance()).commit();
                break;
            case R.id.nav_load_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, LoadDataFragment.newInstance()).commit();
                break;
            case R.id.nav_selection_handling:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, SelectionHandlingFragment.newInstance()).commit();
                break;
            case R.id.nav_see_on_github:
                final Intent githubIntent = new Intent(Intent.ACTION_VIEW);
                githubIntent.setData(Uri.parse("https://github.com/ISchwarz23/SortableTableView-ExampleApp"));
                startActivity(githubIntent);
                break;
            case R.id.nav_support:
                startActivity(new Intent(this, SupportActivity.class));
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.content_container, WelcomeFragment.newInstance()).commit();
        }

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

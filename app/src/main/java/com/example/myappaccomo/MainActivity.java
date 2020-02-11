package com.example.myappaccomo;

import android.content.Intent;
import android.os.Bundle;

import com.example.myappaccomo.activities.AddAdActivity;
import com.example.myappaccomo.databases.AdDatabaseHelper;
import com.example.myappaccomo.entities.Ad;
import com.example.myappaccomo.entities.User;
import com.example.myappaccomo.recyclerview.AdRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import static com.example.myappaccomo.entities.Constants.ADD_AD_ACTIVITY_CODE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    List<Ad> ads;
    AdRecyclerViewAdapter adapter;
    AdDatabaseHelper database;
    View rootView;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAd();
            }
        });

        rootView = findViewById(android.R.id.content).getRootView();

        RecyclerView adsRecyclerView = findViewById(R.id.adsRecyclerView);
//        adsRecyclerView.setHasFixedSize(true);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        adsRecyclerView.setLayoutManager(linearLayoutManager);



        database = new AdDatabaseHelper(this);
        ads = database.getAds();
        adapter = new AdRecyclerViewAdapter(ads, this);
        adsRecyclerView.setAdapter(adapter);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_tools,
                R.id.nav_share, R.id.nav_help, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Bundle b = getIntent().getExtras();
        View header = navigationView.getHeaderView(0);
         TextView  navUsername = header.findViewById(R.id.userNameId);
        TextView navUserEmail = header.findViewById(R.id.userEmail);

        navUsername.setText(b.getCharSequence("name"));
        navUserEmail.setText(b.getCharSequence("email"));

    }








    private void addNewAd() {

        Intent goToCreateAd = new Intent(this, AddAdActivity.class);
        startActivityForResult(goToCreateAd, ADD_AD_ACTIVITY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String message;
        if(requestCode == ADD_AD_ACTIVITY_CODE){
            if(resultCode == RESULT_OK){
                Ad ad = (Ad) data.getSerializableExtra(Ad.AD_KEY);
                //insert the monster into the DB

                Long result =  database.insert(ad.getCategory(), ad.getType(), ad.getDescription());
                //result holds the autogenerated id in the table
                if(result != -1) {
                    ad = database.getAd(result);
                    //try Always to use the adapter to modify the elements of your RecyclerView
                    adapter.addItem(ad);
                    message = "Your ad has been posted";
                }else{
                    message ="Sorry we couldn't add your ad, please try again ";
                }
                Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            // Handle the camera action
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_help) {

       } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}

package com.isetkelibia.customlistviewnd;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected ListView maListViewPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Drawer Navigation */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /* Fin Drawer Navigation */

        /* ListView */
        // Récupération de la "ListView" créée dans le fichier activity_main.xml
        maListViewPerso = findViewById(R.id.listviewperso);

        // Création de la "ArrayList" qui nous permettra de remplir la "ListView"
        ArrayList<HashMap<String, String>> listItems = new ArrayList<>();

        // On déclare la "HashMap" qui contiendra les informations pour un item
        HashMap<String, String> item;

        // Titre des items
        String[] title = new String[]{
                getResources().getString(R.string.word),
                getResources().getString(R.string.excel),
                getResources().getString(R.string.powerpoint),
                getResources().getString(R.string.outlook)};
        // Description des items
        String[] description = new String[]{
                getResources().getString(R.string.word_description),
                getResources().getString(R.string.excel_description),
                getResources().getString(R.string.powerpoint_description),
                getResources().getString(R.string.outlook_description)};
        // Icones (images) des items
        String[] icon = new String[]{
                String.valueOf(R.drawable.word),
                String.valueOf(R.drawable.excel),
                String.valueOf(R.drawable.powerpoint),
                String.valueOf(R.drawable.outlook)};
        // Creation des items de la liste
        for (int i = 0; i < 4; i++) {
            item = new HashMap<>();
            // Titre
            item.put("title", title[i]);
            // Description
            item.put("description", description[i]);
            // Icone
            item.put("icon", icon[i]);
            listItems.add(item);
        }

        // Creation d l’Adapter
        SimpleAdapter adapter = new SimpleAdapter(this.getBaseContext(),
                listItems,
                R.layout.activity_list_item,
                new String[]{"title", "description", "icon"},
                new int[]{R.id.title, R.id.description, R.id.icon});
        // Association de l’adapter à la liste
        maListViewPerso.setAdapter(adapter);

        // Interaction avec les items de la liste
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap item = (HashMap) maListViewPerso.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "" + item.get("title"),
                        Toast.LENGTH_SHORT).show();
            }
        });

        maListViewPerso.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap item = (HashMap) maListViewPerso.getItemAtPosition(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(getString(R.string.adb_title));
                builder.setMessage(getString(R.string.adb_start_message) + " : " + item.get("title"));
                builder.setIcon(R.drawable.office);
                builder.setPositiveButton(getString(R.string.adb_btn_ok), null);
                builder.show();
                return true;
            }
        });
        /* Fin ListView */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            // Handle the gallery action
        } else if (id == R.id.nav_slideshow) {
            // Handle the slideshow action
        } else if (id == R.id.nav_manage) {
            // Handle the manage action
        } else if (id == R.id.nav_share) {
            // Handle the share action
        } else if (id == R.id.nav_send) {
            // Handle the send action
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

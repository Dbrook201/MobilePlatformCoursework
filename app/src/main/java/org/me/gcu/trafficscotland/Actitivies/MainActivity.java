// Matric Number: S1828977

package org.me.gcu.trafficscotland.Actitivies;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.me.gcu.trafficscotland.Adapter.RecyclerAdapter;
import org.me.gcu.trafficscotland.R;
import org.me.gcu.trafficscotland.classes.DatePickerFragment;
import org.me.gcu.trafficscotland.parser.PullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private PullParser PullParser_object;
    private RecyclerView myRecyclerView;
    private RecyclerAdapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_roadworks:
                            Intent a = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(a);
                            break;
                        case R.id.nav_current_incidents:
                            Intent b = new Intent(MainActivity.this, CurrentActivity.class);
                            startActivity(b);
                            break;
                        case R.id.nav_planned_roadworks:
                            Intent c = new Intent(MainActivity.this, PlannedActivity.class);
                            startActivity(c);
                            break;
                    }
                    return false;
                }
            };

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.getMenu().findItem(R.id.nav_roadworks).setChecked(true);


        myRecyclerView = findViewById(R.id.my_recycler_view);

        PullParser_object = new PullParser("https://trafficscotland.org/rss/feeds/roadworks.aspx");

        try {
            myAdapter = new RecyclerAdapter(PullParser_object.DataFetch());

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
    }
} // End of MainActivity

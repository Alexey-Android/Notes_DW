package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewNoteActivity extends AppCompatActivity {

    EditText noteTitle, note, deadline;
    CheckBox checkBox;
    ImageButton calendar;
    private CalendarView DateCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        init();
    }

    private void init() {

noteTitle = findViewById(R.id.et_noteTitle);
note = findViewById(R.id.et_note);
checkBox = findViewById(R.id.cb_deadline);
calendar = findViewById(R.id.ib_calendar);

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enter_menu, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                String nNoteTitle = noteTitle.getText().toString();
                String nNote = note.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ListNotesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

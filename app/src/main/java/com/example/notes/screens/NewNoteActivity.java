package com.example.notes.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notes.App;
import com.example.notes.R;
import com.example.notes.model.Note;

import java.util.Calendar;

public class NewNoteActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "NewNoteActivity.EXTRA_NOTE";

    private Note note;

    public static void start(Activity caller, Note note) {
        Intent intent = new Intent(caller, NewNoteActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    EditText noteTitle, noteText, dateTime;
    CheckBox checkBox;
    ImageButton calendar, time;
    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();

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
        noteText = findViewById(R.id.et_note);
        checkBox = findViewById(R.id.cb_deadline);
        calendar = findViewById(R.id.ib_calendar);
        time = findViewById(R.id.ib_time);
        dateTime = findViewById(R.id.et_date_time);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            assert note != null;
            noteTitle.setText(note.noteTitle);
            noteText.setText(note.noteText);
            checkBox.setChecked(note.hasDeadline);
            dateTime.setText(note.dateTime);
            if (!checkBox.isChecked()) {
                calendar.setEnabled(false);
                time.setEnabled(false);
            }
        } else {
            note = new Note();
            dateTime.setEnabled(false);
            calendar.setEnabled(false);
            time.setEnabled(false);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    setInitialDateTime();

                    dateTime.setEnabled(true);
                    calendar.setEnabled(true);
                    time.setEnabled(true);

                } else {
                    dateTime.setText("");

                    dateTime.setEnabled(false);
                    calendar.setEnabled(false);
                    time.setEnabled(false);

                }
            }
        });

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

                if (noteTitle.getText().length() > 0 | noteText.getText().length() > 0 | dateTime.getText().length() > 0) {
                    note.noteTitle = noteTitle.getText().toString();
                    note.noteText = noteText.getText().toString();
                    note.dateTime = dateTime.getText().toString();
                    if (checkBox.isChecked()) {
                        note.hasDeadline = true;
                    } else {
                        note.hasDeadline = false;
                    }

                    note.timestamp = System.currentTimeMillis();
                    if (getIntent().hasExtra(EXTRA_NOTE)) {
                        App.getInstance().getNoteDao().update(note);
                    } else {
                        App.getInstance().getNoteDao().insert(note);
                    }
                    // finish();
                    Toast.makeText(this, "Заметка успешно сохранена", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(getApplicationContext(), ListNotesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(NewNoteActivity.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(NewNoteActivity.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        dateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
            Toast.makeText(NewNoteActivity.this, "" + dateAndTime, Toast.LENGTH_LONG).show();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };


}

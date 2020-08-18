package com.example.notes;

import android.app.Application;

import androidx.room.Room;

import com.example.notes.data.AppDatabase;
import com.example.notes.data.NoteDao;

public class App extends Application {
    private AppDatabase database;
    private NoteDao noteDao;
    private Pin pin;
    private static App instance;
    private static KeyStore keystore;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();
        noteDao = database.noteDao();
        pin = new Pin(this);
        /* Конкретная реализация выбирается только здесь.
           Изменением одной строчки здесь,
           мы заменяем реализацию во всем приложении!
        */
        //noteRepository = new FileNoteRepository(this);

        keystore = new KeyStore() {
            @Override
            public boolean writeToFile(String str, String fileName) {
                return false;
            }

            @Override
            public String readFromFile(String fileName) {
                return null;
            }
        };
    }

    public Pin getPin() {
        return pin;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    // Возвращаем интерфейс, а не конкретную реализацию!
    public static KeyStore getKeystore() {
        return keystore;
    }
}

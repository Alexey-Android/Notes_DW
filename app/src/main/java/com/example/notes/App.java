package com.example.notes;


import android.app.Application;

import androidx.room.Room;

import com.example.notes.data.AppDatabase;
import com.example.notes.data.NoteDao;


public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;

    private static App instance;

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

        /* Конкретная реализация выбирается только здесь.
           Изменением одной строчки здесь,
           мы заменяем реализацию во всем приложении!
        */

       /* noteRepository = new FileNoteRepository(this);
        passwordStorage = new SimpleKeystore(this);*/
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
/*    public static NoteRepository getNoteRepository() {
        return noteRepository;
    }*/
    // Возвращаем интерфейс, а не конкретную реализацию!
   /* public static Keystore getKeystore() {
        return keystore;
    }*/
}

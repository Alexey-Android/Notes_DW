package com.example.notes.screens;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.App;
import com.example.notes.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }
}

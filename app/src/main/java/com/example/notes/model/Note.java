package com.example.notes.model;


import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Note implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "noteTitle")
    public String noteTitle;

    @ColumnInfo(name = "noteText")
    public String noteText;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "hasDeadline")
    public boolean hasDeadline;

    @ColumnInfo(name = "dateTime")
    public String dateTime;

    public Note() {
    }

    protected Note(Parcel in) {
        uid = in.readInt();
        noteTitle = in.readString();
        noteText = in.readString();
        timestamp = in.readLong();
        hasDeadline = in.readByte() != 0;
        dateTime = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return uid == note.uid &&
                timestamp == note.timestamp &&
                hasDeadline == note.hasDeadline &&
                Objects.equals(noteTitle, note.noteTitle) &&
                Objects.equals(noteText, note.noteText) &&
                Objects.equals(dateTime, note.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, noteTitle, noteText, timestamp, hasDeadline, dateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(noteTitle);
        dest.writeString(noteText);
        dest.writeLong(timestamp);
        dest.writeByte((byte) (hasDeadline ? 1 : 0));
        dest.writeString(dateTime);
    }
}

package com.example.notes.screens;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.notes.App;
import com.example.notes.R;
import com.example.notes.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.NoteViewHolder> implements ListAdapter {

    private SortedList<Note> sortedList;
    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy, hh:mm");
    static Calendar currentDateAndTime = Calendar.getInstance();

    public Adapter() {

        sortedList = new SortedList<>(Note.class, new SortedList.Callback<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                if (!o2.hasDeadline && o1.hasDeadline) {
                    return -1;
                }
                if (o2.hasDeadline && !o1.hasDeadline) {
                    return 1;
                }

                try {
                    if ((format.parse(o2.dateTime)).getTime() - format.parse(o1.dateTime).getTime() > 0) {
                        return -1;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    if ((format.parse(o2.dateTime)).getTime() - format.parse(o1.dateTime).getTime() < 0) {
                        return 1;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return (int) (o2.timestamp - o1.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Note oldItem, Note newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Note item1, Note item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Note> notes) {
        sortedList.replaceAll(notes);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView noteText, noteTitle, deadLine;
        private Note note;

        private final int DIALOG_DELETE = 1;

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.title_note);
            noteText = itemView.findViewById(R.id.note);
            deadLine = itemView.findViewById(R.id.dead_line);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewNoteActivity.start((Activity) itemView.getContext(), note);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {

                    AlertDialog.Builder dialog = new AlertDialog.Builder(itemView.getContext(), DIALOG_DELETE);
                    dialog.setCancelable(false);
                    dialog.setIcon(R.drawable.ic_baseline_delete_24);
                    dialog.setTitle(R.string.attention);
                    dialog.setMessage(R.string.sure);
                    dialog.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            App.getInstance().getNoteDao().delete(note);
                        }
                    })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });

                    final AlertDialog alert = dialog.create();
                    alert.show();
                    return false;
                }
            });
        }

        public void bind(Note note) {

            this.note = note;
            noteTitle.setText(note.noteTitle);
            if (TextUtils.isEmpty(note.noteTitle)) {
                noteTitle.setVisibility(View.GONE);
            } else {
                noteTitle.setVisibility(View.VISIBLE);
            }
            noteText.setText(note.noteText);
            if (TextUtils.isEmpty(note.noteText)) {
                noteText.setVisibility(View.GONE);
            } else {
                noteText.setVisibility(View.VISIBLE);
            }
            deadLine.setText(note.dateTime);
            if (TextUtils.isEmpty(note.dateTime)) {
                deadLine.setVisibility(View.GONE);
            } else {
                deadLine.setVisibility(View.VISIBLE);
            }
            try {
                Context context = deadLine.getContext();
                if ((currentDateAndTime.getTimeInMillis() - (format.parse(note.dateTime)).getTime()) > 0) {
                    deadLine.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
                } else if ((format.parse(note.dateTime)).getTime() - (currentDateAndTime.getTimeInMillis()) < 86400000 &&
                        (currentDateAndTime.getTimeInMillis() - (format.parse(note.dateTime)).getTime()) < 0) {
                    deadLine.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                } else {
                    deadLine.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}

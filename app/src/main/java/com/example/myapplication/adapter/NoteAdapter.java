package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemNoteBinding;
import com.example.myapplication.model.NoteModel;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    List<NoteModel> list = new ArrayList<>();
    Date currentDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.getDefault());
    String dateText = dateFormat.format(currentDate);
    DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
    String timeText = timeFormat.format(currentDate);

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addText(NoteModel model) {
        list.add(model);
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<NoteModel> filterList) {
    }

    public void addModel(List<NoteModel> noteModel) {
        list = noteModel;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemNoteBinding binding;

        public ViewHolder(@NonNull @NotNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(NoteModel model) {

            binding.rvText.setText(model.getText());
           binding.date.setText(model.getDate());
          binding.time.setText(model.getTime());
        }
    }
}

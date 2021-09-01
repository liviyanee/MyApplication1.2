package com.example.myapplication.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;

import org.jetbrains.annotations.NotNull;

public class SwipeToDelete extends ItemTouchHelper {
    public SwipeToDelete(@NonNull @NotNull Callback callback) {
        super(callback);
    }
}

package com.example.myapplication.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentAddNewNoteBinding;
import com.example.myapplication.model.NoteModel;
import com.example.myapplication.utils.App;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewNote extends Fragment {
    FragmentAddNewNoteBinding binding;
    Date currentDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd.MMMM", Locale.getDefault());
    String dateText = dateFormat.format(currentDate);
    DateFormat timeFormat = new SimpleDateFormat("HH:mm",Locale.getDefault());
    String timeText = timeFormat.format(currentDate);



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddNewNoteBinding.inflate(inflater,container,false);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
                navController.navigateUp();
            }
        });
        binding.date.setText(dateText);
        binding.time.setText(timeText);
        initView();
        return binding.getRoot();
    }

    private void initView() {
        binding.txtReady.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = binding.etDescription.getText().toString().trim();
                if (!text.isEmpty()){
                    NoteModel noteModel = new NoteModel(text,dateText,timeText);
                    App.getDatabase().getDao().insertNote(noteModel);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bundle",noteModel);
                    getParentFragmentManager().setFragmentResult("send",bundle);
                    NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content_main);
                    navController.navigateUp();
                }else {
                    binding.etDescription.setError("Введите заметку");

                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar!=null){
            supportActionBar.hide();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        androidx.appcompat.app.ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar!=null){
            supportActionBar.show();
        }
            }
        }

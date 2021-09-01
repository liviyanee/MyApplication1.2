package com.example.myapplication.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NoteAdapter;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.model.NoteModel;
import com.example.myapplication.utils.App;

import org.jetbrains.annotations.NotNull;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private NoteAdapter adapter = new NoteAdapter();
    private FragmentHomeBinding binding;
    List<NoteModel> list = new ArrayList<>();
    private boolean isDashBoard = false;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getDataFromDB();
        return root;

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
    }

    private void getDataFromDB() {
        App.database.getDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(List<NoteModel> noteModels) {
                adapter.addModel(noteModels);
                list = noteModels;
            }
        });
    }

    private void getData() {
//        getParentFragmentManager().setFragmentResultListener("send", getViewLifecycleOwner(), new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
//                NoteModel model = (NoteModel)
//                        result.getSerializable("bundle");
//                adapter.addModel(model);
//                if (isDashBoard) {
//                   binding.recyclerView.setLayoutManager(staggeredGridLayoutManager);
//                } else {
//                    binding.recyclerView.setLayoutManager(linearLayoutManager);
//                }
//            }
//        });
    }

    private void initView() {
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLayoutManager();

        initView();
        search();
        delete();
        getData();
    }

    private void setupLayoutManager() {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager = new LinearLayoutManager(requireContext());
    }

    private void search() {
//        binding.etSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
    }

    private void filter(String text) {
        ArrayList<NoteModel> filterList = new ArrayList<>();
        for (NoteModel model : list) {
            if (model.getText().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(model);
            }
        }
        adapter.filterList(filterList);
    }

    private void delete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                AlertDialog alert = builder.create();
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.database.getDao().deleteNote(list.get(viewHolder.getAdapterPosition()));
                    }
                });


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.cancel();
                    }
                }).show();
            }
        }).attachToRecyclerView(binding.recyclerView);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.dashboard) {
            if (isDashBoard) {
                item.setIcon(R.drawable.ic_format_list);
                isDashBoard = false;
            } else {
                isDashBoard = true;
                item.setIcon(R.drawable.ic_dashboard_);
            }
            binding.recyclerView.setLayoutManager(isDashBoard ? staggeredGridLayoutManager : linearLayoutManager);
            binding.recyclerView.setAdapter(adapter);
        }
        return false;
    }

}

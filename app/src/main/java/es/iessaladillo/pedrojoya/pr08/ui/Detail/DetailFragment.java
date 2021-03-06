package es.iessaladillo.pedrojoya.pr08.ui.Detail;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import es.iessaladillo.pedrojoya.pr08.R;

public class DetailFragment extends Fragment {


    public static DetailFragment newInstance() {
        return new DetailFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews(requireView());
    }

    private void setupViews(View view) {
        setupToolbar(view);
        FloatingActionButton fab = ViewCompat.requireViewById(view, R.id.fab);
        fab.setOnClickListener(this::save);
    }

    private void save(View v) {
        Snackbar.make(v, getString(R.string.detail_save_message), Snackbar.LENGTH_SHORT).show();
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = ViewCompat.requireViewById(view,R.id.collapsingToolbarLayout);

        collapsingToolbarLayout.setTitle(getString(R.string.detail_title));

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}

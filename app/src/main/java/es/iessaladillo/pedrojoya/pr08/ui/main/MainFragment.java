package es.iessaladillo.pedrojoya.pr08.ui.main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import es.iessaladillo.pedrojoya.pr08.R;
import es.iessaladillo.pedrojoya.pr08.ui.Detail.DetailFragment;
import es.iessaladillo.pedrojoya.pr08.ui.settings.SettingsFragment;
import es.iessaladillo.pedrojoya.pr08.ui.utils.FragmentUtils;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainFragment extends Fragment {

    private SharedPreferences.OnSharedPreferenceChangeListener onSharePreferencesChangeListener;
    private SharedPreferences settings;
    private TextView lblLorem;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViews(requireView());
        onSharePreferencesChangeListener = (sharedPreferences, key) -> changeLorem();
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        changeLorem();
    }

    private void changeLorem() {
        String text;

        if (TextUtils.equals(settings.getString(getString(R.string.prefLoremType_key),
                getString(R.string.prefLoremType_defaultValue)),
                getString(R.string.prefLoremType_defaultValue))) {
            text = getString(R.string.main_latin_ipsum);
        } else {
            text = getString(R.string.main_chiquito_ipsum);
        }

        lblLorem.setText(text);
    }

    private void setupViews(View view) {
        setupToolbar(view);
        FloatingActionButton fab = ViewCompat.requireViewById(view, R.id.fab);
        lblLorem = ViewCompat.requireViewById(view, R.id.lblLorem);
        fab.setOnClickListener(v -> openDetail());
    }

    private void openDetail() {
        FragmentUtils.replaceFragmentAddToBackstack(requireFragmentManager(), R.id.flContent,
                DetailFragment.newInstance(), DetailFragment.class.getSimpleName(),
                DetailFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSettings) {
            openSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
        FragmentUtils.replaceFragmentAddToBackstack(requireFragmentManager(), R.id.flContent,
                SettingsFragment.newInstance(), SettingsFragment.class.getSimpleName(),
                SettingsFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(R.string.mainFragName);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

    }

}

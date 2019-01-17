package es.iessaladillo.pedrojoya.pr08.ui.settings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import es.iessaladillo.pedrojoya.pr08.R;


public class SettingsFragment extends PreferenceFragmentCompat {

    private SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().
                registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().
                unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onSharedPreferenceChangeListener = this::updateSumary;
        setupToolbar(requireView());
        setupSettings();
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
            actionBar.setTitle(R.string.settingsName);
        }
    }

    private void setupSettings() {
        updateSumary(getPreferenceScreen().getSharedPreferences(),
                getString(R.string.prefLoremType_key));
    }

    private void updateSumary(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);

        if (TextUtils.equals(preference.getKey(), getString(R.string.prefLoremType_key))) {
            String[] values = getResources().getStringArray(R.array.prefLoremType_values);
            preference.setSummary(TextUtils.equals(sharedPreferences.getString(
                    getString(R.string.prefLoremType_key),
                    getString(R.string.prefLoremType_defaultValue)),
                    getString(R.string.prefLoremType_defaultValue)) ? values[0] : values[1]);
        }
    }
}

package es.iessaladillo.pedrojoya.pr08.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import es.iessaladillo.pedrojoya.pr08.R;
import es.iessaladillo.pedrojoya.pr08.ui.settings.SettingsFragment;
import es.iessaladillo.pedrojoya.pr08.ui.utils.FragmentUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.flContent,
                    MainFragment.newInstance(), MainFragment.class.getSimpleName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSettings) {
            openSettings();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSettings() {
        FragmentUtils.replaceFragmentAddToBackstack(getSupportFragmentManager(), R.id.flContent,
                SettingsFragment.newInstance(), SettingsFragment.class.getSimpleName(),
                SettingsFragment.class.getSimpleName(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

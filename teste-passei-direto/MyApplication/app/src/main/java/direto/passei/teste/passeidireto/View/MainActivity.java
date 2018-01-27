package direto.passei.teste.passeidireto.View;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.R;
import direto.passei.teste.passeidireto.Service.DataService;
import direto.passei.teste.passeidireto.Util.ActivityUtil;

@EActivity(R.layout.activity_search)
public class MainActivity extends AppCompatActivity {

    @Bean
    DataService dataService;

    @ViewById(R.id.navigation)
    android.support.design.widget.BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    switchFragment("SearchFragment");
                    return true;
                case R.id.navigation_favorites:
                    switchFragment("FavoritesFragment");
                    return true;
            }
            return false;
        }

    };

    @AfterViews
    public void afterViews() {
        BottomNavigationView navigation = (BottomNavigationView)
                findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener);
        switchFragment("SearchFragment");
    }

    public boolean isMaterialFavorite(Material material) {
        return dataService.isMaterialFavorite(material);
    }

    public void setFavoriteMaterial(Material material) {

        dataService.persistMaterial(material);
    }

    public void removeFavoriteMaterial(Material material) {
        dataService.removeFavoriteMaterial(material);
    }

    private void switchFragment(String fragment) {
        switch (fragment) {
            case "SearchFragment":
                ActivityUtil.switchFragment(
                        new SearchFragment_(),
                        R.id.content,
                        this);
                break;
            case "FavoritesFragment":
                ActivityUtil.switchFragment(
                        new FavoritesFragment_(),
                        R.id.content,
                        this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 1) {
            manager.popBackStack();
        }else
            callExitDialog(
                    getString(R.string.exit_application),
                    getString(R.string.are_you_sure_about_leaving_application));
    }


    @UiThread
    public void callExitDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                this);
        builder.setTitle(title).setMessage(message).setPositiveButton(
                R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        builder.setTitle(title).setMessage(message).setNegativeButton(
                R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.show();
    }

}

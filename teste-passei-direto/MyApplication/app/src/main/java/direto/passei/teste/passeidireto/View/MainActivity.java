package direto.passei.teste.passeidireto.View;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.R;
import direto.passei.teste.passeidireto.Service.DataService;
import direto.passei.teste.passeidireto.Util.ActivityUtil;

@EActivity(R.layout.activity_search)
public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Bean
    DataService dataService;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    switchFragment("SearchFragment");
                    mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_favorites:
                    switchFragment("FavoritesFragment");
                    mTextMessage.setText(R.string.title_favorites);
                    return true;
            }
            return false;
        }

    };

    @AfterViews
    public void afterViews(){
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment("SearchFragment");
    }

    public boolean isMaterialFavorite(Material material){
        return dataService.isMaterialFavorite(material);
    }

    public void setFavoriteMaterial(Material material){
        dataService.persistMaterial(material);
    }

    public void removeFavoriteMaterial(Material material){
        dataService.removeFavoriteMaterial(material);
    }

    private void switchFragment(String fragment){
        switch(fragment){
            case "SearchFragment":
                ActivityUtil.switchFragment(
                        new SearchFragment_()
                        ,R.id.container,this);
                break;
            case "FavoritesFragment":
                ActivityUtil.switchFragment(
                        new FavoritesFragment_()
                        ,R.id.container,this);
                break;
        }
    }

}

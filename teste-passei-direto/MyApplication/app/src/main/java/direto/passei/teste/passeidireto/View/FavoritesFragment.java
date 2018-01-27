package direto.passei.teste.passeidireto.View;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.field.DatabaseField;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.R;
import direto.passei.teste.passeidireto.Service.DataService;

/**
 * Created by lunid on 25/01/2018.
 */

@EFragment(R.layout.fragment_search)
public class FavoritesFragment extends Fragment {

    List<Material> favoriteMaterials;

    @ViewById(R.id.materialsListView)
    ListView materialsListView;

    @ViewById(R.id.searchEditText)
    EditText searchEditText;

    @ViewById(R.id.searchButton)
    Button searchButton;

    @ViewById(R.id.noContentTextView)
    TextView  noContentTextView;

    @Bean
    DataService dataService;

    @AfterViews
    public void afterViews() {
        removeSearchVisibility();
        getFavoriteMaterials();
    }

    private void removeSearchVisibility() {
        searchEditText.setVisibility(View.GONE);
        searchButton.setVisibility(View.GONE);
    }

    @Background
    public void getFavoriteMaterials() {
        favoriteMaterials = dataService.getFavoriteMaterials();
        showAdapter();
    }

    @UiThread
    void showAdapter() {
        if(favoriteMaterials.size() > 0) {
            noContentTextView.setVisibility(View.GONE);
            materialsListView.setAdapter(
                    new MaterialsAdapter(getContext(), favoriteMaterials));
        }else{
            noContentTextView.setText(getString(R.string.no_favorite));
            noContentTextView.setVisibility(View.VISIBLE);
        }
    }
}

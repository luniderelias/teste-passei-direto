package direto.passei.teste.passeidireto.View;

import android.support.v4.app.Fragment;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import direto.passei.teste.passeidireto.R;
import direto.passei.teste.passeidireto.Service.DataService;

/**
 * Created by lunid on 25/01/2018.
 */

@EFragment(R.layout.fragment_search)
public class SearchFragment extends Fragment {
    @Bean
    DataService dataService;

    @ViewById(R.id.searchEditText)
    EditText searchEditText;

    @AfterViews
    public void afterViews(){
        getMaterials();
    }

    @Background
    public void getMaterials(){
        dataService.getMaterials(searchEditText.getText().toString());
    }
}

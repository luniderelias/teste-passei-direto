package direto.passei.teste.passeidireto.View;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.R;
import direto.passei.teste.passeidireto.Service.DataService;
import direto.passei.teste.passeidireto.Util.ActivityUtil;

/**
 * Created by lunid on 25/01/2018.
 */

@EFragment(R.layout.fragment_search)
public class SearchFragment extends Fragment {
    @Bean
    DataService dataService;

    @ViewById(R.id.searchListView)
    ListView searchListView;

    @ViewById(R.id.searchEditText)
    EditText searchEditText;

    ArrayList<Material> materials;
    ProgressDialog progressDialog;

    @AfterViews
    public void afterViews(){
    }

    @Background
    public void showMaterials(){
        getMaterials();
        showAdapter();
    }

    @Click(R.id.searchButton)
    public void click(){
        showMaterials();
    }

    private void getMaterials(){
        callProgressDialog(getString(R.string.searching_materials),
                getString(R.string.please_wait));
        if (ActivityUtil.isConnectedToInternet(getActivity()))
            materials = dataService.getMaterials(searchEditText.getText().toString());
        else
            askForConnectivity();

        closeProgressDialog();
    }

    private void askForConnectivity() {
        showErrorDialog(
                getString(R.string.connection_dialog_title),
                getString(R.string.connection_dialog_message));
    }

    @UiThread
    public void showErrorDialog(String title, String message) {
        closeProgressDialog();
        ActivityUtil.showErrorDialog(getActivity(), title, message);
    }

    @UiThread
    void showAdapter() {
        if (materials.size() > 0)
            searchListView.setAdapter(new MaterialsAdapter(getContext(), materials));
    }

    @UiThread
    void callProgressDialog(String title, String message) {
        if (progressDialog == null)
            progressDialog = ActivityUtil.callProgressDialog(getActivity(), title, message);
    }

    @UiThread
    void closeProgressDialog() {
        if (progressDialog != null)
            ActivityUtil.closeProgressDialog(progressDialog);
    }
}

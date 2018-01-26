package direto.passei.teste.passeidireto.Service;

import android.content.Context;
import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;
import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import direto.passei.teste.passeidireto.Model.Material;
import direto.passei.teste.passeidireto.Rest.RetrofitRest;
import direto.passei.teste.passeidireto.Util.DatabaseHelper;
import direto.passei.teste.passeidireto.Util.RestUtil;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by lunid on 25/01/2018.
 */

@EBean
public class DataService {

    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<Material, Integer> materialDao;

    @RootContext
    protected Context mContext;

    private ArrayList<Material> foundMaterials;
    private static Integer contenttypeids = 1;
    private static Integer pagenumber = 0;
    private static Integer pagesize = 20;
    private static Integer order = 2;
    private static Boolean favorite = false;

    public synchronized ArrayList<Material> getMaterials(String query) {
        foundMaterials = new ArrayList<>();
        try {
            ArrayList foundMaterialsList =
                    (ArrayList) requestService(query).get("Results");

            for (int id = 0; id < foundMaterialsList.size(); id++)
                addFoundMaterial(id, foundMaterialsList);
            persistAllMaterialsFound();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundMaterials;
    }

    private HashMap requestService(String query) throws Exception {
        return (RestUtil.getService().getMaterials(
                query,
                contenttypeids,
                pagenumber,
                pagesize,
                order)).execute().body();
    }

    private void addFoundMaterial(int id, ArrayList foundMaterialsList) {
        LinkedTreeMap materialLinkedTreeMap = (LinkedTreeMap)
                foundMaterialsList.get(id);
        foundMaterials.add(id, new Material(
                materialLinkedTreeMap.get("Name").toString(),
                materialLinkedTreeMap.get("SubjectName").toString(),
                materialLinkedTreeMap.get("UniversityName").toString(),
                favorite));
    }

    private void persistAllMaterialsFound() {
        for (int ii = 0; ii < foundMaterials.size(); ii++)
            persistMaterial(foundMaterials.get(ii));
    }

    private void persistMaterial(Material material) {
        try {
            materialDao.createOrUpdate(material);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

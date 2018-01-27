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
import java.util.List;
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
            //persistAllMaterialsFound();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundMaterials;
    }

    public synchronized ArrayList<Material> getQueriedMaterials() {
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
        foundMaterials.add(new Material(
                ((Double) materialLinkedTreeMap.get("Id")).intValue(),
                materialLinkedTreeMap.get("Name").toString(),
                materialLinkedTreeMap.get("SubjectName").toString(),
                materialLinkedTreeMap.get("UniversityName").toString(),
                favorite));
    }

    public void persistMaterial(Material material) {
        try {
            materialDao.createOrUpdate(material);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isMaterialFavorite(Material material) {
        try {
            return materialDao.idExists(material.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeFavoriteMaterial(Material material) {
        try {
            materialDao.delete(material);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Material> getFavoriteMaterials() {
        try {
            return materialDao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

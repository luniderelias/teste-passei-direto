package direto.passei.teste.passeidireto.Rest;

import java.util.HashMap;

import direto.passei.teste.passeidireto.Model.Material;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lunid on 25/01/2018.
 */

public interface RetrofitRest {

    @GET("Search/GlobalSearch?")
    Call<HashMap> getMaterials(
            @Query("query") String search,
            @Query("contenttypeids") Integer contentType,
            @Query("pagenumber") Integer pageNumber,
            @Query("pagesize") Integer pageSize,
            @Query("order") Integer order);
}

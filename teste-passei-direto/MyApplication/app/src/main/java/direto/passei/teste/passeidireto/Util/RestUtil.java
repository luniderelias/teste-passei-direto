package direto.passei.teste.passeidireto.Util;

import direto.passei.teste.passeidireto.Rest.RetrofitRest;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lunid on 25/01/2018.
 */

public class RestUtil {

    public static RetrofitRest getService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://search-api.passeidireto.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitRest.class);
    }

}

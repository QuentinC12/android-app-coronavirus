package fr.esilv2020.moneyroom;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ListApi {
    @GET("countries?fbclid=IwAR28maY-nzfzs9eojISCleErkPA2lkR2r6WjVXRiTT1TcclYjSZKLs0Ki-w")
    Call<List<ListCountries>> getList();
}

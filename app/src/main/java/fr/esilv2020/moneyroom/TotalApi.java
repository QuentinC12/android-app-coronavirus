package fr.esilv2020.moneyroom;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TotalApi {

    @GET("all?fbclid=IwAR0K-bDi4w6KgBUK3bg2A8wwXOSXmVs8pdutt7N70y_FwchyJAJlcuwTlGo")
    Call<Total> getTotal();
}
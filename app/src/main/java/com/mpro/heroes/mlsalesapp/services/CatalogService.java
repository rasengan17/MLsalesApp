package com.mpro.heroes.mlsalesapp.services;

import com.mpro.heroes.mlsalesapp.services.response.CatalogResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by cmacias on 4/20/16.
 */
public interface CatalogService {

    String CATALOGS_ENDPOINT = "products";

    @GET(CATALOGS_ENDPOINT)
    Call<List<CatalogResponse>> getCatalogs();
}

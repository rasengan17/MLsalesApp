package com.mpro.heroes.mlsalesapp.services;

import com.mpro.heroes.mlsalesapp.services.response.CatalogResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cmacias on 4/20/16.
 */
public interface CatalogService {

    String CATALOGS_ENDPOINT = "https://mlm-api-git-betomaru.c9users.io/products";

    @GET(CATALOGS_ENDPOINT)
    Call<CatalogResponse> getCatalogs();
}

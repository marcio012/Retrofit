package br.com.marcioheleno.retrofit

// presta atenção no import
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
//    Call<List<Repos>> getRepositories()
    fun getRepositories(@Path("user") user: String): Call<List<Repository>>



}
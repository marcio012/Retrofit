package br.com.marcioheleno.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = configuraRetroFilt()

        executaRequisicao(retrofit)

    }


    private fun configuraRetroFilt(): Retrofit {

        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun executaRequisicao(retrofit: Retrofit){

        val gitHubService = retrofit.create(GitHubService::class.java)
        gitHubService.getRepositories("marcio012").enqueue(object : Callback<List<Repository>> {

            override fun onResponse(call: Call<List<Repository>>?, response: Response<List<Repository>>?) {
                Log.d("TAG", "DEUUUUUU CERTOOOOOO")

                response?.let {

                    if (it.isSuccessful) {
                        response.body()?.let {

                            it.forEach{ repository ->
                                Log.d("TAG", "Respositóriooooooo ${repository.name} foi escrito por ${repository.language}" )

                            }
                        }
                    } else {
                        Log.d("TAG", "FALHOU macho" )
                    }
                }

            }

            override fun onFailure(call: Call<List<Repository>>?, t: Throwable?) {
                Log.d("TAG", "FALHOUUUUUUU")
            }

        })
    }


}

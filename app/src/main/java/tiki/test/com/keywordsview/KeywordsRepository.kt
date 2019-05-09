package tiki.test.com.keywordsview

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class KeywordsRepository() {
    private val services: Services

    interface GetKeywordsCallback {
        fun onResponse(keywords: Array<String>?)
    }

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .build()

            val request = original.newBuilder()
                .url(url).build()
            chain.proceed(request)
        }

        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(Services.URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        services = retrofit.create(Services::class.java)
    }

    companion object {


        private var projectRepository: KeywordsRepository? = null

        val instance: KeywordsRepository
            @Synchronized get() {
                if (projectRepository == null) {
                    if (projectRepository == null) {
                        projectRepository = KeywordsRepository()
                    }
                }
                return projectRepository as KeywordsRepository
            }
    }


    fun getKeywords(callback: GetKeywordsCallback) {
        val call = services.keywordsList
        call.enqueue(object : Callback<Array<String>> {
            override fun onFailure(call: Call<Array<String>>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Array<String>>, response: Response<Array<String>>) {
                callback.onResponse(response.body())
            }
        })
    }
}

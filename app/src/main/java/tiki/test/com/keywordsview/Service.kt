package tiki.test.com.keywordsview

import retrofit2.Call
import retrofit2.http.GET

interface Services {

    @get:GET("keywords.json")
    val keywordsList: Call<Array<String>>

    companion object {
        val URL = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/"
    }
}

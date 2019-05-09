package tiki.test.com.keywordsview

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Build
import android.support.annotation.RequiresApi
import kotlin.collections.forEach as forEach1


class KeywordsModel : ViewModel() {
    var keywordsData: MutableLiveData<Array<String>>? = null


    @RequiresApi(Build.VERSION_CODES.N)
    fun fetchKeywordsData(): MutableLiveData<Array<String>> {
        if (keywordsData == null) {
            keywordsData = MutableLiveData()
        }
        KeywordsRepository.instance.getKeywords(object : KeywordsRepository.GetKeywordsCallback {
            override fun onResponse(keywords: Array<String>?) {
                keywords!!.forEachIndexed { idx, value ->
                    keywords[idx] = generateTextData(value)
                }
                keywordsData!!.value = keywords
            }
        })
        return keywordsData as MutableLiveData<Array<String>>
    }

    fun generateTextData(text: String): String {
        var formattedText = ""
        var sectionIndex = 0
        val sections = text?.split(" ")
        var firstStr = ""
        var secondStr = ""
        when {
            sections!!.size == 2 -> return sections[0] + "\n" + sections[1].trim()
            sections!!.size <= 1 -> return text
            else -> firstStr += sections[0]
        }
        for (j in 1 until sections!!.size - 1) {
            var tempText = firstStr + " " + sections[j]
            secondStr = text.replaceFirst(tempText, "")
            if (tempText.length >= secondStr.length) {
                sectionIndex = j
                break
            }
            firstStr = firstStr + " " + sections[j]
        }
        if ((firstStr + " " + sections[sectionIndex]).length < (sections[sectionIndex] + secondStr).length - 1) {
            formattedText = (firstStr + " " + sections[sectionIndex]) + "\n" + secondStr.trim()
        } else {
            formattedText = firstStr + "\n" + (sections[sectionIndex] + secondStr).trim()
        }
        return formattedText
    }

}

package vn.tamhuynh.com.demotestandroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tiki.test.com.keywordsview.R

class KeywordsAdapter(private val mKeywords: Array<String>) :
    RecyclerView.Adapter<KeywordsAdapter.KeywordsViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): KeywordsViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false)
        return KeywordsViewHolder(view)
    }

    override fun onBindViewHolder(KeywordsViewHolder: KeywordsViewHolder, position: Int) {
        val post = mKeywords[position]
        KeywordsViewHolder.setContent(post)
    }

    override fun getItemCount(): Int {
        return mKeywords.size
    }

    class KeywordsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val mTxtKeywords: TextView = view.findViewById(R.id.tvKeyword) as TextView

        fun setContent(keyword: String) {
            mTxtKeywords.text = keyword
        }
    }
}

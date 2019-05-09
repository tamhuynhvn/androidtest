package tiki.test.com.keywordsview

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import vn.tamhuynh.com.demotestandroid.KeywordsAdapter



class MainActivity : AppCompatActivity() {
    private var viewModel: KeywordsModel? = null
    private var mRecyclerView: RecyclerView? = null
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.my_recycler_view)
        mRecyclerView!!.layoutManager=(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        viewModel = ViewModelProviders.of(this).get(KeywordsModel::class.java)
        viewModel!!.fetchKeywordsData().observe(this, Observer<Array<String>> { this.displayView(it!!) })
    }

    private var lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    private fun displayView(keywordsData: Array<String>) {
        mRecyclerView!!.adapter = KeywordsAdapter(keywordsData)
        mRecyclerView!!.adapter!!.notifyDataSetChanged()
    }
}

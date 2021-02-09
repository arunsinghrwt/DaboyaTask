package com.arun.daboyatask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arun.daboyatask.Model.MainViewModel
import com.arun.daboyatask.adapter.ListAdapter
import com.arun.daboyatask.di.PbrBtn
import com.arun.daboyatask.utlis.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    val mainModel by viewModel<MainViewModel>()

    private var COUNT : Int = 0
    private lateinit var listviewadapter : ListAdapter
    val pbr by inject<PbrBtn>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        COUNT = 0
        initView()
        setAdapter()
        observer()

    }

    private fun initView() {
        pbr.init(pbrView)
        mainModel.getList("" + COUNT)
    }

    private fun observer() {
        mainModel.mediatorList.observe(this , {
            if (!it.isNullOrEmpty()) {
                pbr.cancel()
                pbrView.visibility = View.GONE
                COUNT += 1
                listviewadapter.addList(it)
            }
        })

    }

    private fun setAdapter() {
        val llm = androidx.recyclerview.widget.LinearLayoutManager(this)
        myView.layoutManager = llm
        listviewadapter = ListAdapter(this)
        myView.adapter = listviewadapter
        myView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(llm){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                pbrView.visibility = View.VISIBLE
                pbr.start()
                Log.e("onLoadMore","page ->$page    totalItemsCount-> $totalItemsCount")
                Handler(Looper.getMainLooper()).postDelayed({
                    mainModel.getList("" + COUNT) },100)
            }
        })


    }
}
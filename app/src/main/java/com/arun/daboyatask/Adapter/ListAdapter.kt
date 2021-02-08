package com.arun.daboyatask.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arun.daboyatask.Model.ResponseModel
import com.arun.daboyatask.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_layout.view.*


/**
// Created by Arun singh rawat  on 08-02-2021.



 **/

class ListAdapter (var context : Context) : androidx.recyclerview.widget.RecyclerView.Adapter<ListAdapter.ViewHolder>() {

   var list  =  ArrayList<ResponseModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val t = list[position]
        holder.itemView.title.text = t.author
        holder.itemView.heightT.text = t.height.toString()
        holder.itemView.widthT.text = t.width.toString()
        try {
            Glide.with(context)
                .load(t.url)
                .into(holder.itemView.img)
        }catch (Ex: Exception){
            Log.e("Exception", "---->>>>$Ex")
        }











    }

    override fun getItemCount(): Int {
     return list.size
    }



    class ViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

    }

}
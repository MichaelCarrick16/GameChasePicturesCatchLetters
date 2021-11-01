package com.example.gamechasepicturescatchletters.ui.fragment.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechasepicturescatchletters.R

class ResultAdapter(private var list : MutableList<String> , private var context: Context) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    fun updateList(listUpdate : MutableList<String>){
        list = listUpdate
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_result_detail,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvResultDetail.setText(list.get(position))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        var tvResultDetail : TextView = itemview.findViewById(R.id.tv_result_detail)
    }
}
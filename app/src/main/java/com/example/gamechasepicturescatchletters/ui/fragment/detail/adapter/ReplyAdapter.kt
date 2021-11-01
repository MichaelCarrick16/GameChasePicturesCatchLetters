package com.example.gamechasepicturescatchletters.ui.fragment.detail.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamechasepicturescatchletters.R
import com.example.gamechasepicturescatchletters.ui.callback.OnActionCallback

class ReplyAdapter(private var list : MutableList<String>, private var context: Context) : RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {
    private lateinit var callBack : OnActionCallback

    fun setCallback(callback: OnActionCallback){
        this.callBack = callback
    }

    fun updateList(listUpdate : MutableList<String>){
        list = listUpdate
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_reply_detail,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.tvReplyDetail.setText(list.get(position))
        holder.llReplyDetail.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                callBack.onCallback(position)
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        var tvReplyDetail : TextView = itemview.findViewById(R.id.tv_reply_detail)
        var llReplyDetail : LinearLayout = itemview.findViewById(R.id.ll_reply_detail)
    }
}
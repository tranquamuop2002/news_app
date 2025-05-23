package com.example.news_app_mvvm.presentation.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(
    private val layoutResId: Int,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataSet = mutableListOf<T>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<T>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: List<T>) {
        val start = dataSet.size
        dataSet.addAll(data)
        notifyItemRangeInserted(start, data.size)
    }

    fun getItem(position: Int): T = dataSet[position]

    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataSet[position]
        bind(holder, item)
    }

    abstract fun bind(holder: RecyclerView.ViewHolder, item: T)
}

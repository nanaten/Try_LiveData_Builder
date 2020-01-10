package com.nanaten.try_livedata_builder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nanaten.try_livedata_builder.R
import com.nanaten.try_livedata_builder.databinding.ListItemRvBinding
import com.nanaten.try_livedata_builder.entity.Repos

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<Repos> = emptyList()

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(list[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(parent)
    }


    class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ListItemRvBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_rv,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Repos) {
            binding.item = item
        }
    }

    fun update(list: List<Repos>) {
        this.list = list
        notifyDataSetChanged()
    }
}


fun RecyclerView.bindItems(items: List<Repos>?) {

    items ?: return

    val adapter = adapter as RecyclerViewAdapter
    adapter.update(items)
}

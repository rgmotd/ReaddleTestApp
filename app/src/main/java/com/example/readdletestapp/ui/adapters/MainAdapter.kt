package com.example.readdletestapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.readdletestapp.Item
import com.example.readdletestapp.databinding.ItemGridBinding
import com.example.readdletestapp.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class MainAdapter(
    val layoutState: Boolean
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClickCallback: (Item) -> Unit = { /* no-op */ }
    var items: List<Item> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemId(position: Int): Long {
        return items[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder = when (layoutState) {
            true -> ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            false -> GridViewHolder(ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        viewHolder.itemView.setOnClickListener {
            onItemClickCallback(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is GridViewHolder -> holder.bind(item)
            is ListViewHolder -> holder.bind(item)
        }
    }

    override fun getItemCount(): Int = items.size

    class ListViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                Picasso.with(ivIcon.context).load(item.imageUrl).into(ivIcon)
                tvTitle.text = item.text
                if (item.status) ivOnline.visibility = View.VISIBLE else ivOnline.visibility = View.INVISIBLE
            }
        }
    }

    class GridViewHolder(val binding: ItemGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                Picasso.with(ivIcon.context).load(item.imageUrl).into(ivIcon)
                if (item.status) ivOnline.visibility = View.VISIBLE else ivOnline.visibility = View.INVISIBLE
            }
        }
    }
}
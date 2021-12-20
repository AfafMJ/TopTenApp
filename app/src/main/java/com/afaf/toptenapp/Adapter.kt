package com.afaf.toptenapp


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afaf.toptenapp.databinding.ItemRowBinding


class Adapter(private var apps: ArrayList<App>):
    RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val question = apps[position]
        holder.binding.apply {
            titleTv.text = question.title

        }
    }

    override fun getItemCount() = apps.size

    fun update(apps: ArrayList<App>) {
        this.apps = apps
        notifyDataSetChanged()
    }
}
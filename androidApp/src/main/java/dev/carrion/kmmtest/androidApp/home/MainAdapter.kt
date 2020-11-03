package dev.carrion.kmmtest.androidApp.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.carrion.kmmtest.androidApp.R

class MainAdapter(list: List<String>, private val callback: (Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: MutableList<String> = list.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false), callback)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainViewHolder).onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    inner class MainViewHolder(itemView: View, callback: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        private val tv: TextView = itemView.findViewById(R.id.item_tv)

        init {
            itemView.setOnClickListener {
                callback(adapterPosition)
            }
        }

        fun onBind(text: String) {
            tv.text = text
        }
    }


}
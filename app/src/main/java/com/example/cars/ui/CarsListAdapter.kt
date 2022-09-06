package com.example.cars.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cars.R
import com.example.cars.model.CarView
import kotlinx.android.synthetic.main.car_item.view.*


class CarsListAdapter(private val onClick: (CarView) -> Unit) :
    ListAdapter<CarView, CarsListAdapter.MyCarViewHolder>(CarDiffCallback) {
    class MyCarViewHolder(itemView: View, val onClick: (CarView) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var carView: CarView? = null

        init {
            itemView.setOnClickListener {
                carView?.let {
                    onClick(it)
                }
            }
        }

        fun bind(data: CarView) {
            carView = data
            itemView.tv_licensePlate.text = data.licensePlate
            itemView.tv_name.text = data.name
            Glide.with(itemView.context).load(data.carImageUrl)
                .placeholder(R.drawable.place_holder).fitCenter().into(itemView.iv_carPreview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item, parent, false)
        return MyCarViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MyCarViewHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}


object CarDiffCallback : DiffUtil.ItemCallback<CarView>() {
    override fun areItemsTheSame(oldItem: CarView, newItem: CarView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CarView, newItem: CarView): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
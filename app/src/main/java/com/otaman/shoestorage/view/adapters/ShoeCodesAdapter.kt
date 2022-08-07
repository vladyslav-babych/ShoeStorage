package com.otaman.shoestorage.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otaman.shoestorage.R
import com.otaman.shoestorage.databinding.ShoeTypeListItemBinding
import com.otaman.shoestorage.model.shoelist.ShoeTypesList

class ShoeTypesListAdapter(
    val onShoeTypeListClick: OnShoeTypeListClick,
    val onShoeTypeListDeleteClick: OnShoeTypeListDeleteClick
): RecyclerView.Adapter<ShoeTypesListAdapter.ShoeTypeListViewHolder>() {
    private val shoeTypesList = ArrayList<ShoeTypesList>()

    inner class ShoeTypeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ShoeTypeListItemBinding.bind(itemView)

        private val tvModelType = binding.tvModelType
        private val ibDeleteType = binding.ibDeleteType

        fun showShoeTypeList(position: Int) {
            tvModelType.text = shoeTypesList[position].model_type
            ibDeleteType.setOnClickListener {
                onShoeTypeListDeleteClick.onShoeTypeListDeleteClick(shoeTypesList[position])
            }
            itemView.setOnClickListener{
                onShoeTypeListClick.onShoeTypeListClick(shoeTypesList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeTypeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoe_type_list_item, parent, false)
        return ShoeTypeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoeTypeListViewHolder, position: Int) {
        holder.showShoeTypeList(position)
    }

    override fun getItemCount() = shoeTypesList.size

    fun updateShoeTypesList(newShoeTypesList: List<ShoeTypesList>) {
        this.shoeTypesList.clear()
        this.shoeTypesList.addAll(newShoeTypesList)
        notifyDataSetChanged()
    }
}

interface OnShoeTypeListClick {
    fun onShoeTypeListClick(shoeTypesList: ShoeTypesList)
}

interface OnShoeTypeListDeleteClick {
    fun onShoeTypeListDeleteClick(shoeTypesList: ShoeTypesList)
}
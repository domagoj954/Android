package com.example.carshopapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.carshopapp.CarShopRVAdapter.*
import com.example.carshopapp.R

class CarShopRVAdapter (
    var list: List<CarShopItems>,
    val carShopItemClickInterface: CarShopItemClickInterface
    ): RecyclerView.Adapter<CarShopViewHolder>() {

    inner class CarShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV=itemView.findViewById<TextView>(R.id.idTVItemName)
        val quantityTV=itemView.findViewById<TextView>(R.id.idTVQuantity)
        val rateTV=itemView.findViewById<TextView>(R.id.idTVRate)
        val amountTV=itemView.findViewById<TextView>(R.id.idTVTotalAmt)
        val deleteTV=itemView.findViewById<ImageView>(R.id.idTVDelete)
    }

    interface CarShopItemClickInterface{
        fun onItemClick(carShopItems: CarShopItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarShopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_shop_rv_item,parent,false)
        return CarShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarShopViewHolder, position: Int) {
        holder.nameTV.text=list.get(position).itemName
        holder.quantityTV.text=list.get(position).itemQuantity.toString()
        holder.rateTV.text= "€"+list.get(position).itemPrice.toString()
        val itemTotal : Int=list.get(position).itemPrice * list.get(position).itemQuantity
        holder.amountTV.text= "€"+list.get(position).itemPrice * list.get(position).itemQuantity
        holder.deleteTV.setOnClickListener{
            carShopItemClickInterface.onItemClick(list.get(position))
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}
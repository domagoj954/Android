package com.example.carshopapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carshopapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), CarShopRVAdapter.CarShopItemClickInterface {
    lateinit var itemsRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var list: List<CarShopItems>
    lateinit var carShopRVAdapter: CarShopRVAdapter
    lateinit var carShopViewModal: CarShopViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsRV=findViewById(R.id.idRVItems)
        addFAB=findViewById(R.id.idFABAdd)
        list=ArrayList<CarShopItems>()
        carShopRVAdapter= CarShopRVAdapter(list,this)
        itemsRV.layoutManager=LinearLayoutManager(this)
        itemsRV.adapter=carShopRVAdapter
        val carShopRepository=CarShopRepository(CarShopDatabase(this))
        val factory=CarShopViewModalFactory(carShopRepository)
        carShopViewModal=ViewModelProvider(this,factory).get(CarShopViewModal::class.java)
        carShopViewModal.getAllCarShopItems().observe(this, Observer {
            carShopRVAdapter.list = it
            carShopRVAdapter.notifyDataSetChanged()
        })

        addFAB.setOnClickListener {
            openDialog()
        }
    }

    fun openDialog(){
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.car_shop_add_dialog)
        val cancelBtn= dialog.findViewById<Button>(R.id.idBtnCancel)
        val addBtn= dialog.findViewById<Button>(R.id.idBtnAdd)
        val itemEdt= dialog.findViewById<EditText>(R.id.idEditItemName)
        val itemPriceEdt= dialog.findViewById<EditText>(R.id.idEditItemPrice)
        val itemQuantityEdt= dialog.findViewById<EditText>(R.id.idEditItemQuantity)
        cancelBtn.setOnClickListener{
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName : String = itemEdt.text.toString()
            val itemPrice : String = itemPriceEdt.text.toString()
            val itemQuantity : String = itemQuantityEdt.text.toString()
            val qty : Int = itemQuantity.toInt()
            val pr : Int = itemPrice.toInt()
            if(itemName.isNotEmpty() && itemPrice.isNotEmpty() && itemQuantity.isNotEmpty()){
                val items = CarShopItems(itemName,qty,pr)
                carShopViewModal.insert(items)
                Toast.makeText(applicationContext, "Item Inserted..",Toast.LENGTH_SHORT).show()
                carShopRVAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext, "Please enter all the data..",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun onItemClick(carShopItems: CarShopItems) {
        carShopViewModal.delete(carShopItems)
        carShopRVAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted..",Toast.LENGTH_SHORT).show()
    }
}
package com.example.carapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent

import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var carAdapter: CarAdapter
    val Car = ArrayList<car>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.carRv.layoutManager = LinearLayoutManager(this)


        Car.add(car("Tesla Model S Plaid", 3.0, R.drawable.n1, "Engine: Dual electric motors, Power: 1,020 hp, 0-60 mph: 1.99 seconds, Top Speed: 200 mph, Range: 396 miles"))
        Car.add(car("Ford Mustang Mach-E", 2.0, R.drawable.n2jpg, "Engine: Electric motor, Power: Up to 480 hp (in GT variant), 0-60 mph: 3.5 seconds, Top Speed: 130 mph, Up to 312 miles"))
        Car.add(car("*Chevrolet Corvette Stingray", 5.0, R.drawable.n3, "A Wonderful place in World"))
        Car.add(car("Miami Maeketta", 2.75, R.drawable.n4, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 6.5,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Porsche 911 Turbo S", 6.25,R.drawable.n6, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Mercedes-Benz S-Class", 6.5,R.drawable.n7, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Toyota Camry XSE", 6.75,R.drawable.n8, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Honda Civic Type R", 7.25,R.drawable.n9, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Jaguar F-Type R", 7.5,R.drawable.n10, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 8.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 9.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 10.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 12.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 13.55,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 14.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 15.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 16.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 17.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))
        Car.add(car("Tangalooma Island", 18.25,R.drawable.n05, "Engine: 6.2L V8, Power: 490 hp, 0-60 mph: 2.9 seconds, Top Speed: 194 mph, Range: 300 miles\n"))


        carAdapter = CarAdapter(Car)
        binding.carRv.adapter = carAdapter

        carAdapter.onClick={
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it.carName)
            intent.putExtra("price", it.carPrice)
            intent.putExtra("desc", it.carDesc)
            intent.putExtra("image", it.carImg)
            startActivity(intent)
        }

        binding.addBtn.setOnClickListener {
            showCarAddDialog()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Car.removeAt(viewHolder.adapterPosition)
                carAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.carRv)



    }

    private fun showCarAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_car_layout, null)
        val nameEt = dialogView.findViewById<EditText>(R.id.carNameEt)
        val priceEt = dialogView.findViewById<EditText>(R.id.carMpriceEt)
        val descEt = dialogView.findViewById<EditText>(R.id.CarDescEt)

        AlertDialog.Builder(this)
            .setTitle("Add Fruit")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEt.text.toString()
                val price = priceEt.text.toString().toDouble()
                val desc = descEt.text.toString()
                val img = R.drawable.n1
                Car.add(car(name, price, img, desc))
                carAdapter.notifyItemInserted(Car.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()

    }


}
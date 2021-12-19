package com.example.smarthome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_devices.*

private lateinit var database: DatabaseReference

class DevicesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("MainActivity", "onViewCreated")
        database = Firebase.database.reference

        card1.setOnClickListener {
            Log.i("MainActivity", "crd1 Clicked")
            database.child("Devices").child("Device1").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Device 1")
                val device1Stat = it.value.toString()
                if(device1Stat.toInt() == 1){ //if on turn off
                    //card1.setCardBackgroundColor(resources.getColor(R.color.yellow_off))

                    database.child("Devices").child("Device1").setValue(0)
                }
                else if (device1Stat.toInt() == 0){ //if off turn on
                    //card1.setCardBackgroundColor(resources.getColor(R.color.yellow_on))
                    database.child("Devices").child("Device1").setValue(1)
                }
                showAnimation()
/*                val loading = LoadingFragment()
                fragmentManager?.let { loading.show(it, "Loading") }*/



            }
        }

        card2.setOnClickListener {
            Log.i("MainActivity", "crd2 Clicked")
            database.child("Devices").child("Device2").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Device 2")
                val device2Stat = it.value.toString().toInt()
                if(device2Stat == 1){
                    //card2.setCardBackgroundColor(resources.getColor(R.color.yellow_off))
                    database.child("Devices").child("Device2").setValue(0)
                }
                else {
                    //card2.setCardBackgroundColor(resources.getColor(R.color.yellow_on))
                    database.child("Devices").child("Device2").setValue(1)
                }
                showAnimation()
            }
        }

        card3.setOnClickListener {
            Log.i("MainActivity", "crd3 Clicked")
            database.child("Devices").child("Device3").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Device 3")
                val device3Stat = it.value.toString().toInt()
                if(device3Stat == 1){
                    //card3.setCardBackgroundColor(resources.getColor(R.color.yellow_off))
                    database.child("Devices").child("Device3").setValue(0)
                }
                else {
                    //card3.setCardBackgroundColor(resources.getColor(R.color.yellow_on))
                    database.child("Devices").child("Device3").setValue(1)
                }
                showAnimation()
            }
        }

        card4.setOnClickListener {
            Log.i("MainActivity", "crd4 Clicked")
            database.child("Devices").child("Device4").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Device 4")
                val device4Stat = it.value.toString().toInt()
                if(device4Stat == 1){
                    //card4.setCardBackgroundColor(resources.getColor(R.color.yellow_off))
                    database.child("Devices").child("Device4").setValue(0)
                }
                else {
                    //card4.setCardBackgroundColor(resources.getColor(R.color.yellow_on))
                    database.child("Devices").child("Device4").setValue(1)
                }
                showAnimation()
            }
        }
    }

    private fun showAnimation(){
        val loading = LoadingFragment()
        fragmentManager?.let { loading.show(it, "Loading") }
    }


}
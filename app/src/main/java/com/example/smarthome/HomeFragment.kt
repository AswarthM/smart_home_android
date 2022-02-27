package com.example.smarthome

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*

private lateinit var database: DatabaseReference

lateinit var mContext: Context

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("MainActivity", "onViewCreated")

        database = Firebase.database.reference

        updateDoor()
        updateIntruder()
        updateSmoke()
        updateLock()
        updateTemp()

        card_lock.setOnClickListener{
            database.child("lock").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Lock")
                val lockStat = it.value.toString()
                if(lockStat.toInt() == 1){ //if unlocked, lock it
                    database.child("lock").setValue(0)
                    tv_lock.text = "Unlock Door"
                    iv_lock.setImageDrawable(resources.getDrawable(R.drawable.unlock_solid))
                }
                else if (lockStat.toInt() == 0){ //if locked, unlock it
                    database.child("lock").setValue(1)
                    tv_lock.text = "Lock Door"
                    iv_lock.setImageDrawable(resources.getDrawable(R.drawable.ic_lock_solid))
                }
                showAnimation()
            }
        }

/*
        card1.setOnClickListener {
            Log.i("MainActivity", "crd1 Clicked")
            database.child("Devices").child("Device1").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Device 1")
                val device1Stat = it.value.toString()
                if(device1Stat.toInt() == 1){ //if on turn off
                    database.child("Devices").child("Device1").setValue(0)
                }
                else if (device1Stat.toInt() == 0){ //if off turn on
                    database.child("Devices").child("Device1").setValue(1)
                }
                showAnimation()
            }
        }

        card2.setOnClickListener {
            Log.i("MainActivity", "crd2 Clicked")
            database.child("Devices").child("Device2").get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value} for Device 2")
                val device2Stat = it.value.toString().toInt()
                if(device2Stat == 1){
                    database.child("Devices").child("Device2").setValue(0)
                }
                else {
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
                    database.child("Devices").child("Device3").setValue(0)
                }
                else {
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
                    database.child("Devices").child("Device4").setValue(0)
                }
                else {
                    database.child("Devices").child("Device4").setValue(1)
                }
                showAnimation()
            }
        }*/

    }

    private fun updateDoor(){

        database.child("door").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(tv_door_stat != null){
                    val text = dataSnapshot.value.toString().toInt()
                    if(text == 1){
                        tv_door_stat.setTextColor(resources.getColor(R.color.text_red))
                        tv_door_stat.text = mContext.resources.getString(R.string.door_open)
                    }else{
                        tv_door_stat.setTextColor(resources.getColor(R.color.text_green))
                        tv_door_stat.text = mContext.resources.getString(R.string.door_closed)
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun updateIntruder(){

        database.child("intruder").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(tv_intruder != null){
                    val text = dataSnapshot.value.toString().toInt()
                    if(text == 1){
                        tv_intruder.setTextColor(resources.getColor(R.color.text_red))
                        tv_intruder.text = mContext.resources.getString(R.string.intruders_detected)
                    }else{
                        tv_intruder.setTextColor(resources.getColor(R.color.text_green))
                        tv_intruder.text = mContext.resources.getString(R.string.no_intruders_detected)
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun updateSmoke(){

        database.child("mq2").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(tv_smoke_stat != null){
                    val text = dataSnapshot.value.toString().toInt()
                    if(text == 1){
                        tv_smoke_stat.setTextColor(resources.getColor(R.color.text_red))
                        tv_smoke_stat.text = mContext.resources.getString(R.string.smoke_detected)
                    }else{
                        tv_smoke_stat.setTextColor(resources.getColor(R.color.text_green))
                        tv_smoke_stat.text = mContext.resources.getString(R.string.no_smoke_detected)
                    }

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private  fun updateLock(){
        database.child("lock").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value} for Lock")
            val lockStat = it.value.toString()
            if(lockStat.toInt() == 1){
                tv_lock.text = "Lock Door"
                iv_lock.setImageDrawable(resources.getDrawable(R.drawable.ic_lock_solid))
            }
            else if (lockStat.toInt() == 0){
                tv_lock.text = "Unlock Door"
                iv_lock.setImageDrawable(resources.getDrawable(R.drawable.unlock_solid))
            }
        }
    }

    private fun updateTemp(){
        Toast.makeText(getActivity(), "hlo", Toast.LENGTH_SHORT).show()

        database.child("temp").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val text = dataSnapshot.value.toString().toFloat()
                val temp = text.toInt().toString()
                tv_temp_stat.text = "$temp °C"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun showAnimation(){
        val loading = LoadingFragment()
        fragmentManager?.let { loading.show(it, "Loading") }
    }

}
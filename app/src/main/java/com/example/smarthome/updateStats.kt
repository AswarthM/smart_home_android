package com.example.smarthome

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class updateDoor : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        Toast.makeText(applicationContext, "Start", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val runnable = Runnable {
            update()
        }
        val thread = Thread(runnable)
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    fun update(){
        val database = Firebase.database.reference

        database.child("door").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value.toString().equals("1")){
                    Notifications().Notify(applicationContext, "Door Opened!!", 10)
                    //Toast.makeText(applicationContext, dataSnapshot.value.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {  }

        })

        database.child("mq2").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value.toString().equals("1")){
                    Notifications().Notify(applicationContext, "Smoke Detected!!", 11)
                    //Toast.makeText(applicationContext, dataSnapshot.value.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {  }

        })

        database.child("intruder").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.value.toString().equals("1")){
                    Notifications().Notify(applicationContext, "Intruder Detected, Stay Alert!!", 12)
                    Toast.makeText(applicationContext, dataSnapshot.value.toString(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {  }

        })

    }
}
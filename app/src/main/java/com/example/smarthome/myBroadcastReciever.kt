package com.example.smarthome

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


private lateinit var database: DatabaseReference

class myBroadcastReceiver:BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action.equals("com.tester.alarmmanager")){
            database = Firebase.database.reference
            val b = intent.extras
            val deviceA = b!!.getInt("message")
            val device = "Device$deviceA"
            val notifyMe = Notifications()
            notifyMe.Notify(context!!, device!!, deviceA)
            database.child("Devices").child(device!!).get().addOnSuccessListener {
                val deviceStat = it.value.toString().toInt()
                if(deviceStat == 1){ //if on turn off
                    database.child("Devices").child(device!!).setValue(0)
                }
                else if (deviceStat == 0){ //if off turn on
                    database.child("Devices").child(device!!).setValue(1)
                }
            }

           // Toast.makeText(context, device, Toast.LENGTH_LONG).show()
        }
        else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){
            val saveData = SaveData(context!!)
            saveData.setAlarm(1, ScheduleFragment().getFromSharedPrefs("repeat1"))
            saveData.setAlarm(4 + 1, ScheduleFragment().getFromSharedPrefs("repeat1"))
        }
    }
}
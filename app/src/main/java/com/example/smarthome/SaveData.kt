package com.example.smarthome


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.*

class SaveData {

    var context: Context?=null
    var sharedPrefs: SharedPreferences?=null
    constructor(context: Context){
        this.context=context
        this.sharedPrefs = context.getSharedPreferences("myRef", Context.MODE_PRIVATE)
    }

    fun saveData(hour: Int, minute: Int, btn:Int){
        var editor = sharedPrefs!!.edit()
        editor.putInt("hour$btn", hour)
        editor.putInt("minute$btn", minute)
        editor.commit()
    }

    fun clearData(btn:Int){
        var editor = sharedPrefs!!.edit()
        editor.putInt("hour$btn", 0)
        editor.putInt("minute$btn", 0)
        editor.commit()
    }

    fun getHour(btn:Int):Int{
        return sharedPrefs!!.getInt("hour$btn", 0)
    }

    fun getMinute(btn: Int):Int{
        return sharedPrefs!!.getInt("minute$btn", 0)
    }

    fun setAlarm(btn: Int, repeat: Int){
        val hour = getHour(btn)
        val minute = getMinute(btn)
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            val am = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context,myBroadcastReceiver::class.java)
            var btn2 = 0
            if(btn > 4){
                btn2 = btn - 4
            }else{
                btn2 = btn
            }
            intent.putExtra("message", btn2)
            intent.action = "com.tester.alarmmanager"

            val pi = PendingIntent.getBroadcast(context, btn, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if(repeat == 1){
                am.setRepeating(AlarmManager.RTC_WAKEUP, 5000, calendar.timeInMillis, pi)
                //Toast.makeText(context, "Runs Everyday", Toast.LENGTH_SHORT).show()
            }else{
                am.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pi)
                //Toast.makeText(context, "Runs Once", Toast.LENGTH_SHORT).show()
            }

    }

    fun cancelAlarm(btn: Int) {
        val hour = getHour(btn)
        val minute = getMinute(btn)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val am = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context,myBroadcastReceiver::class.java)
        intent.putExtra("message", "Alarm$btn")
        intent.action = "com.tester.alarmmanager"

        val pi = PendingIntent.getBroadcast(context, btn, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        am.cancel(pi)

        clearData(btn)
        //Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()

    }


}
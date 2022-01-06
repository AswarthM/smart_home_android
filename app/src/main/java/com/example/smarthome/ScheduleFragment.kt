package com.example.smarthome

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.fragment_schedule.*

//////////TODO
/////////DONE add door unlock, door status, gas and movement notification, add cancel 4 +x alarm
var repeat1 = 0
var repeat2 = 0
var repeat3 = 0
var repeat4 = 0

class ScheduleFragment : Fragment() {

    val repeatMessage = "Repeats Everyday"
    val onceMessage = "Runs Once"
    val cancelMessage = "Cancelled"

    var btn = 0
    private var mContext: Context? = null
    var sharedPrefs: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        sharedPrefs = mContext!!.getSharedPreferences("myRef", Context.MODE_PRIVATE)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val saveData = SaveData(mContext!!)
        val notifyMe = Notifications()

        //<editor-fold desc="Device1...">
        repeat1 = getFromSharedPrefs("repeat1")
        sw_run1.isChecked = repeat1 == 1
        if(getFromSharedPrefs("alarm1") == 1){
            //make timepickers visible
            switch1.isChecked = true
            tvShowTime11.isVisible = true
            tvShowTime12.isVisible = true
            sw_run1.isVisible = true
            tvon1.isVisible = true
            tvoff1.isVisible = true
        }
        else {
            //make timepickers invisible
            switch1.isChecked = false
            tvShowTime11.isVisible = false
            tvShowTime12.isVisible = false
            sw_run1.isVisible = false
            tvon1.isVisible = false
            tvoff1.isVisible = false
        }

        tvShowTime11.text = ("${saveData.getHour(1)}:${saveData.getMinute(1)}")
        tvShowTime12.text = ("${saveData.getHour(4 + 1)}:${saveData.getMinute(4 + 1)}")

        tvShowTime11.setOnClickListener {
            btn = 1
            popTime(btn, repeat1)
        }
        tvShowTime12.setOnClickListener {
            btn = 4 + 1
            popTime(btn, repeat1)
        }

        switch1.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                //make timepickers visible
                tvShowTime11.isVisible = true
                tvShowTime12.isVisible = true
                sw_run1.isVisible = true
                tvon1.isVisible = true
                tvoff1.isVisible = true
                saveToSharedPrefs("alarm1", 1)

            } else {
                //make timepickers invisible
                tvShowTime11.isVisible = false
                tvShowTime12.isVisible = false
                sw_run1.isVisible = false
                tvon1.isVisible = false
                tvoff1.isVisible = false
                saveToSharedPrefs("alarm1", 0)

                saveData.cancelAlarm(1)
                saveData.cancelAlarm(4 + 1)
                showToast(cancelMessage)
            }
        }


        sw_run1.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                repeat1 = 1
                showToast(repeatMessage)
            } else {
                repeat1 = 0
                showToast(onceMessage)
            }
            SaveData(mContext!!).setAlarm(1, repeat1)
            SaveData(mContext!!).setAlarm(4 + 1, repeat1)
            saveToSharedPrefs("repeat1", repeat1)
        }

//</editor-fold>

        //<editor-fold desc="Device2...">
        sw_run2.isChecked = getFromSharedPrefs("repeat2") == 1
        if(getFromSharedPrefs("alarm2") == 1){
            //make timepickers visible
            switch2.isChecked = true
            tvShowTime21.isVisible = true
            tvShowTime22.isVisible = true
            sw_run2.isVisible = true
            tvon2.isVisible = true
            tvoff2.isVisible = true
        }
        else {
            //make timepickers invisible
            switch2.isChecked = false
            tvShowTime21.isVisible = false
            tvShowTime22.isVisible = false
            sw_run2.isVisible = false
            tvon2.isVisible = false
            tvoff2.isVisible = false
        }

        tvShowTime21.text = ("${saveData.getHour(2)}:${saveData.getMinute(2)}")
        tvShowTime22.text = ("${saveData.getHour(4 + 2)}:${saveData.getMinute(4 + 2)}")

        tvShowTime21.setOnClickListener {
            btn = 2
            popTime(btn, repeat2)
        }
        tvShowTime22.setOnClickListener {
            btn = 4 + 2
            popTime(btn, repeat2)
        }

        switch2.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                //make timepickers visible
                tvShowTime21.isVisible = true
                tvShowTime22.isVisible = true
                sw_run2.isVisible = true
                tvon2.isVisible = true
                tvoff2.isVisible = true
                saveToSharedPrefs("alarm2", 1)

            } else {
                //make timepickers invisible
                tvShowTime21.isVisible = false
                tvShowTime22.isVisible = false
                sw_run2.isVisible = false
                tvon2.isVisible = false
                tvoff2.isVisible = false
                saveToSharedPrefs("alarm2", 0)

                saveData.cancelAlarm(2)
                saveData.cancelAlarm(4 + 2)
                showToast(cancelMessage)
            }
        }


        sw_run2.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                repeat2 = 1
                showToast(repeatMessage)
            } else {
                repeat2 = 0
                showToast(onceMessage)
            }
            SaveData(mContext!!).setAlarm(2, repeat2)
            SaveData(mContext!!).setAlarm(4 + 2, repeat2)
            saveToSharedPrefs("repeat2", repeat2)
        }

//</editor-fold>

        //<editor-fold desc="Device3...">
        sw_run3.isChecked = getFromSharedPrefs("repeat3") == 1
        if(getFromSharedPrefs("alarm3") == 1){
            //make timepickers visible
            switch3.isChecked = true
            tvShowTime31.isVisible = true
            tvShowTime32.isVisible = true
            sw_run3.isVisible = true
            tvon3.isVisible = true
            tvoff3.isVisible = true
        }
        else {
            //make timepickers invisible
            switch3.isChecked = false
            tvShowTime31.isVisible = false
            tvShowTime32.isVisible = false
            sw_run3.isVisible = false
            tvon3.isVisible = false
            tvoff3.isVisible = false
        }

        tvShowTime31.text = ("${saveData.getHour(3)}:${saveData.getMinute(3)}")
        tvShowTime32.text = ("${saveData.getHour(4 + 3)}:${saveData.getMinute(4 + 3)}")

        tvShowTime31.setOnClickListener {
            btn = 3
            popTime(btn, repeat3)
        }
        tvShowTime32.setOnClickListener {
            btn = 4 + 3
            popTime(btn, repeat3)
        }

        switch3.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                //make timepickers visible
                tvShowTime31.isVisible = true
                tvShowTime32.isVisible = true
                sw_run3.isVisible = true
                tvon3.isVisible = true
                tvoff3.isVisible = true
                saveToSharedPrefs("alarm3", 3)

            } else {
                //make timepickers invisible
                tvShowTime31.isVisible = false
                tvShowTime32.isVisible = false
                sw_run3.isVisible = false
                tvon3.isVisible = false
                tvoff3.isVisible = false
                saveToSharedPrefs("alarm3", 0)

                saveData.cancelAlarm(3)
                saveData.cancelAlarm(4 + 3)
                showToast(cancelMessage)
            }
        }


        sw_run3.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                repeat3 = 1
                showToast(repeatMessage)
            } else {
                repeat3 = 0
                showToast(onceMessage)
            }
            SaveData(mContext!!).setAlarm(3, repeat3)
            SaveData(mContext!!).setAlarm(4 + 3, repeat3)
            saveToSharedPrefs("repeat3", repeat3)
        }

//</editor-fold>

        //<editor-fold desc="Device4...">
        sw_run4.isChecked = getFromSharedPrefs("repeat4") == 1
        if(getFromSharedPrefs("alarm4") == 1){
            //make timepickers visible
            switch4.isChecked = true
            tvShowTime41.isVisible = true
            tvShowTime42.isVisible = true
            sw_run4.isVisible = true
            tvon4.isVisible = true
            tvoff4.isVisible = true
        }
        else {
            //make timepickers invisible
            switch4.isChecked = false
            tvShowTime41.isVisible = false
            tvShowTime42.isVisible = false
            sw_run4.isVisible = false
            tvon4.isVisible = false
            tvoff4.isVisible = false
        }

        tvShowTime41.text = ("${saveData.getHour(4)}:${saveData.getMinute(4)}")
        tvShowTime42.text = ("${saveData.getHour(4 + 4)}:${saveData.getMinute(4 + 4)}")

        tvShowTime41.setOnClickListener {
            btn = 4
            popTime(btn, repeat4)
        }
        tvShowTime42.setOnClickListener {
            btn = 4 + 4
            popTime(btn, repeat4)
        }

        switch4.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                //make timepickers visible
                tvShowTime41.isVisible = true
                tvShowTime42.isVisible = true
                sw_run4.isVisible = true
                tvon4.isVisible = true
                tvoff4.isVisible = true
                saveToSharedPrefs("alarm4", 1)

            } else {
                //make timepickers invisible
                tvShowTime41.isVisible = false
                tvShowTime42.isVisible = false
                sw_run4.isVisible = false
                tvon4.isVisible = false
                tvoff4.isVisible = false
                saveToSharedPrefs("alarm4", 0)

                saveData.cancelAlarm(4)
                saveData.cancelAlarm(4 + 4)
                showToast(cancelMessage)
            }
        }


        sw_run4.setOnCheckedChangeListener { buttonView,
                                             isChecked ->
            if (isChecked) {
                repeat4 = 1
                showToast(repeatMessage)
            } else {
                repeat4 = 0
                showToast(onceMessage)
            }
            SaveData(mContext!!).setAlarm(4, repeat4)
            SaveData(mContext!!).setAlarm(4 + 4, repeat4)
            saveToSharedPrefs("repeat4", repeat4)
        }

//</editor-fold>

    }

    private fun setTime(hour: Int, minute: Int, repeat: Int, btn: Int) {

        when(btn){
            1-> tvShowTime11.text = "$hour:$minute"
            2-> tvShowTime21.text = "$hour:$minute"
            3-> tvShowTime31.text = "$hour:$minute"
            4-> tvShowTime41.text = "$hour:$minute"
            5-> tvShowTime12.text = "$hour:$minute"
            6-> tvShowTime22.text = "$hour:$minute"
            7-> tvShowTime32.text = "$hour:$minute"
            8-> tvShowTime42.text = "$hour:$minute"
        }

        val saveData = SaveData(mContext!!)
        saveData.saveData(hour, minute, btn)
        saveData.setAlarm(btn, repeat)
    }

    private fun popTime(btn: Int, repeat: Int) {
        val saveData = SaveData(mContext!!)
        // instance of MDC time picker
        val materialTimePicker: MaterialTimePicker = MaterialTimePicker.Builder()
            // set the title for the alert dialog
            .setTitleText("Select Time")
            // set the default hour for the
            // dialog when the dialog opens
            .setHour(saveData.getHour(btn))
            // set the default minute for the
            // dialog when the dialog opens
            .setMinute(saveData.getMinute(btn))
            // set the time format
            // according to the region
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()
        materialTimePicker.show(parentFragmentManager, "MainActivity")
        materialTimePicker.addOnPositiveButtonClickListener {
            val hour = materialTimePicker.hour
            val minute = materialTimePicker.minute
            setTime(hour, minute, repeat, btn)
        }
        materialTimePicker.addOnCancelListener {
            switch1.isChecked = false
        }
        materialTimePicker.addOnNegativeButtonClickListener {
            switch1.isChecked = false
        }
    }

    private fun saveToSharedPrefs(key: String, value: Int){
        val editor = sharedPrefs!!.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun getFromSharedPrefs(key:String):Int{
        return sharedPrefs!!.getInt(key, 0)
    }

    fun showToast(message: String){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
    }

}
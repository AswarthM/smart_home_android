package com.example.smarthome

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var pageNamesArray: Array<String>
    private val CODE_AUTHENTICATION_VERIFICATION = 241

    var pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //Toast.makeText(this@MainActivity, "Selected position: $position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

            val intent = Intent(this, updateDoor::class.java)
            startService(intent)

            Log.i("MainActivity", "onCreate")
            //showAlertDialog()
            authenticate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == CODE_AUTHENTICATION_VERIFICATION){
            Toast.makeText(applicationContext, "Authentication Successful", Toast.LENGTH_SHORT).show()

            pageNamesArray = resources.getStringArray(R.array.page_names)

            val pagesAdapter = PageAdapter(this, pageNamesArray.size)
            viewPager.adapter = pagesAdapter

            //TODO:5 Register page change callback here
            viewPager.registerOnPageChangeCallback(pageChangeCallback)

            //TODO:7 Change ViewPager2 orientation here
            //viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

            //TODO:10 Connect TabLayout and ViewPager2 here
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                //To get the first name of doppelganger celebrities
                tab.text = pageNamesArray[position].split(" ")[0]
            }.attach()

        }
        else{
            showAlertDialog()
        }
    }

    fun authenticate(){
        val km = applicationContext.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        if(km.isDeviceSecure){
            val intent = km.createConfirmDeviceCredentialIntent("Authentication Required", " ")
            startActivityForResult(intent, CODE_AUTHENTICATION_VERIFICATION)
        }else{
            Toast.makeText(applicationContext, "No Security Setup", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        alertDialog.setTitle("Authentication Failure")
        alertDialog.setMessage("Please authenticate using your Biometrics or Screen Lock")
        alertDialog.setPositiveButton(
            "Ok"
        ) { _, _ ->
            authenticate()
        }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}


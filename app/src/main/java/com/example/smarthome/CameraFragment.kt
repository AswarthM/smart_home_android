package com.example.smarthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_camera.*


class CameraFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //webView.loadUrl("http://192.168.1.14:8081")

        webView.loadDataWithBaseURL(null, "<style>:root{ background-color: #E9E9E9;}img{border-radius: 10px; display: inline;height: auto;max-width: 100%;}</style> <img src = 192.168.1.14:8081>"  , "text/html", "UTF-8", null);

    }


}
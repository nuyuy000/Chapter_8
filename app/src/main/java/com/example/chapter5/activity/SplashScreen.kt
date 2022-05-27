package com.example.chapter5.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chapter5.R
import com.example.chapter5.activity.MainActivity.Companion.SHARED_FILE
import com.example.chapter5.databinding.FragmentListFilmBinding

class SplashScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreference = context?.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE)

        val userShared = sharedPreference?.getString("islogin", "")

        Handler(Looper.getMainLooper()).postDelayed({
            if (userShared == "") {
                val action =SplashScreenDirections.actionSplashScreenToLoginFragment()
                findNavController().navigate(action)
            } else {
                val action = SplashScreenDirections.actionSplashScreenToListFilm()
                findNavController().navigate(action)
            }
        }, 2000)
    }

}
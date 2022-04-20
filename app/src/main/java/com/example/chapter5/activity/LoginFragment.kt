package com.example.chapter5.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chapter5.R
import com.example.chapter5.databases.AkunRepository
import com.example.chapter5.databinding.FragmentLoginFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//import com.example.chapter5.model.AkunRepository

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var akunRepository: AkunRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        akunRepository = AkunRepository(requireContext())
        binding.btnLogin.setOnClickListener {
            if (binding.tvPassword.text.isEmpty() || binding.tvUsername.text.isEmpty()) {
                Toast.makeText(requireContext(), "Kolom Masih Kosong", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch(Dispatchers.IO) {
                    val regis = akunRepository.login(
                        binding.tvUsername.text.toString(),
                        binding.tvPassword.text.toString()
                    )
                    runBlocking(Dispatchers.Main) {
                        if (regis != null) {
                            Toast.makeText(requireContext(), "Berhasil Login", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "Login Gagal, periksa kembali username dan password anda", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }

        binding.tvDaftar.setOnClickListener {
            it.findNavController().navigate(R.id.action_login_fragment_to_regis_fragment)
        }
    }
}






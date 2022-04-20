package com.example.chapter5.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.chapter5.R
import com.example.chapter5.databases.Akun
import com.example.chapter5.databases.AkunRepository
import com.example.chapter5.databinding.FragmentRegisFragmentBinding
//import com.example.chapter5.model.AkunRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class regis_fragment : Fragment() {
    private var _binding: FragmentRegisFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var akunRepository: AkunRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        akunRepository = AkunRepository(requireContext())
        binding.btnRegis.setOnClickListener{
            if (binding.etUsername.text.toString().isEmpty() || binding.etPassword.text.isEmpty() || binding.etEmail.text.isEmpty()) {
                Toast.makeText(requireContext(), "Form Tidak Boleh Kosong ", Toast.LENGTH_SHORT).show()
            }
            else if (binding.etPassword.text.toString() != binding.etEmail.text.toString()){
                Toast.makeText(requireContext(),      "Password Harus Sama", Toast.LENGTH_SHORT).show()
            }
            else {
                val akun = Akun (null,binding.etUsername.text.toString(), binding.etPassword.text.toString())
                lifecycleScope.launch(Dispatchers.IO){
                    val regis = akunRepository.addUser(akun)
                    runBlocking(Dispatchers.Main) {
                        if (regis!=0.toLong()){
                            Toast.makeText(requireContext(), "Berhasil Registrasi", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_login_fragment_to_regis_fragment)
                        }
                        else {
                            Toast.makeText(requireContext(), "Gagal Registrasi", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            }
        }
    }

}
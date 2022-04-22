package com.example.chapter5.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chapter5.R
import com.example.chapter5.databases.AkunRepository
import com.example.chapter5.databinding.FragmentUpdateProfileBinding

class UpdateProfile : Fragment() {
    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding get() = _binding!!
    lateinit var authRepository: AkunRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener{
            findNavController().navigate(R.id.action_updateProfile_to_login_fragment)
        }
//        authRepository = AkunRepository(requireContext())
//        val sharedPreference = context?.getSharedPreferences("SHARED_FILE", Context.MODE_PRIVATE)
//        val email = sharedPreference?.getString("email","")
//        var userid : Int? = -1
//        //untuk mengambil user berdasarkan email
//        lifecycleScope.launch(Dispatchers.IO){
//            val user = authRepository.getuser(email.toString())
//            activity?.runOnUiThread {
//                binding.apply {
//                    etNama.setText(user?.username)
//                    etEmial.isEnabled = false
//                    etPassword.setText(user?.password)
//                }
//            }
//            userid = user?.id
//        }
//        //fungsi updatenya
//        binding.btnUpdate.setOnClickListener{
//            val username = binding.etNama.text.toString()
//            val ubahpass = binding.etPassword.text.toString()
////            val updateuser = Akun(userid,username,email.toString(), ubahpass)
//            lifecycleScope.launch(Dispatchers.IO){
//                val newuser = authRepository.update(updateuser)
//                activity?.runOnUiThread {
//                    if (newuser != 0){
//                        Toast.makeText(context, "Update Sukses", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
    }
}
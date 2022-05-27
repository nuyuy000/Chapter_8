package com.example.chapter5.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.chapter5.R
import com.example.chapter5.databases.AkunRepository
import com.example.chapter5.databinding.FragmentUpdateProfileBinding
import com.example.chapter5.datastore.PermissionUtils
import com.example.chapter5.datastore.StorageUtils
import java.util.*

class UpdateProfile : Fragment() {
    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding get() = _binding!!
    private var imageUri: Uri? = null
    private var imageSource = -1
    var iduser: Int? = -1
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

        binding.imageProfile.setOnClickListener {
            if (PermissionUtils.isPermissionsGranted(requireActivity(), getRequiredPermission()) {
                    activity?.let {
                        requestPermissionLauncher.launch(getRequiredPermission())
                        imageSource=1
                    }
                }){
                openGallery()
            }
        }
        binding.tvCamera.setOnClickListener {
            if (PermissionUtils.isPermissionsGranted(requireActivity(), getRequiredPermission()) {
                    activity?.let {
                        requestPermissionLauncher.launch(getRequiredPermission())
                        imageSource=2
                    }
                }){
                openCamera()
            }
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
    private var galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            imageUri = data?.data
            imageUri?.let { loadImage(it) }
        }
    }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bitmap = result.data?.extras?.get("data") as Bitmap
                val uri = StorageUtils.savePhotoToExternalStorage(
                    context?.contentResolver,
                    UUID.randomUUID().toString(),
                    bitmap
                )
                imageUri = uri
                uri?.let {
                    loadImage(it)
                }
            }
        }

    private fun loadImage(uri: Uri) {
        binding.imageProfile.setImageURI(uri)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun openGallery() {
        val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        galleryLauncher.launch(intentGallery)
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val granted = permissions.entries.all {
            it.value
        }
        if (granted) {
            openImage(imageSource)
        }
    }

    private fun openImage(imageSource: Int) {
        if (imageSource==1){
            openGallery()
        }else{
            openCamera()
        }
    }

    private fun getRequiredPermission(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        } else {
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        }
    }
}
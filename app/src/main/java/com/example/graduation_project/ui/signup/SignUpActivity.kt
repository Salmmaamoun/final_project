package com.example.graduation_project.ui.signup

import RegisterViewModel
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.data.remote.LoginRegiisterRetrofitInstance
import com.example.data.repo.repo.RepoImp
import com.example.domain.usecase.SignUpUseCase
import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import com.example.graduation_project.R
import com.example.graduation_project.databinding.ActivitySignUpBinding
import com.example.graduation_project.ui.Login.LoginActivity

import com.example.graduation_project.ui.MainActivity
import com.example.weather_app.ui.base.BaseActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: RegisterViewModel
    private var selectedImageUri: Uri? = null
    private var currentPhotoPath: String? = null
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001

    override val layoutActivityId: Int
        get() = R.layout.activity_sign_up
    private val takePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                // The picture was taken successfully
                selectedImageUri?.let { uri ->
                    binding.imageViewProfile.setImageURI(uri)
                    saveImageUri(selectedImageUri)
                    Log.d("img", uri.toString())
                } ?: run {
                    Toast.makeText(this, "Selected image URI is null", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Failed to take picture
                Toast.makeText(this, "Failed to take picture", Toast.LENGTH_SHORT).show()
            }
        }


    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                selectedImageUri = imageUri
                binding.imageViewProfile.setImageURI(imageUri)
                saveImageUri(selectedImageUri)
            } else {
                // Handle the case when the image selection was canceled
                Toast.makeText(this, "Image selection canceled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)


        val apiService = LoginRegiisterRetrofitInstance.getApi()
        val repository = RepoImp(apiService)
        val useCase = SignUpUseCase(repository)
        val viewModelFactory = ViewModelFactory(this, useCase)

        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        setUpViews()
        observeRegistrationResult()
        observeValidationErrors()

    }

    private fun observeRegistrationResult() {
        viewModel.registrationResult.observe(this, Observer { response ->
            if (response != null) {
                if (response.statusCode != 422) {
                    Log.d("ss", "Successful registration")
                } else {
                    Log.d("ss", "Error during registration")
                }
            } else {
                Log.d("ss", "Null response received")
            }
        })
    }

    private fun observeValidationErrors() {
        viewModel.nameError.observe(this, Observer { error ->
            error?.let {

                binding.editTextUsername.error = it
            }
        })

        viewModel.emailError.observe(this, Observer { error ->
            error?.let {

                binding.editTextEmail.error = it
            }
        })

        viewModel.passwordError.observe(this, Observer { error ->
            error?.let {

                binding.editTextPassword.error = it
            }
        })



        viewModel.phoneError.observe(this, Observer { error ->
            error?.let {
                binding.editTextPhone.error = it
            }
        })
    }

    private fun setUpViews() {
        binding.imageViewProfile.setOnClickListener {
            showImagePickerDialog()
        }
        binding.textInputLayoutPassword.setEndIconOnClickListener {
            // Toggle the input type of the EditText between textPassword and textVisiblePassword
            val currentInputType = binding.editTextPassword.inputType
            if (currentInputType == android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                // Hide the password text
                binding.editTextPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                // Show the password text
                binding.editTextPassword.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
        }

        binding.buttonSignUp.setOnClickListener {
            val name = binding.editTextUsername.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val passwordConfirmation = binding.editTextPasswordconfirm.text.toString()
            val gender = if (binding.radioButtonMale.isChecked) "male" else "female"
            val phone = binding.editTextPhone.text.toString()
            val image = viewModel.selectedImageUri.value

            // Call the registerUser function with the input values
            viewModel.registerUser(
                name,
                email,
                password,
                passwordConfirmation,
                gender,
                phone,
                image
            )


            if (!(email.isEmpty() && password.isEmpty() && name.isEmpty() && passwordConfirmation.isEmpty() && name.isEmpty() && phone.isEmpty())) {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                startActivity(intent)

            }


        }
        binding.textViewSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    private fun showImagePickerDialog() {
        val options = arrayOf<CharSequence>( "Choose from Gallery", "Cancel")
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Choose your profile picture")
        builder.setItems(options) { dialog, item ->
            when {

             /*   options[item] == "Take Photo" -> {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    val photoFile: File? = try {
                        createImageFile()
                    } catch (ex: IOException) {
                        // Handle error while creating the file
                        null
                    }
                    if (photoFile != null) {
                        val photoURI: Uri = FileProvider.getUriForFile(
                            this,
                            "com.example.graduation_project.fileprovider",
                            photoFile
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        takePictureLauncher.launch(photoURI)
                    } else {
                        Toast.makeText(this, "Failed to create image file", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
*/
                options[item] == "Choose from Gallery" -> {
                    val pickImageIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    pickImageLauncher.launch(pickImageIntent)
                }

                options[item] == "Cancel" -> {
                    dialog.dismiss()
                }
            }
        }
        builder.show()
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (storageDir != null) {
            val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
            currentPhotoPath = file.absolutePath // Set currentPhotoPath here
            return file
        } else {
            throw IOException("Failed to get external storage directory")
        }
    }

    // Handle the result of the permission request
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted, proceed with camera operation
                    launchCamera()
                } else {
                    // Permission denied, handle accordingly (e.g., show a message)
                    Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            // Handle other permission requests if needed
            else -> {
                // Handle other permissions
            }
        }
    }

    private fun checkCameraPermissionAndLaunchCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission granted, proceed with camera operation
            launchCamera()
        }
    }

    private fun launchCamera() {
        // Check if the CAMERA permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // If not granted, request the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            )
        } else {
            // If permission is already granted, proceed with launching the camera
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Handle error while creating the file
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.graduation_project.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, CAMERA_PERMISSION_REQUEST_CODE)
                }
            }
        }
    }

    fun saveImageUri(uri: Uri?) {
        val editor = sharedPreferences.edit()
        editor.putString("imageUri", uri?.toString())
        editor.apply()
    }

}

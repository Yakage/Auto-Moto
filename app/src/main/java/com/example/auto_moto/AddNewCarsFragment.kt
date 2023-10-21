package com.example.auto_moto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.auto_moto.databinding.FragmentAddNewCarsBinding
import com.example.auto_motov04.DBhelper
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class AddNewCarsFragment : Fragment() {
    private lateinit var binding: FragmentAddNewCarsBinding
    private lateinit var db: DBhelper
    private var selectedImageUri: Uri? = null  // Store the selected image URI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = DBhelper(requireContext())
        binding = FragmentAddNewCarsBinding.inflate(inflater, container, false)

        binding.ibBackArrow.setOnClickListener {
            findNavController().navigate(AddNewCarsFragmentDirections.actionAddNewCarsFragmentToMyCarsFragment())
        }

        // Handle image selection button click
        binding.btnSelectImage.setOnClickListener {
            openImagePicker()
        }

        binding.btAddCar.setOnClickListener {
            val carName = binding.etCarName.text.toString()
            val carModel = binding.etCarModel.text.toString()
            val carBrand = binding.etCarBrand.text.toString()

            if (ValidationUtils.isTextNotEmpty(carName) && ValidationUtils.isTextNotEmpty(carModel) && ValidationUtils.isTextNotEmpty(carBrand)) {
                // Check if an image is selected
                if (selectedImageUri == null) {
                    Toast.makeText(requireContext(), "Please select a car image", Toast.LENGTH_SHORT).show()
                } else {
                    // Use the saveImageToInternalStorage function to get the image as a byte array
                    val imageBytes = saveImageToInternalStorage(selectedImageUri!!)

                    if (imageBytes != null) {
                        // Display the selected image
                        binding.ivCarImage.setImageURI(selectedImageUri)

                        val IsCarSave = db.saveusercars(carName, carModel, carBrand, imageBytes)

                        if (IsCarSave) {
                            Toast.makeText(requireContext(), "$carName is Saved", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(AddNewCarsFragmentDirections.actionAddNewCarsFragmentToMyCarsFragment())
                        } else {
                            Toast.makeText(requireContext(), "Failed to save $carName", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Failed to load image", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please Fill the Required Fields", Toast.LENGTH_SHORT).show()
            }
        }

// ...


        return binding.root
    }


    // Function to open an image picker
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_IMAGE_PICKER)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data
            // Display the selected image
            binding.ivCarImage.setImageURI(selectedImageUri)
        }
    }

    companion object {
        private const val REQUEST_IMAGE_PICKER = 123

    }
    private fun saveImageToInternalStorage(imageUri: Uri): ByteArray? {
        val context = requireContext()
        val inputStream = context.contentResolver.openInputStream(imageUri)
        if (inputStream != null) {
            try {
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream) // Adjust quality as needed
                return stream.toByteArray()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }


}






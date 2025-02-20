package com.test.alorferi.ui.profile

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.test.alorferi.R
import com.test.alorferi.databinding.ActivityEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("DefaultLocale")
@AndroidEntryPoint
class EditProfileActivity : AppCompatActivity() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_profile)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val etDOB = findViewById<EditText>(R.id.etDOB)

        // Inflate the layout using View Binding
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            viewModel.userResponseFlow.collect { response ->

                // Safely access and update UI components with data
                response?.data?.attributes?.let { attributes ->
                    val fullName = "${attributes.first_name} ${attributes.surname}"
                    binding.etName.setText(fullName)

                    attributes.email?.let { email ->
                        binding.etEmail.setText(email)
                    }

                    attributes.dob?.let { dob ->
                        binding.etDOB.setText(dob)
                    }
                }
            }
        }


        etDOB.setOnClickListener {
            // Get the current date
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    // Format the selected date and set it to EditText
                    val formattedDate = String.format(
                        "%02d/%02d/%04d",
                        selectedDay,
                        selectedMonth + 1,
                        selectedYear
                    )
                    etDOB.setText(formattedDate)
                },
                year,
                month,
                day
            )
            // Show the DatePickerDialog
            datePickerDialog.show()
        }
    }
}
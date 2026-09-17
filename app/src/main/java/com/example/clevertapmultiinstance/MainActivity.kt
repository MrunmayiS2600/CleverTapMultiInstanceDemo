package com.example.clevertapmultiinstance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clevertapmultiinstance.databinding.ActivityMainBinding
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val phone = binding.etPhone.text?.toString()?.trim().orEmpty()
            if (phone.isEmpty()) {
                binding.tilPhone.error = "Enter a phone number"
                return@setOnClickListener
            }
            binding.tilPhone.error = null
            loginToBothDashboards(phone)
        }
    }

    private fun loginToBothDashboards(phone: String) {
        // Project 1: Identity = the user's real phone number.
        val project1Profile = HashMap<String, Any>()
        project1Profile["Identity"] = phone
        project1Profile["Phone"] = phone
        CleverTapMultiInstanceApp.project1Instance.onUserLogin(project1Profile)
        CleverTapMultiInstanceApp.project1Instance.pushEvent("Logged In")

        // Project 2: Identity = a random id, unrelated to the phone number.
        val randomUserId = "user_${UUID.randomUUID().toString().take(8)}"
        val project2Profile = HashMap<String, Any>()
        project2Profile["Identity"] = randomUserId
        CleverTapMultiInstanceApp.project2Instance.onUserLogin(project2Profile)
        CleverTapMultiInstanceApp.project2Instance.pushEvent("Logged In")

        binding.tvStatus.text =
            "Project 1 dashboard -> Identity: $phone\nProject 2 dashboard -> Identity: $randomUserId"
    }
}

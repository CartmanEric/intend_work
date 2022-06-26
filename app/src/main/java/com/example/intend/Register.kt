package com.example.intend

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.intend.Constance.constance
import com.example.intend.databinding.ActivityMainBinding
import com.example.intend.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intend = Intent()
        val result = intent.getStringExtra(constance.SIGN_STATE)
        if (result == constance.SIGN_UP_STATE) {
            binding.nameText.visibility = View.INVISIBLE
            binding.name2Text.visibility = View.INVISIBLE
            binding.name3Text.visibility = View.INVISIBLE
            binding.avatarButtin.visibility = View.INVISIBLE
        }
        binding.avatarButtin.setOnClickListener {
            binding.avatar.visibility = View.VISIBLE
        }
        binding.done.setOnClickListener {
            if (result == constance.SIGN_IN_STATE) {
                intend.putExtra(constance.LOGIN, binding.loginText.text.toString())
                intend.putExtra(constance.PASSWORD, binding.passwordText.text.toString())
                intend.putExtra(constance.NAME, binding.nameText.text.toString())
                intend.putExtra(constance.NAME2, binding.name2Text.text.toString())
                intend.putExtra(constance.NAME3, binding.name3Text.text.toString())
                intend.putExtra(constance.AVATAR, R.drawable.cartman)
                setResult(RESULT_OK, intend)
                finish()
            } else if (result == constance.SIGN_UP_STATE) {
                intend.putExtra(constance.LOGIN, binding.loginText.text.toString())
                intend.putExtra(constance.PASSWORD, binding.passwordText.text.toString())
                setResult(RESULT_FIRST_USER, intend)
                finish()
            }
        }
    }
}


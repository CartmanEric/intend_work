package com.example.intend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intend.Constance.constance
import com.example.intend.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var login = "empty"
    private var password = "empty"
    private var name = "empty"
    private var name2 = "empty"
    private var name3 = "empty"
    private var avatarImageId = 0
    private var launcher: ActivityResultLauncher<Intent>? = null
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_FIRST_USER) {
                val l = it.data?.getStringExtra(constance.LOGIN)
                val p = it.data?.getStringExtra(constance.PASSWORD)
                if (login == l && password == p) {
                    val text = "$name $name2 $name3"
                    binding.avatarMain.setImageResource(avatarImageId)
                    binding.textMain.text = text
                    binding.signIn.visibility = View.INVISIBLE
                    binding.avatarMain.visibility = View.VISIBLE
                    binding.signUp.text = "Выйти"
                    binding.erorMain.visibility = View.INVISIBLE
                    binding.signUp.setOnClickListener {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                } else {
                    binding.erorMain.visibility = View.VISIBLE
                    binding.signIn.visibility = View.VISIBLE
                }
            }
            if (it.resultCode == RESULT_OK) {
                login = it.data?.getStringExtra(constance.LOGIN)!!
                password = it.data?.getStringExtra(constance.PASSWORD)!!
                name = it.data?.getStringExtra(constance.NAME)!!
                name2 = it.data?.getStringExtra(constance.NAME2)!!
                name3 = it.data?.getStringExtra(constance.NAME3)!!
                avatarImageId = it.data?.getIntExtra(constance.AVATAR, 0)!!
                binding.erorMain.visibility = View.INVISIBLE
            }
        }
        binding.signIn.setOnClickListener {
            launcher?.launch(
                Intent(this, Register::class.java).putExtra(
                    constance.SIGN_STATE,
                    constance.SIGN_IN_STATE
                )
            )
        }
        binding.signUp.setOnClickListener {
            launcher?.launch(
                Intent(this, Register::class.java).putExtra(
                    constance.SIGN_STATE,
                    constance.SIGN_UP_STATE
                )
            )
        }
    }
}
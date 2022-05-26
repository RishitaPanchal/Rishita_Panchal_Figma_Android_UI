package com.example.medicsapp.webservices.httpurlconnection

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.medicsapp.databinding.ActivityApiselectorBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import com.example.medicsapp.signn.up.screen.SignUpActivity

class APISelectorActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityApiselectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiselectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            binding.btnHttp.id -> {
                APICallingType = APIServices.RetrofitOrHttpUrlConnection.HttpUrlConnection.name
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            binding.btnRetrofit.id -> {
                APICallingType = APIServices.RetrofitOrHttpUrlConnection.Retrofit.name
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }

}
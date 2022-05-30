package com.example.medicsapp.OnBoardingScreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.medicsapp.R
import com.example.medicsapp.base.setup.BaseSetupForSharedPreferences
import com.example.medicsapp.databinding.ActivityOnBoardingScreenBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
import com.example.medicsapp.webservices.httpurlconnection.APISelectorActivity
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingScreenActivity : BaseActivity(), View.OnClickListener {

    /*** Instance variables */
    private lateinit var binding: ActivityOnBoardingScreenBinding
    private var onBoardingData: ArrayList<OnBoardingData> = ArrayList()

    /*** Overridden method */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        binding.viewpager.adapter = OnBoardingAdapter(onBoardingData)
        attachIndicator()
        binding.onClick = this
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btnNext.id -> goNext()
            binding.btnSkip.id -> {
                BaseSetupForSharedPreferences.firstRunSession(this)
                startActivity(Intent(this, APISelectorActivity::class.java))
            }
        }
    }

    /*** Functions */
    private fun initData() {
        onBoardingData.add(
            OnBoardingData(
                R.drawable.start_onboarding,
                getString(R.string.onBoardingTextOne)
            )
        )
        onBoardingData.add(
            OnBoardingData(
                R.drawable.mid_onboarding,
                getString(R.string.onBoardingTextTwo)
            )
        )
        onBoardingData.add(
            OnBoardingData(
                R.drawable.end_onboarding,
                getString(R.string.onBoardingTextThree)
            )
        )
    }

    private fun attachIndicator() {
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { _, _ ->
        }.attach()
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.onBoardingImage.setImageResource(onBoardingData[position].image)
            }
        })
    }

    private fun goNext() {
        val currPos: Int = binding.viewpager.currentItem
        if ((currPos + 1) != onBoardingData.size) {
            binding.viewpager.currentItem = currPos + 1
        } else {
            BaseSetupForSharedPreferences.firstRunSession(this)
            startActivity(Intent(this, APISelectorActivity::class.java))
        }
    }

}
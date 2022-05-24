package com.example.medicsapp.OnBoardingScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.medicsapp.R
import com.example.medicsapp.databinding.ActivityOnBoardingScreenBinding
import com.example.medicsapp.sign.`in`.screen.BaseActivity
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
            binding.btnSkip.id -> gotoSignIn()
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

    private fun gotoSignIn() {
        Toast.makeText(this@OnBoardingScreenActivity, getString(R.string.skipClicked), Toast.LENGTH_LONG).show()
    }

    private fun attachIndicator() {
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
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
            Toast.makeText(
                this@OnBoardingScreenActivity,
                getString(R.string.goToSignInActivity),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
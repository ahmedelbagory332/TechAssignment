package com.example.techassignment.ui

import android.os.Bundle
import com.example.techassignment.R
import com.example.techassignment.base.BaseActivity
import com.example.techassignment.databinding.ActivityMainBinding
import com.example.techassignment.ui.currentOrders.CurrentOrdersFragment
import com.example.techassignment.ui.previousOrders.PreviousOrdersFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    lateinit var binding: ActivityMainBinding

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!


        val adapter = MyPagerAdapter(
            arrayListOf(CurrentOrdersFragment(), PreviousOrdersFragment()),
            supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (position == 0)
                tab.text = "Current Orders"
            else
                tab.text = "Previous Orders"

        }.attach()
    }


}
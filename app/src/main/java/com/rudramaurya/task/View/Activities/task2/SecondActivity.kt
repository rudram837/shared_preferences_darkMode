package com.rudramaurya.task.View.Activities.task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rudramaurya.task.Adapter.ViewPagerAdapter
import com.rudramaurya.task.View.Fragment.DataFragment
import com.rudramaurya.task.utils.PrefManager
import com.google.android.material.tabs.TabLayoutMediator
import com.rudramaurya.task.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager(this)

        val list1 = prefManager.getList("field1")

        val list2 = prefManager.getList("field2")

        val list3 = prefManager.getList("field3")

        val fragmentList = mutableListOf<Fragment>()

        val titleList = mutableListOf<String>()

        fragmentList.add(DataFragment.newInstance(list1))
        titleList.add("Tab 1")

        fragmentList.add(DataFragment.newInstance(list2))
        titleList.add("Tab 2")

        fragmentList.add(DataFragment.newInstance(list3))
        titleList.add("Tab 3")

        val adapter = ViewPagerAdapter(this, fragmentList)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->
            tab.text = titleList[position]
        }.attach()

        binding.goBackFirst.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
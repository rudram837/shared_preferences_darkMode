package com.rudramaurya.task.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rudramaurya.task.Adapter.DataAdapter
import com.rudramaurya.task.databinding.FragmentDataBinding

class DataFragment : Fragment() {

    private lateinit var binding: FragmentDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDataBinding.inflate(inflater, container, false)

        val dataList = arguments?.getStringArrayList("data") ?: arrayListOf()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = DataAdapter(dataList)

        return binding.root
    }

    companion object {
        fun newInstance(
            list: ArrayList<String>
        ): DataFragment {
            val fragment = DataFragment()
            val bundle = Bundle()
            bundle.putStringArrayList("data", list)
            fragment.arguments = bundle
            return fragment
        }
    }
}
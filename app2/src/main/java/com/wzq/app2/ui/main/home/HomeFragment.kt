package com.wzq.app2.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.wzq.app2.R
import com.wzq.app2.databinding.FragmentHomeBinding
import com.wzq.app2.ui.main.MainFragmentDirections
import com.wzq.app2.ui.main.MainViewModel
import com.wzq.app2.weidget.SimpleDecoration

class HomeFragment : Fragment(), HomeAdapter.ItemClickListener {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val listAdapter = HomeAdapter(this)
        binding.listView.addItemDecoration(SimpleDecoration())
        binding.listView.adapter = listAdapter

        viewModel.homeData.observe(viewLifecycleOwner) {
            listAdapter.submitList(it.getOrNull()?.data?.datas)
        }
    }

    override fun onItemClick(url: String) {
        val directions = MainFragmentDirections.actionMainToWeb(url)
        findNavController().navigate(directions)
    }

    override fun onLikeClick(id: Int) {
    }
}
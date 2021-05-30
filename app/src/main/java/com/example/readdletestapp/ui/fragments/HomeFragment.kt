package com.example.readdletestapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionManager
import com.example.readdletestapp.ui.adapters.MainAdapter
import com.example.readdletestapp.data.MainRepository
import com.example.readdletestapp.R
import com.example.readdletestapp.databinding.FragmentHomeBinding
import com.example.readdletestapp.ui.MainActivity
import com.example.readdletestapp.ui.viewmodels.MainViewModel
import com.example.readdletestapp.ui.viewmodels.MainViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        setupViewModel()
        setupAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            rvAdapter.items = items
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupOnClickListeners() {
        val toolbar = (requireActivity() as MainActivity).toolbar

        toolbar.setOnMenuItemClickListener { menuItem ->
            val layoutManager = binding.rvItems.layoutManager!! as GridLayoutManager
            val items = rvAdapter.items
            val onItemClickListener = rvAdapter.onItemClickCallback
            TransitionManager.endTransitions(binding.rvItems)
            rvAdapter = MainAdapter(!rvAdapter.layoutState)
            rvAdapter.setHasStableIds(true)
            rvAdapter.items = items
            rvAdapter.onItemClickCallback = onItemClickListener
            binding.rvItems.adapter = rvAdapter

            when (menuItem.itemId) {
                R.id.grid_item -> {
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.list_menu)
                    layoutManager.spanCount = 1
                    rvAdapter.notifyItemRangeChanged(0, rvAdapter.itemCount)
                    true
                }
                R.id.list_item -> {
                    toolbar.menu.clear()
                    toolbar.inflateMenu(R.menu.grid_menu)
                    layoutManager.spanCount = 3
                    toolbar.title = "Grid items"
                    rvAdapter.notifyItemRangeChanged(0, rvAdapter.itemCount)
                    true
                }
                else -> false
            }
        }
        binding.btnSimulateChanges.setOnClickListener {
            viewModel.simulateChanges()
        }
    }

    private fun setupAdapter() {
        binding.rvItems.layoutManager = GridLayoutManager(requireContext(), 1)
        rvAdapter = MainAdapter(true)
        rvAdapter.setHasStableIds(true)
        rvAdapter.onItemClickCallback = {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
        binding.rvItems.adapter = rvAdapter
    }

    private fun setupViewModel() {
        val repository = MainRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

}
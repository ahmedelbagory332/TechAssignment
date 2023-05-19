package com.example.techassignment.ui.previousOrders

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.techassignment.R
import com.example.techassignment.base.BaseFragment
import com.example.techassignment.databinding.FragmentPreviousOrdersBinding
import com.example.techassignment.ui.currentOrders.CurrentOrdersViewModel
import com.example.techassignment.ui.currentOrders.adapter.CurrentOrdersAdapter
import com.example.techassignment.util.CustomLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PreviousOrdersFragment: BaseFragment<FragmentPreviousOrdersBinding>() {

    lateinit var binding: FragmentPreviousOrdersBinding
    val viewModel: PrevoiusOrdersViewModel by viewModels()
    lateinit var previousOrdersAdapter: PreviousOrdersAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_previous_orders
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        previousOrdersAdapter = PreviousOrdersAdapter(baseActivity)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!



        setObservers()
    }

    private fun setObservers() {

        lifecycleScope.launch {
            viewModel.getOrders().collectLatest {
                previousOrdersAdapter.submitData(it)

            }
        }

        binding.prevoiusOrders.adapter = previousOrdersAdapter.withLoadStateFooter(
            footer = CustomLoadStateAdapter {
                previousOrdersAdapter.retry()
            }
        )
        lifecycleScope.launch {
            previousOrdersAdapter.loadStateFlow.collect {
                if (it.refresh is LoadState.Loading) {
                    baseActivity.showDialogLoading()
                } else {
                    baseActivity.hideDialogLoading()
                }


            }
        }


    }
}
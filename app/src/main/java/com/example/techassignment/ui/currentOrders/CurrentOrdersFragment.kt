package com.example.techassignment.ui.currentOrders


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.techassignment.R
import com.example.techassignment.base.BaseFragment
import com.example.techassignment.databinding.FragmentCurrentOrdersBinding
import com.example.techassignment.ui.currentOrders.adapter.CurrentOrdersAdapter
import com.example.techassignment.util.CustomLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CurrentOrdersFragment : BaseFragment<FragmentCurrentOrdersBinding>() {

    lateinit var binding: FragmentCurrentOrdersBinding
    val viewModel: CurrentOrdersViewModel by viewModels()
    lateinit var currentOrdersAdapter: CurrentOrdersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentOrdersAdapter = CurrentOrdersAdapter(baseActivity)

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_current_orders
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!

        setObservers()
    }

    private fun setObservers() {

        lifecycleScope.launch {
            viewModel.getOrders().collectLatest {
                currentOrdersAdapter.submitData(it)

            }
        }

        binding.currentOrders.adapter = currentOrdersAdapter.withLoadStateFooter(
            footer = CustomLoadStateAdapter {
                currentOrdersAdapter.retry()
            }
        )
        lifecycleScope.launch {
            currentOrdersAdapter.loadStateFlow.collect {
                if (it.refresh is LoadState.Loading) {
                    baseActivity.showDialogLoading()
                } else {
                    baseActivity.hideDialogLoading()
                }


            }
        }


    }


}
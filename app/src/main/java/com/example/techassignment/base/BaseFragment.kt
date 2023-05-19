package com.example.techassignment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.JsonParser

abstract class BaseFragment<T : ViewDataBinding?> : Fragment() {



    lateinit var baseActivity: BaseActivity<*>
        private set
    private var mRootView: View? = null
    var viewDataBinding: T? = null
        private set

    val jsonParser = JsonParser()

    @LayoutRes
    abstract fun getLayoutId(): Int

    protected lateinit var navController: NavController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            baseActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = viewDataBinding?.root
        return mRootView
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()
        try {
            navController = Navigation.findNavController(requireView())

        }catch (e:java.lang.Exception){

        }
    }



    fun showToast(text: String?) {
        Toast.makeText(baseActivity, text, Toast.LENGTH_SHORT).show()
    }

    fun showDialogLoading() {
        baseActivity.showDialogLoading()
    }

    fun hideDialogLoading() {
        baseActivity.hideDialogLoading()
    }

    val supportFragmentManager : FragmentManager
    get() = baseActivity.supportFragmentManager
}
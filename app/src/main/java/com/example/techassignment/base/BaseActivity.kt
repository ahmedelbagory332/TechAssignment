package com.example.techassignment.base


import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.techassignment.R
import com.example.techassignment.data.shared.DataManager
import com.example.techassignment.util.LocaleUtils


abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {



    var viewDataBinding: T? = null
        private set


    private var pd: Dialog? = null



    @LayoutRes
    abstract fun getLayoutId(): Int

    init {
        LocaleUtils.updateConfig(this)
    }


    lateinit var dataManager: DataManager


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createCustomProgressDialog()
        dataManager = (application as BaseApplication).dataManager!!


            if (dataManager.lang != null) {
                if (dataManager.lang.equals(LocaleUtils.LAN_ENGLISH)) {
                    LocaleUtils.setLocale(java.util.Locale(LocaleUtils.LAN_ENGLISH))
                    LocaleUtils.updateConfig(
                        application,
                        baseContext.resources.configuration
                    )
                } else {
                    LocaleUtils.setLocale(java.util.Locale(LocaleUtils.LAN_ARABIC))
                    LocaleUtils.updateConfig(
                        application,
                        baseContext.resources.configuration
                    )
                }
            }

        performDataBinding()

    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding?.executePendingBindings()
    }

    private fun createCustomProgressDialog() {
        this.let {
            pd = Dialog(it, R.style.DialogCustomTheme)
            pd?.setContentView(R.layout.progress_layout)
            pd?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            pd?.setCancelable(false)
        }
    }

    fun showDialogLoading() {
        pd?.let {
            if (!it.isShowing)
                it.show()
        }
    }

    fun hideDialogLoading() {
        pd?.let {
            if (it.isShowing)
                it.dismiss()
        }
    }


    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


}
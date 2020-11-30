package com.example.marlonviana.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.marlonviana.R


class VisibilityManager {

    companion object{
        var progressDialog: AlertDialog? = null

        @SuppressLint("InflateParams")

        fun showProgress(activity: Activity, show:Boolean){
            if (progressDialog !=null)
            {
                progressDialog?.dismiss()
                progressDialog = null
            }

            if (show)
            {
                val dialogBuilder = AlertDialog.Builder(activity)
                val inflater = activity.layoutInflater
                val dialogView = inflater.inflate(R.layout.view_progress_bar, null)
                dialogBuilder.setView(dialogView)
                if (progressDialog ==null)
                {
                    progressDialog = dialogBuilder.create()
                    progressDialog?.setCancelable(false)
                    progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    progressDialog!!.show()
                }
            }
        }
    }
}


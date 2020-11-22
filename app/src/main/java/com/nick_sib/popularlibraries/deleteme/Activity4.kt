package com.nick_sib.popularlibraries.deleteme

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.databinding.Activity4Binding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class Activity4 : MvpAppCompatActivity(), Activity4View {
    private val REQUEST_CODE = 42
    private val REQUEST_PERMISSION_CODE = 142
    private val REQUESTED_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE

    private lateinit var binding: Activity4Binding

    private var alertDialog: AlertDialog? = null

    private fun checkPermission() =
        ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSION) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(REQUESTED_PERMISSION), REQUEST_PERMISSION_CODE)
    }

    private val presenter: PresenterLesson4 by moxyPresenter {
        PresenterLesson4(ModelLesson4)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity4Binding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.bOpenImage.isEnabled = checkPermission()
        if (!binding.bOpenImage.isEnabled) {
            requestPermission()
        }

        binding.bOpenImage.setOnClickListener {
            openImageDialog()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            binding.bOpenImage.isEnabled = (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            if (!binding.bOpenImage.isEnabled) {
                Toast.makeText(this, "Пространное рассуждение что разрешение важно для работы", Toast.LENGTH_LONG).show()
                return
            }
        }
    }


    private fun openImageDialog(){
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode != REQUEST_CODE || resultCode != RESULT_OK) return

        resultData?.run {
            data?.also {imagePath ->
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.also {imagesRoot ->
                    presenter.convertImage(imagePath.toString(), imagesRoot.toString())
                }

            }
        }
    }


    override fun beginConvert(imagePath: String) {
        binding.ivOriginal.setImageURI(imagePath.toUri())
        alertDialog = AlertDialog.Builder(this).apply {
            setTitle("Conversation")
            setMessage("converting in progress!")
            setView(R.layout.progress_dialog)
            setCancelable(false)
            setNegativeButton("Cancel") { dialog: DialogInterface, _ ->
                presenter.cancelConvert()
                dialog.cancel()
            }
        }.create().apply {
            show()
        }
    }

    override fun endConvert(imagePath: Uri) {
        alertDialog?.run {
            if (isShowing)
                cancel()
        }

        binding.ivConverted.setImageURI(imagePath)
    }

    override fun progressConvert(progress: Int) {
        alertDialog?.findViewById<ProgressBar>(R.id.bar)?.progress = progress
    }

    override fun showError(errorText: String) {
        alertDialog?.run {
            if (isShowing)
                cancel()
        }
    }
}
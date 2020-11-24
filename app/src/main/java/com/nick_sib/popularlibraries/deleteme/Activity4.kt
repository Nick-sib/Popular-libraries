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
import com.nick_sib.popularlibraries.R
import kotlinx.android.synthetic.main.activity_4.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter



class Activity4 : MvpAppCompatActivity(), Activity4View {
    private val REQUEST_CODE = 42
    private val REQUEST_PERMISSION_CODE = 142
    private val REQUESTED_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE

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
        setContentView(R.layout.activity_4)


        bOpenImage.isEnabled = checkPermission()
        if (!bOpenImage.isEnabled) {
            requestPermission()
        }

        bOpenImage.setOnClickListener {
            openImageDialog()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            bOpenImage.isEnabled = (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            if (!bOpenImage.isEnabled) {
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
                    presenter.convertImage(imagePath, imagesRoot.toString())
                }

            }
        }
    }


    override fun beginConvert(imagePath: Uri) {
        ivOriginal.setImageURI(imagePath)
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

        ivConverted.setImageURI(imagePath)
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
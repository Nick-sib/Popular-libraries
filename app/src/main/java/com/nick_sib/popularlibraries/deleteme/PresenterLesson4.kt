package com.nick_sib.popularlibraries.deleteme

import android.R.attr.data
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import moxy.MvpPresenter
import java.io.File


class PresenterLesson4(private val model: ModelLesson4): MvpPresenter<Activity4View>() {
    private var disposable: Disposable? = null

    private fun getCapturedImage(context: Context, imagePath: Uri): Bitmap? =
        if (Build.VERSION.SDK_INT < 28)
            loadImageO(context, imagePath)
        else
            loadImageP(context, imagePath)


    @RequiresApi(Build.VERSION_CODES.M)
    fun loadImageO(context: Context, imagePath: Uri): Bitmap? =
        MediaStore.Images.Media.getBitmap(
            context.contentResolver, imagePath
        )

    @RequiresApi(Build.VERSION_CODES.P)
    fun loadImageP(context: Context, imagePath: Uri): Bitmap? {
        val source = ImageDecoder.createSource(context.contentResolver, imagePath)
        return ImageDecoder.decodeBitmap(source)
    }

    fun convertImage(context: Context, imagePath: Uri) {
        val bitmap = getCapturedImage(context, imagePath)

        bitmap?.also { originalBitmap ->
            val root = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            root?.also { imagesRoot ->
                val hotObservable: ConnectableObservable<Int> = model.convert(
                    originalBitmap,
                    imagesRoot.toString(),
                    imagePath.getName(context)
                )
                hotObservable.subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable?) {
                        disposable = d
                        viewState.beginConvert(imagePath)
                    }

                    override fun onNext(progress: Int?) {
                        progress?.run {
                            viewState.progressConvert(progress)
                        }
                    }

                    override fun onError(e: Throwable?) {
                        viewState.showError(e.toString())
                    }

                    override fun onComplete() {
                        model.convertedImage?.run {
                            viewState.endConvert(this)
                        } ?: viewState.showError("Ошибка созданного имени файла")
                    }
                }
                )
                hotObservable.connect()
            } ?: viewState.showError("Корневая директория не определилась")
        } ?: viewState.showError("Картинка не загрузилась")

    }


    fun cancelConvert() {
        disposable?.run {
            if (!isDisposed) dispose()
        }
        model.convertedImage?.run {
            viewState.endConvert(this)
        }
    }

}
package com.nick_sib.popularlibraries.deleteme

import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.toUri
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream


object ModelLesson4 {

    private const val IMAGE_FOLDER = "/image_converter_results"
    private const val MAX_STEPS = 10

    var convertedImage: Uri? = null
        private set



    fun convert(bitmap: Bitmap, imagesRoot: String, fileName: String): ConnectableObservable<Int> =
         Observable.create<Int>{ emitter ->
             for (i in 1 until MAX_STEPS) {
                 Thread.sleep(1000)
                 emitter.onNext(i)
             }

             //Непосредственно конвертация
             val myImagesDir = File(imagesRoot + IMAGE_FOLDER)
             if (!myImagesDir.exists()) myImagesDir.mkdirs()
             val result = String.format("%s%s/%s.png", imagesRoot, IMAGE_FOLDER, fileName)
             val out = FileOutputStream(File(result))
             bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
             emitter.onNext(MAX_STEPS)
             convertedImage = result.toUri()
             out.flush()
             out.close()
             emitter.onComplete()
         }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .publish()


}
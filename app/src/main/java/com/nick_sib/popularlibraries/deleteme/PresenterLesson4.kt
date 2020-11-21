package com.nick_sib.popularlibraries.deleteme


import android.net.Uri
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observables.ConnectableObservable
import moxy.MvpPresenter


class PresenterLesson4(private val model: ModelLesson4): MvpPresenter<Activity4View>() {
    private var disposable: Disposable? = null

/**@param imagePath - путь к конвертируемой картинке
 * @param imagesRoot - путь к папке для результатов конвертирования
 * */
    fun convertImage(imagePath: Uri, imagesRoot: String) {
            val hotObservable: ConnectableObservable<Int> = model.convert(
                imagePath,
                imagesRoot
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
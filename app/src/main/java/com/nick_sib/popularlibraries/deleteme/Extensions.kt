package com.nick_sib.popularlibraries.deleteme

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.annotation.RequiresApi
import com.nick_sib.popularlibraries.App



//на тесте надо подменить модуль с этими расширениями
fun Uri.getName(): String =
    App.instance.baseContext
        .contentResolver
        .query(this, null, null, null, null)?.let { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            val fileName = cursor.getString(nameIndex)
            cursor.close()
            fileName.replace("jpg","png",true)
        } ?: "result.png"

fun Uri.getCapturedImage(): Bitmap?  =
    if (Build.VERSION.SDK_INT < 28)
        loadImageO(App.instance.baseContext, this)
    else
        loadImageP(App.instance.baseContext, this)


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




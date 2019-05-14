package com.akalatskij.testtask.model.storage

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class LocalFileWorker: ImageSaver{

    override fun saveImageToDir(path: String, name: String, bitmap: Bitmap): Uri {

        val file = File(path, "$name.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress the bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the output stream
            stream.flush()

            // Close the output stream
            stream.close()
        } catch (e: IOException){ // Catch the exception
            Log.e("Save error", e.toString())
        }

        // Return the saved image path to uri
        Log.d("Save image", file.absolutePath)
        return Uri.parse(file.absolutePath)
    }

}

interface ImageSaver {
    fun saveImageToDir(path: String, name: String, bitmap: Bitmap): Uri
}
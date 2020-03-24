package com.example.rest_api_retrofit_android.Utilitis

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class ImageDownloader: AsyncTask<String, Void, Bitmap>() {
    override fun doInBackground(vararg urls: String?): Bitmap? {
        try {
            val url = URL(urls[0])
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.connect()

            val inputStream: InputStream = connection.inputStream

            return BitmapFactory.decodeStream(inputStream)

        }catch (e: MalformedURLException){
            e.stackTrace
        }catch (e: IOException){
            e.stackTrace
        }
        return null
    }

}

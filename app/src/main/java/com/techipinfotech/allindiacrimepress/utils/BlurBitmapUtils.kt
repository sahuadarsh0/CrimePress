package com.techipinfotech.allindiacrimepress.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur

object BlurBitmapUtils {
    private const val BLUR_RADIUS = 20
    private const val SCALED_WIDTH = 100
    private const val SCALED_HEIGHT = 100
    @JvmOverloads
    fun blur(imageView: ImageView, bitmap: Bitmap, radius: Int = BLUR_RADIUS) {
        imageView.setImageBitmap(getBlurBitmap(imageView.context, bitmap, radius))
    }

    fun getBlurBitmap(context: Context?, bitmap: Bitmap): Bitmap {
        return getBlurBitmap(context, bitmap, BLUR_RADIUS)
    }

    fun getBlurBitmap(context: Context?, bitmap: Bitmap, radius: Int): Bitmap {
        val inputBitmap: Bitmap = Bitmap.createScaledBitmap(bitmap, SCALED_WIDTH, SCALED_HEIGHT, false)
        val outputBitmap: Bitmap = Bitmap.createBitmap(inputBitmap)
        val rs: RenderScript = RenderScript.create(context)
        val blurScript: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn: Allocation = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut: Allocation = Allocation.createFromBitmap(rs, outputBitmap)
        blurScript.setRadius(radius.toFloat())
        blurScript.setInput(tmpIn)
        blurScript.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)
        return outputBitmap
    }
}
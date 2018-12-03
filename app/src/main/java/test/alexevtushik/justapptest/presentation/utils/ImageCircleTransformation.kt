package test.alexevtushik.justapptest.presentation.utils

import android.graphics.*
import com.squareup.picasso.Transformation

class ImageCircleTransformation : Transformation {

    private var hasBorder: Boolean = false
    private var borderColor: Int? = null
    private var borderSize: Float? = null

    constructor() {
        this.hasBorder = false
    }

    constructor(borderColor: Int, borderSize: Float) {
        this.borderColor = borderColor
        this.borderSize = borderSize
        this.hasBorder = true
    }

    override fun transform(source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val paint = Paint()
        paint.shader = shader
        paint.isAntiAlias = true
        var radius = size / 2f
        val xCoordinate = radius
        val yCoordinate = radius
        if (hasBorder) {
            val paintBg = Paint()
            paintBg.isAntiAlias = true
            paintBg.color = this.borderColor!!
            canvas.drawCircle(xCoordinate, yCoordinate, radius, paintBg)
            radius -= this.borderSize!!
        }
        canvas.drawCircle(xCoordinate, yCoordinate, radius, paint)
        squaredBitmap.recycle()
        return bitmap
    }

    override fun key(): String {
        return ImageCircleTransformation::class.java.simpleName
    }
}
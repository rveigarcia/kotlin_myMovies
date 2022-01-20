package com.mon.mymovies.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.mon.mymovies.R
import com.mon.mymovies.model.Movie

class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    var ratio: Float = 1f

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView)
        ratio = a.getFloat(R.styleable.AspectRatioImageView_ratio, 1f)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0){
            return
        }

        if (width > 0){
            height = (width * ratio). toInt()
        } else if (height > 0) {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }
}

/*
class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val cover: ImageView
    private val title: TextView

    init {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.view_movie_item, this, true)

        cover = view.findViewById(R.id.cover)
        title = view.findViewById(R.id.title)

        orientation = VERTICAL
    }

    fun setMovie(movie: Movie){
        title.text = movie.title
      //  cover.image = movie.cover
    }
}

*/

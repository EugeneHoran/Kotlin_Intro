package eugene.com.kotlininro.util

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.v7.widget.CardView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object NewsBindingAdapter {
    @JvmStatic
    @BindingAdapter("setVisible")
    fun bindVisibility(view: View, isDataLoading: Boolean) {
        view.visibility = if (isDataLoading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImageGlide(imageView: ImageView, imageReference: String?) {
        if (TextUtils.isEmpty(imageReference)) {
            imageView.visibility = View.GONE
            return
        }
        GlideApp.with(imageView.context).load(imageReference)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageInt")
    fun bindImageInt(imageView: ImageView, imageInt: Int?) {
        GlideApp.with(imageView.context).load(imageInt)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("cardColor")
    fun bindCardBackground(cardView: CardView, color: Int?) {
        cardView.setCardBackgroundColor(color!!)
    }

    @JvmStatic
    @BindingAdapter("tintColor")
    fun bindTintDrawable(imageView: ImageView, color: Int?) {
        imageView.drawable.mutate()
        imageView.drawable.colorFilter = PorterDuffColorFilter(color!!, PorterDuff.Mode.MULTIPLY)
    }

    @JvmStatic
    @BindingAdapter("textColor")
    fun bindTextColor(textView: TextView, color: Int?) {
        textView.setTextColor(color!!)
    }
}
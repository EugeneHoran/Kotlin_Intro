package eugene.com.kotlininro.util

import android.databinding.BindingAdapter
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object NewsBindingAdapter {
    @JvmStatic
    @BindingAdapter("setVisible")
    fun bindVisibility(view: View, isDataLoading: Boolean) {
        view.visibility = if (isDataLoading) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imageView: ImageView, imageReference: String?) {
        if (TextUtils.isEmpty(imageReference)) {
            imageView.visibility = View.GONE
            return
        }
        Glide.with(imageView.context).load(imageReference)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

}
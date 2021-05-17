package com.krithik.findthefreeze

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.krithik.findthefreeze.country.CountryProperty
import com.krithik.findthefreeze.ui.country.CountryApiStatus
import com.krithik.findthefreeze.ui.country.GridAdapter


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let { val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        GlideToVectorYou
            .init()
            .with(imgView.context)
            .withListener(object : GlideToVectorYouListener {
                override fun onLoadFailed() {
                    Toast.makeText(imgView.context, "Loading error", Toast.LENGTH_SHORT).show()
                }
                override fun onResourceReady() {

                }
            }).setPlaceHolder(R.drawable.loading_animation,R.drawable.broken_image)
            .load(imgUri, imgView)
    }
}

@BindingAdapter("CountryApiStatus")
fun bindStatus(statusImageView: ImageView, status: CountryApiStatus?) {
    when (status) {
        CountryApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CountryApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        CountryApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

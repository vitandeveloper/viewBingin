package com.example.marlonviana.util
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.marlonviana.R

fun View.setAnimationPushButton() {
    var anim : Animation = AnimationUtils.loadAnimation(this.context, R.anim.bounce_button)
    this.startAnimation(anim)
}

fun View.setAnimationFadeIn(duration: Int) {
    if (this.visibility == View.INVISIBLE || this.visibility == View.GONE){
        this.visibility = View.VISIBLE
        var anim : Animation = AnimationUtils.loadAnimation(this.context, R.anim.fade_in)
        anim.duration = duration.toLong()
        this.startAnimation(anim)
    }
}

fun View.setAnimationFadeOut(duration: Int) {
    val viewMaster = this

    if (this.visibility == View.VISIBLE){
        var anim : Animation = AnimationUtils.loadAnimation(viewMaster.context, R.anim.fade_out)
        anim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                viewMaster.visibility= View.GONE
            }

            override fun onAnimationStart(p0: Animation?) {
            }
        })
        anim.duration = duration.toLong()
        viewMaster.startAnimation(anim)
    }
}
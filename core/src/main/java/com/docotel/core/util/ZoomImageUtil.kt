package com.docotel.core.util

import android.animation.*
import android.content.*
import android.graphics.*
import android.view.*
import android.view.animation.*
import android.widget.*

import com.bumptech.glide.load.engine.*

/**
 * Created by bezzo on 22/12/17.
 */

class ZoomImageUtil(private val resourceDefaultId: Int) {

    private var mCurrentAnimatorEffect: Animator? = null
    private var mShortAnimationDurationEffect: Int = 0

    fun show(currentAnimatorEffect: Animator, thumbView: View, context: Context,
             urlPhoto: String, ivExpandedPhoto: ImageView, container: View,
             shortAnimationDurationEffect: Int) {
        this.mCurrentAnimatorEffect = currentAnimatorEffect
        this.mShortAnimationDurationEffect = shortAnimationDurationEffect

        if (mCurrentAnimatorEffect != null) mCurrentAnimatorEffect!!.cancel()

        GlideApp.with(context)
                .load(urlPhoto)
                .placeholder(resourceDefaultId)
                .error(resourceDefaultId)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(ivExpandedPhoto)

        val startBounds = Rect()
        val finalBounds = Rect()
        val globalOffset = Point()

        thumbView.getGlobalVisibleRect(startBounds)
        container.getGlobalVisibleRect(finalBounds, globalOffset)
        startBounds.offset(-globalOffset.x, -globalOffset.y)
        finalBounds.offset(-globalOffset.x, -globalOffset.y)

        val startScale: Float
        if (finalBounds.width().toFloat() / finalBounds.height() > startBounds.width().toFloat() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = startBounds.height().toFloat() / finalBounds.height()
            val startWidth = startScale * finalBounds.width()
            val deltaWidth = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width().toFloat() / finalBounds.width()
            val startHeight = startScale * finalBounds.height()
            val deltaHeight = (startHeight - startBounds.height()) / 2
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        thumbView.alpha = 0f
        ivExpandedPhoto.visibility = View.VISIBLE

        ivExpandedPhoto.pivotX = 0f
        ivExpandedPhoto.pivotY = 0f

        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        val set = AnimatorSet()
        set
                .play(ObjectAnimator.ofFloat<View>(ivExpandedPhoto, View.X,
                        startBounds.left.toFloat(), finalBounds.left.toFloat()))
                .with(ObjectAnimator.ofFloat<View>(ivExpandedPhoto, View.Y,
                        startBounds.top.toFloat(), finalBounds.top.toFloat()))
                .with(ObjectAnimator.ofFloat(ivExpandedPhoto, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(ivExpandedPhoto,
                        View.SCALE_Y, startScale, 1f))
        set.duration = mShortAnimationDurationEffect.toLong()
        set.interpolator = DecelerateInterpolator()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mCurrentAnimatorEffect = null
            }

            override fun onAnimationCancel(animation: Animator) {
                mCurrentAnimatorEffect = null
            }
        })
        set.start()
        mCurrentAnimatorEffect = set

        ivExpandedPhoto.setOnClickListener {
            if (mCurrentAnimatorEffect != null) {
                mCurrentAnimatorEffect!!.cancel()
            }

            // back to their original values.
            val set = AnimatorSet()
            set.play(ObjectAnimator
                    .ofFloat<View>(ivExpandedPhoto, View.X, startBounds.left.toFloat()))
                    .with(ObjectAnimator
                            .ofFloat<View>(ivExpandedPhoto,
                                    View.Y, startBounds.top.toFloat()))
                    .with(ObjectAnimator
                            .ofFloat(ivExpandedPhoto,
                                    View.SCALE_X, startScale))
                    .with(ObjectAnimator
                            .ofFloat(ivExpandedPhoto,
                                    View.SCALE_Y, startScale))
            set.duration = mShortAnimationDurationEffect.toLong()
            set.interpolator = DecelerateInterpolator()
            set.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    thumbView.alpha = 1f
                    ivExpandedPhoto.visibility = View.GONE
                    mCurrentAnimatorEffect = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    thumbView.alpha = 1f
                    ivExpandedPhoto.visibility = View.GONE
                    mCurrentAnimatorEffect = null
                }
            })
            set.start()
            mCurrentAnimatorEffect = set
        }
    }
}

package com.docotel.core.widget

import android.app.Activity
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.docotel.core.R

/**
 * Created by Lafran on 11/9/17.
 */
class TopSnackBar(val context: Activity, val text: String, val subText: String, val clickOnDismiss: Boolean = false, val autoShow: Boolean = true) {

    val DELAYED_ANIMATION_TO_DISMISS = 1500L
    private var viewGroup: ViewGroup? = null
    private var defaultBackgroundColorRes = R.color.top_snack_bar_color
    private var listener: OnDismissListener? = null

    companion object {
        val ALERT_COLOR = Color.parseColor("#E74C3C")

        var isShowing = false
        var isOneShotEnabled = true
        private var contentView: View? = null
    }

    init {
        setup()
        if (autoShow)
            show()
    }

    private fun setup() {
        initContent()
    }

    private fun initContent() {
        if (text.isEmpty())
            return

        if (contentView == null) {
            contentView = View.inflate(context, R.layout.layout_top_snack_bar, null)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            contentView!!.layoutParams = params
        }

        backgroundColorRes(defaultBackgroundColorRes)
        val snackTitle = contentView!!.findViewById<TextView>(R.id.tv_snack_title)
        val snackSubTitle = contentView!!.findViewById<TextView>(R.id.tv_snack_subtitle)

        snackTitle.text = text

        if (!subText.isEmpty())
            snackSubTitle.text = subText

        if (clickOnDismiss) {
            contentView!!.setOnClickListener { view ->
                animateHide()
            }
        }
    }

    fun onDismiss(listener: OnDismissListener): TopSnackBar {
        this.listener = listener
        return this
    }

    fun background(color: Int): TopSnackBar {
        contentView?.setBackgroundColor(color)
        return this
    }

    fun background(color: String): TopSnackBar {
        contentView?.setBackgroundColor(Color.parseColor(color))
        return this
    }

    fun backgroundColorRes(colorRes: Int): TopSnackBar {
        contentView?.setBackgroundColor(ContextCompat.getColor(context, colorRes))
        return this
    }

    fun show() {
        if (isOneShotEnabled) {
            showOnShot()
        } else {
            showBurst()
        }
    }

    fun update(): TopSnackBar {
        initContent()
        return this
    }

    fun dismis(delay: Long) {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                animateHide()
            }
        }, delay)
    }

    private fun showOnShot() {
        if (!isShowing) {
            viewGroup!!.addView(contentView)
            animate()
        }
    }

    private fun showBurst() {
        viewGroup!!.addView(contentView)
        animate()
    }

    private fun animate() {
        animateShow()
        if (!clickOnDismiss) {
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    animateHide()
                }
            }, DELAYED_ANIMATION_TO_DISMISS)
        }
    }

    private fun animateShow() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.snack_bar_show)
        contentView?.startAnimation(anim)
        TopSnackBar.isShowing = true
    }

    private fun animateHide() {
        val anim = AnimationUtils.loadAnimation(context, R.anim.snack_bar_hide)
        contentView?.startAnimation(anim)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                viewGroup?.removeView(contentView)
                TopSnackBar.isShowing = false
                listener?.onDismiss(this@TopSnackBar)
            }

        })
    }

    interface OnDismissListener {
        fun onDismiss(topSnackBar: TopSnackBar)
    }
}
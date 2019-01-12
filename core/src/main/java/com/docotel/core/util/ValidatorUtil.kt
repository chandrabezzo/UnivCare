package com.docotel.core.util

import android.text.TextUtils
import android.widget.TextView

/**
 * Created by Lafran on 11/15/17.
 */

class Validator {

    val views: MutableList<TextView> = mutableListOf()
    val requirements: MutableList<Requirement> = mutableListOf()
    var listener: OnValidateChanged? = null

    companion object {
        const val CANNOT_FILL_BLANK = 0
        const val CANNOT_FILL_MORE_THAN_MAX = 1
    }

    fun add(view: TextView, requirement: Requirement): Validator {
        views.add(view)
        requirements.add(requirement)
        return this
    }

    fun setListener(listener: OnValidateChanged): Validator {
        this.listener = listener
        return this
    }

    fun validate() {
        for (i in 0..(views.size - 1)) {
            val tv = views[i]
            val req = requirements[i]
            val type = req.type
            if (type == CANNOT_FILL_BLANK) {
                if (TextUtils.isEmpty(tv.text)) {
                    listener?.didFailValidated(tv, req.alertMessage)
                    return
                } else {
                    if (!TextUtils.isEmpty(req.placeholder)) {
                        if (tv.text.equals(req.placeholder)) {
                            listener?.didFailValidated(tv, req.alertMessage)
                            return
                        }
                    }
                }
            } else if (type == CANNOT_FILL_MORE_THAN_MAX) {
                if (!TextUtils.isEmpty(tv.text)) {
                    if (req.maxChar != 0) {
                        if (tv.text.length > req.maxChar) {
                            listener?.didFailValidated(tv, req.alertMessage)
                            return
                        }
                    }
                }
            }
        }
        listener?.didSuccess()
    }
}

interface OnValidateChanged {
    fun didFailValidated(textView: TextView, alertMessage: String)
    fun didSuccess()
}

class Requirement(val type: Int, val alertMessage: String, val maxChar: Int = 0, val placeholder: String = "")
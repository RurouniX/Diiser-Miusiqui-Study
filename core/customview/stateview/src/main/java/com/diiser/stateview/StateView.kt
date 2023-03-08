package com.diiser.stateview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.diiser.stateview.databinding.StateviewLayoutBinding

class StateView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private var attributes: TypedArray = context.theme.obtainStyledAttributes(
        attrs,
        R.styleable.custom_stateview,
        0, 0
    )

    private val stateButton by lazy { stateviewBinding.stateviewBtn }
    private val stateTextTitle by lazy { stateviewBinding.stateviewTxt }
    private val stateImage by lazy { stateviewBinding.stateviewImg }
    private val stateGroup by lazy { stateviewBinding.stateview }

    private var stateviewBinding: StateviewLayoutBinding =
        StateviewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {

//        setStateMessage(messageTxt, messageSubTxt)
//        setButtonMessage(messageButton)
//        setImage(image)
//        setStateView()

    }
//
//    private fun setStateView() {
//        if (startInLoading)
//            showLoading()
//    }
//
//    fun showLoading() {
//        setVisible()
//        stateLoading.setVisible()
//        stateGroup.setGone()
//    }
//
//    fun hideLoading() {
//        setGone()
//        stateLoading.setGone()
//        stateGroup.setGone()
//    }
//
//    fun loadingVisibility(visibility: Int) {
//        this.visibility = visibility
//        stateLoading.visibility = visibility
//    }
//
//    fun isLoading(): Boolean {
//        return stateLoading.isVisible
//    }
//
//    fun showErrorState() {
//        stateLoading.visibility = GONE
//        stateGroup.visibility = VISIBLE
//    }

    fun hideErrorState() {
        stateGroup.visibility = GONE
    }

//    fun setStateMessage(message: String, subtitle: String) {
//        stateTextTitle.text = message
//        stateTextSubTitle.text = subtitle
//    }

    fun setButtonMessage(message: String?) {
        stateButton.visibility = if (message.isNullOrEmpty()) GONE else {
            stateButton.text = message
            VISIBLE
        }
    }

//    fun setButtonClick(callback: () -> Unit) {
//        stateButton.setOnClickListener {
//            hideErrorState()
//            showLoading()
//            callback.invoke()
//        }
//    }

    fun setImage(drawable: Drawable?) {
        stateImage.visibility = if (drawable != null) {
            stateImage.setImageDrawable(drawable)
            VISIBLE
        } else
            GONE
    }

//    fun mountByStatus(code: Int) {
//        showErrorState()
//        when (code) {
//            0 -> {
//                setStateMessage(
//                    context.getString(R.string.no_connection_title),
//                    context.getString(R.string.no_connection_subtitle)
//                )
//            }
//            else -> {
//                setStateMessage(
//                    context.getString(R.string.generic_error_title),
//                    context.getString(R.string.generic_error_subtitle)
//                )
//            }
//        }
//    }
}

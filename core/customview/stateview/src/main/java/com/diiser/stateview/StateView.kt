package com.diiser.stateview

import android.content.Context
import android.content.res.TypedArray
import android.opengl.Visibility
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieDrawable
import com.diiser.stateview.databinding.StateviewLayoutBinding

class StateView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs) {

    private var attributes: TypedArray = context.theme.obtainStyledAttributes(
        attrs,
        R.styleable.custom_stateview,
        0, 0
    )

    private val isLoadingProperties by lazy {
        attributes.getBoolean(
            R.styleable.custom_stateview_isLoading,
            false
        )
    }

    private val startInLoading by lazy {
        attributes.getBoolean(
            R.styleable.custom_stateview_startLoading,
            true
        )
    }

    private val btnMessageProperties by lazy {
        attributes.getString(
            R.styleable.custom_stateview_btn_try_again
        ) ?: context.getString(R.string.btn_try_again)
    }

    private val messageProperties by lazy {
        attributes.getString(R.styleable.custom_stateview_message)
            ?: context.getString(R.string.loading_message_default)
    }

    private val loadingImgProperties by lazy {
        attributes.getResourceId(
            R.styleable.custom_stateview_stateview_image,
            R.raw.loading_music
        )
    }

    private val stateButton by lazy { stateviewBinding.stateviewBtn }
    private val stateTextTitle by lazy { stateviewBinding.stateviewTxt }
    private val stateImage by lazy { stateviewBinding.stateviewImg }

    private var stateviewBinding: StateviewLayoutBinding =
        StateviewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        setStateView()
        mountBtn()
    }

    private fun mountBtn() {
        stateButton.text = btnMessageProperties
    }

    private fun setStateView() {
        if (startInLoading)
            showLoading(VISIBLE)
    }

    fun showLoading(visibility: Int = VISIBLE) {
        this.visibility = visibility
        mountLoading()
    }

    private fun mountLoading() {
        with(stateImage) {
            setAnimation(R.raw.loading_music)
            playAnimation()
            repeatCount = LottieDrawable.INFINITE
        }
        stateButton.visibility = GONE
        stateTextTitle.text = context.getString(R.string.loading_message_default)
    }


    fun showErrorState() {
        visibility = VISIBLE
        with(stateImage) {
            setAnimation(R.raw.warning_status)
            playAnimation()
            repeatCount = 0
        }
        stateButton.visibility = VISIBLE
        stateTextTitle.text = context.getString(R.string.error_message_default)
    }

    fun setButtonClick(callback: () -> Unit) {
        stateButton.setOnClickListener {
            showLoading(VISIBLE)
            callback.invoke()
        }
    }

}

package com.diiser

import android.view.View.VISIBLE
import androidx.lifecycle.MutableLiveData

class FlowState<D>(
    val status: Status,
    val data: D? = null,
    val error: Throwable? = null,
    val resources: Int = 0,
    val loadingVisibility: Int = VISIBLE
) {
    enum class Status { LOADING, SUCCESS, ERROR }
}

fun <D> MutableLiveData<FlowState<D>>.postLoading(state: Int) {
    value = FlowState(FlowState.Status.LOADING, loadingVisibility = state)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?, loadingVisibility: Int = 0) {
    value =
        FlowState(FlowState.Status.SUCCESS, data = data, loadingVisibility = loadingVisibility)
}

fun <D> MutableLiveData<FlowState<D>>.postSuccess(data: D?) {
    value = FlowState(FlowState.Status.SUCCESS, data = data)
}

fun <D> MutableLiveData<FlowState<D>>.postError(throwable: Throwable) {
    value = FlowState(FlowState.Status.ERROR, error = throwable)
}


fun <D> MutableLiveData<FlowState<D>>.postError(resources: Int) {
    value = FlowState(FlowState.Status.ERROR, resources = resources)
}

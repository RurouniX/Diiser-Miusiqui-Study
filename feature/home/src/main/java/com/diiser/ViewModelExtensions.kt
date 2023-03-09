package com.diiser

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


fun <D> LiveData<FlowState<D>>.observerEvents(
    lifecycleOwner: LifecycleOwner,
    onLoading: (Int) -> Unit = {},
    onSuccess: (D) -> Unit = {},
    onError: (Throwable) -> Unit = {},
    onErrorWithId: ((Int) -> Unit)? = null
) {
    observe(lifecycleOwner) {
        when (it.status) {
            FlowState.Status.LOADING -> {
                onLoading(it.loadingVisibility)
            }
            FlowState.Status.SUCCESS -> {
                it.data?.let { data -> onSuccess(data) }
            }
            FlowState.Status.ERROR -> {
                if (it.resources != 0)
                    it.resources.let { error -> onErrorWithId?.invoke(error) }
                else
                    it.error?.let { error -> onError(error) }
            }
        }
    }
}
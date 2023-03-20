package com.diiser.testutils

import com.diiser.utils.ProviderContext
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestProviderContext: ProviderContext(){
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}
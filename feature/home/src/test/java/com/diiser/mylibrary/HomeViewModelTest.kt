package com.diiser.mylibrary

import android.R
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diiser.flowstate.FlowState
import com.diiser.home.HomeUseCase
import com.diiser.home.HomeViewModel
import com.diiser.model.home.SearchModel
import com.diiser.network.utils.ResponseError
import com.diiser.testutils.TestProviderContext
import com.diiser.testutils.readJsonFile
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    private val pathName = "../feature/home/src/test/java/com/diiser/assets"

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val providerContext = TestProviderContext()

    private val searchModel =
        javaClass.classLoader?.let { readJsonFile<SearchModel>(it.getResourceAsStream("searchModel.json")) }

    private val viewModel: HomeViewModel by lazy { HomeViewModel(useCase, providerContext) }
    private val useCase = mockk<HomeUseCase>()

    @Test
    fun shouldReturnMusicList_whenSuccess() {

        coEvery {
            useCase.getHomeData(
                "metal",
                onSuccess = captureLambda(),
                onError = any()
            )
        } coAnswers {
            lambda<(SearchModel) -> Unit>().invoke(searchModel!!)
        }

        viewModel.getHomeData("metal")
        assert(viewModel.homeDataLiveData.value?.status == FlowState.Status.SUCCESS)
        viewModel.homeDataLiveData.value?.data?.data?.let{
            assert(it.size == 25)
            assert(it[0].title == "Metal")
            assert(it[0].duration == 80)
            assert(it[0].link == "https://www.deezer.com/track/1741590727")
        }
    }

    @Test
    fun shouldReturnLoadingState_whenCallHomeDataFunction() {

        coEvery {
            useCase.getHomeData(
                "metal",
                onSuccess = any(),
                onError = captureLambda()
            )
        } coAnswers {
            lambda<(ResponseError) -> Unit>().invoke(ResponseError(Throwable()))
        }

        viewModel.getHomeData("metal")
        assert(viewModel.homeDataLiveData.value?.status == FlowState.Status.ERROR)
    }

    @Test
    fun shouldReturnLoadingState_whenCallHomeDataFunctionLoading() {

        coEvery {
            useCase.getHomeData(
                "metal",
                onSuccess = any(),
                onError = any()
            )
        } just (runs)

        viewModel.getHomeData("metal")
        assert(viewModel.homeDataLiveData.value?.status == FlowState.Status.LOADING)
    }

}
package com.diiser.home

import com.diiser.home.HomeUseCase
import com.diiser.model.home.SearchModel
import com.diiser.network.repository.HomeRepository
import com.diiser.network.utils.ResponseError
import com.diiser.network.utils.ResultType
import com.diiser.testutils.readJsonFile
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class HomeUserCaseTest {

    private val homeUseCase: HomeUseCase by lazy { HomeUseCase(repository) }

    private val repository = mockk<HomeRepository>()
    private val searchTerm = "Metal"

    private val searchModel =
        javaClass.classLoader?.run { readJsonFile<SearchModel>(getResourceAsStream("searchModel.json")) }

    @Test
    fun shouldReturnSearchModel_whenCall_getHomeData() = runBlocking {
        val expectedResult = ResultType.Success(searchModel)
        var result: SearchModel? = null

        coEvery { repository.getHomeData(any()) } coAnswers { expectedResult as ResultType.Success<SearchModel> }

        homeUseCase.getHomeData(searchTerm, onSuccess = { result = it }, onError = {})

        assert(result?.data?.get(0)?.title == "Metal")
    }


    @Test
    fun shouldReturnError_whenCall_getHomeData() = runBlocking {
        val expectedResult = ResultType.Fail(ResponseError(Throwable("Error")))
        var result: ResponseError? = null

        coEvery { repository.getHomeData(any()) } coAnswers { expectedResult }

        homeUseCase.getHomeData(searchTerm, onSuccess = {}, onError = {result = it})

        assert(result?.cause?.message == "Error")
    }

}
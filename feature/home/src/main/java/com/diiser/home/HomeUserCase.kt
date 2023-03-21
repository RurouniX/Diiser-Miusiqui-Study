package com.diiser.home

import com.diiser.model.home.SearchModel
import com.diiser.network.repository.HomeRepository
import com.diiser.network.utils.ResponseError
import com.diiser.network.utils.handleResultType

class HomeUseCase(private val homeRepository: HomeRepository) {

    suspend fun getHomeData(
        search: String,
        onSuccess: (SearchModel) -> Unit,
        onError: (ResponseError) -> Unit
    ) {
        val result = homeRepository.getHomeData(search)

        result.handleResultType(
            success = {
                if (it.data == null) {
                    onError(ResponseError(cause = Throwable("Sem dados para essa busca")))
                } else {
                    onSuccess(it)
                }
            },
            error = {
                onError(it)
            }
        )
    }
}



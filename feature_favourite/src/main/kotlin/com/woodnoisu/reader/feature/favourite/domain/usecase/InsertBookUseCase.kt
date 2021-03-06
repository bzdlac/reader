package com.woodnoisu.reader.feature.favourite.domain.usecase

import com.woodnoisu.reader.feature.favourite.domain.model.FavouriteBookDomainModel
import com.woodnoisu.reader.feature.favourite.domain.repository.FavouriteRepository
import java.io.IOException

internal class InsertBookUseCase(
    private val favouriteRepository: FavouriteRepository
) {

    sealed class Result {
        data class Success(val data: String) : Result()
        data class Error(val e: Throwable) : Result()
    }

    suspend fun execute(favouriteBookDomainModel: FavouriteBookDomainModel): Result {
        return try {
            favouriteRepository.insertBook(favouriteBookDomainModel)
            Result.Success("新增成功")
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}

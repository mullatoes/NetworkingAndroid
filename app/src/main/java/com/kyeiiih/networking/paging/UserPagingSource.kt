package com.kyeiiih.networking.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kyeiiih.networking.model.User
import com.kyeiiih.networking.network.ApiClient.userService
import com.kyeiiih.networking.network.UserService

class UserPagingSource(private val userService: UserService) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        try {
            val page = params.key ?: 1
            val userList = userService.getUsers(page)
            return LoadResult.Page(
                data = userList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}

val pagingConfig = PagingConfig(
    pageSize = 20,
    prefetchDistance = 5,
    enablePlaceholders = false
)

val pager = Pager(config = pagingConfig) {
    UserPagingSource(userService)
}
val userFlow = pager.flow



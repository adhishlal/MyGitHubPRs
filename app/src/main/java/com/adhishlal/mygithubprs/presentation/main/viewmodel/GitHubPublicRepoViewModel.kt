package com.adhishlal.mygithubprs.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adhishlal.mygithubprs.data.model.request.UserRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.domain.usecases.network.FetchUserRepositoriesUseCase
import com.adhishlal.mygithubprs.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitHubPublicRepoViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var fetchUserRepositoriesUseCase: FetchUserRepositoriesUseCase

    val userRepositoriesLiveData = MutableLiveData<ArrayList<UserRepositoriesResponseModel>>()

    fun getUserRepositories(owner: String, page: Int) {

        val userRepositoriesRequestModel = UserRepositoriesRequestModel()
        userRepositoriesRequestModel.owner = owner
        userRepositoriesRequestModel.page = page

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = fetchUserRepositoriesUseCase.execute(userRepositoriesRequestModel)
            userRepositoriesLiveData.postValue(response)
        }
    }
}

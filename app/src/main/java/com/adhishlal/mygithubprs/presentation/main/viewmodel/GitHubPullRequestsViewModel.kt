package com.adhishlal.mygithubprs.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adhishlal.mygithubprs.data.model.request.MyRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.request.UserPullRequestModel
import com.adhishlal.mygithubprs.data.model.request.UserRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserResponseModel
import com.adhishlal.mygithubprs.domain.usecases.network.FetchMyRepositoriesUseCase
import com.adhishlal.mygithubprs.domain.usecases.network.FetchUserRepositoriesUseCase
import com.adhishlal.mygithubprs.domain.usecases.network.GetPullRequestUseCase
import com.adhishlal.mygithubprs.domain.usecases.network.GetUserUseCase
import com.adhishlal.mygithubprs.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitHubPullRequestsViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var gitHubUserDataUseCase: GetUserUseCase

    @Inject
    lateinit var getPullRequestUseCase: GetPullRequestUseCase

    @Inject
    lateinit var fetchUserRepositoriesUseCase: FetchUserRepositoriesUseCase

    @Inject
    lateinit var fetchMyRepositoriesUseCase: FetchMyRepositoriesUseCase

    val userDataResponseLiveData = MutableLiveData<UserResponseModel>()
    val prListLiveData = MutableLiveData<ArrayList<UserPullRequestsResponseModel>>()
    val userRepositoriesLiveData = MutableLiveData<ArrayList<UserRepositoriesResponseModel>>()
    val myRepositoriesLiveData = MutableLiveData<ArrayList<MyRepositoriesResponseModel>>()

    fun getUserData(authToken: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = gitHubUserDataUseCase.execute(authToken)
            userDataResponseLiveData.postValue(response)
        }
    }

    fun getPRList(userId: String, repo: String, page: Int) {

        val getPullRequestModel = UserPullRequestModel()
        getPullRequestModel.repo = repo
        getPullRequestModel.userId = userId
        getPullRequestModel.page = page

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = getPullRequestUseCase.execute(getPullRequestModel)
            prListLiveData.postValue(response)
        }
    }

    fun getUserRepositories(owner: String, page: Int) {

        val userRepositoriesRequestModel = UserRepositoriesRequestModel()
        userRepositoriesRequestModel.owner = owner
        userRepositoriesRequestModel.page = page

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = fetchUserRepositoriesUseCase.execute(userRepositoriesRequestModel)
            userRepositoriesLiveData.postValue(response)
        }
    }

    fun getMyRepositories(page: Int) {

        val myRepositoriesRequestModel = MyRepositoriesRequestModel()
        myRepositoriesRequestModel.page = page

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = fetchMyRepositoriesUseCase.execute(myRepositoriesRequestModel)
            myRepositoriesLiveData.postValue(response)
        }
    }

}
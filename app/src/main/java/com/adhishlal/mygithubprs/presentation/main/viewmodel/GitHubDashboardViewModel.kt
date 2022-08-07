package com.adhishlal.mygithubprs.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adhishlal.mygithubprs.data.model.request.MyRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserResponseModel
import com.adhishlal.mygithubprs.domain.usecases.network.FetchMyRepositoriesUseCase
import com.adhishlal.mygithubprs.domain.usecases.network.GetUserUseCase
import com.adhishlal.mygithubprs.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitHubDashboardViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var gitHubUserDataUseCase: GetUserUseCase

    @Inject
    lateinit var fetchMyRepositoriesUseCase: FetchMyRepositoriesUseCase

    val userDataResponseLiveData = MutableLiveData<UserResponseModel>()
    val myRepositoriesLiveData = MutableLiveData<ArrayList<MyRepositoriesResponseModel>>()

    fun getUserData(authToken: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val response = gitHubUserDataUseCase.execute(authToken)
            userDataResponseLiveData.postValue(response)
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
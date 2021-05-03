package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.model.SearchModel
import ir.rahnama.sherapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVieModel @Inject constructor(val repository: Repository):ViewModel() {


    private val _searchModel = MutableLiveData<MutableList<SearchModel>>()
    val searchModel: LiveData<MutableList<SearchModel>>
        get() = _searchModel

    fun getSearchResult(name:String,id:Int){

        viewModelScope.launch(Dispatchers.IO) {

            repository.getSearchResult(name,id).let {

                _searchModel.postValue(it.body())

            }

        }

    }

}
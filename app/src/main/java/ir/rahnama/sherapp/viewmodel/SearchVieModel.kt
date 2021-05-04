package ir.rahnama.sherapp.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.rahnama.sherapp.model.SearchModel
import ir.rahnama.sherapp.repository.Repository
import ir.rahnama.sherapp.utiles.ResultHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVieModel @Inject constructor(val repository: Repository):ViewModel() {


    private val _searchModel = MutableLiveData<ResultHandler<MutableList<SearchModel>>>()
    val searchModel: LiveData<ResultHandler<MutableList<SearchModel>>>
        get() = _searchModel

    private val handler = CoroutineExceptionHandler {
            _, exception ->
        _searchModel.postValue(exception.message?.let { ResultHandler.Error(it) })
    }

    fun getSearchResult(name:String,id:Int){

        viewModelScope.launch(Dispatchers.IO + handler) {

            _searchModel.postValue(ResultHandler.Loading)

            repository.getSearchResult(name,id).let {

                _searchModel.postValue(ResultHandler.Success(it.data!!))

            }

        }

    }

}
package ir.rahnama.sherapp.model.remote

import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService) : CheckApiResponse() {


    suspend fun getPoster() = getResult{apiService.getPoster()}
    suspend fun getSearchResult(name:String,id:Int) = getResult {apiService.getSearchResult(name, id)}
    suspend fun getPoetryName() = getResult { apiService.getPoetryList() }
    suspend fun getSelectionPoetry() =  getResult { apiService.getSelectionPoetry() }
    suspend fun getPoetryBooks(category_id:Int) = getResult { apiService.getPoetryBooks(category_id) }
    suspend fun getBookContent(category_id:Int) = getResult { apiService.getBookContent(category_id) }
    suspend fun getPoemById(poem_id:Int) = getResult { apiService.getPoemById(poem_id) }
    suspend fun getAllNegare() = getResult { apiService.getAllNegare() }
    suspend fun getAllSubscription () = getResult { apiService.getAllSubscription() }
}
package ir.rahnama.sherapp.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.rahnama.sherapp.model.local.*
import ir.rahnama.sherapp.model.remote.ApiClient
import ir.rahnama.sherapp.model.remote.ApiService
import ir.rahnama.sherapp.repository.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton




@Module
@InstallIn(ApplicationComponent::class)
object AppModule {



    @Provides
    fun provideGson()=GsonBuilder().setLenient().create()


    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson):Retrofit = Retrofit.Builder()
        .baseUrl("https://iranswanweb.ir/poem_app/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)


    @Singleton
    @Provides
    fun provideApiClient(apiService: ApiService) = ApiClient(apiService)


    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) = AppDataBase.getInstance(context)


    @Singleton
    @Provides
    fun providePoetryDao(db: AppDataBase) = db.poetryDao

    @Singleton
    @Provides
    fun provideSelectionPoetryDaoDao(db: AppDataBase) = db.selectionPoetryDao

    @Singleton
    @Provides
    fun providePoembodyDao(db: AppDataBase) = db.poemBodyDao

    @Singleton
    @Provides
    fun providebookDao(db: AppDataBase) = db.booksDao

    @Singleton
    @Provides
    fun providebooksContentDao(db: AppDataBase) = db.booksContentDao

    @Singleton
    @Provides
    fun provideSubscription(db: AppDataBase) = db.subscribtion

    @Singleton
    @Provides
    fun provideNegar(db: AppDataBase) = db.negare

    @Singleton
    @Provides
    fun providerepository(poetryDao: PoetryDao,
                          selectionPoetryDao: SelectionPoetryDao
                          , booksDao: BooksDao
                          , poemBodyDao: PoemBodyDao
                          , booksContentDao: BooksContentDao
                          , subscribtionDao : SubscriptionDao
                          , negareDao: NegareDao
                          , apiClient: ApiClient) =Repository(poetryDao, selectionPoetryDao, booksDao, poemBodyDao, booksContentDao, subscribtionDao , negareDao , apiClient )


}
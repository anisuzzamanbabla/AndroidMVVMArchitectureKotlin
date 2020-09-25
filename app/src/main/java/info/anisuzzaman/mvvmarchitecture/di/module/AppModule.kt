package info.anisuzzaman.mvvmarchitecture.di.module

import android.app.Application
import androidx.room.Room
import info.anisuzzaman.mvvmarchitecture.repositories.UserRepository
import info.anisuzzaman.mvvmarchitecture.repositories.api.UserWebservice
import info.anisuzzaman.mvvmarchitecture.repositories.database.MyDatabase
import info.anisuzzaman.mvvmarchitecture.repositories.database.dao.UserDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by anisuzzaman on 24/9/20.
 */

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MyDatabase {
        return Room.databaseBuilder(application!!,
                MyDatabase::class.java, "MyDatabase.db")
                .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MyDatabase): UserDao {
        return database.userDao()!!
    }


    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    @Provides
    @Singleton
    fun provideUserRepository(webservice: UserWebservice, userDao: UserDao, executor: Executor): UserRepository {
        return UserRepository(webservice, userDao, executor)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiWebservice(restAdapter: Retrofit): UserWebservice {
        return restAdapter.create(UserWebservice::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}
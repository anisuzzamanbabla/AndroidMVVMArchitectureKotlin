package info.anisuzzaman.mvvmarchitecture.utils

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by anisuzzaman on 24/9/20.
 */

class LiveDataTestUtil {

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T? {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer: Observer<T?> = object : Observer<T?> {
            override fun onChanged(o: T?) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        Handler(Looper.getMainLooper()).post { liveData.observeForever(observer) }
        latch.await(2, TimeUnit.SECONDS)
        return data[0] as T?
    }
}
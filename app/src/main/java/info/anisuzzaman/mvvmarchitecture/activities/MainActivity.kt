package info.anisuzzaman.mvvmarchitecture.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import info.anisuzzaman.mvvmarchitecture.R
import info.anisuzzaman.mvvmarchitecture.fragments.UserProfileFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by anisuzzaman on 24/9/20.
 */

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @JvmField
    @Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureDagger()
        showFragment(savedInstanceState)
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector!!
    }

    private fun showFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragment = UserProfileFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, null)
                    .commit()
        }
    }

    private fun configureDagger() {
        AndroidInjection.inject(this)
    }
}
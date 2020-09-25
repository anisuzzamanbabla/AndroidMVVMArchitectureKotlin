package info.anisuzzaman.mvvmarchitecture.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import info.anisuzzaman.mvvmarchitecture.R
import info.anisuzzaman.mvvmarchitecture.repositories.database.entity.User
import info.anisuzzaman.mvvmarchitecture.view_models.UserProfileViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by anisuzzaman on 24/9/20.
 */

class UserProfileFragment : Fragment() {
    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    private var viewModel: UserProfileViewModel? = null

    @JvmField
    @BindView(R.id.imageViewUser)
    var imageViewUser: ImageView? = null

    @JvmField
    @BindView(R.id.textViewUserName)
    var textViewUserName: TextView? = null

    @JvmField
    @BindView(R.id.textViewCompany)
    var textViewCompany: TextView? = null

    @JvmField
    @BindView(R.id.textViewWebsite)
    var textViewWebsite: TextView? = null

    @JvmField
    @BindView(R.id.editTextUserLogin)
    var editTextUserLogin: TextView? = null

    @JvmField
    @BindView(R.id.btnLoadData)
    var btnLoadData: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureDagger()
    }

    private fun configureDagger() {
        AndroidSupportInjection.inject(this)
    }

    @OnClick(R.id.btnLoadData)
    fun btnLoadData() {
        configureViewModel()
    }

    private fun configureViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory!!).get(UserProfileViewModel::class.java)
        viewModel!!.init(if (editTextUserLogin!!.text.toString() == "") "anisuzzamanbabla" else editTextUserLogin!!.text.toString())
        viewModel!!.user?.observe(viewLifecycleOwner, { user: User? -> updateUI(user) })
    }

    private fun updateUI(user: User?) {
        if (user != null) {
            Glide.with(this).load(user.avatar_url).apply(RequestOptions.circleCropTransform()).into(imageViewUser!!)
            textViewUserName!!.text = user.name
            textViewCompany!!.text = user.company
            textViewWebsite!!.text = user.blog
        }
    }
}
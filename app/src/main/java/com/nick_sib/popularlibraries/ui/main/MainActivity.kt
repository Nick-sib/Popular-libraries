package com.nick_sib.popularlibraries.ui.main

import android.os.Bundle
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.databinding.ActivityMainBinding
import com.nick_sib.popularlibraries.ui.BackButtonListener
import com.nick_sib.popularlibraries.mvp.presenters.MainPresenter
import com.nick_sib.popularlibraries.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}


//class MainActivity : MvpAppCompatActivity(), MainView {
//
//    private lateinit var binding: ActivityMainBinding
//
//    private val navigatorHolder = App.instance.navigatorHolder
//    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)
//
//    private val presenter: MainPresenter by moxyPresenter {
//        MainPresenter(App.instance.router)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    override fun onResumeFragments() {
//        super.onResumeFragments()
//        navigatorHolder.setNavigator(navigator)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        navigatorHolder.removeNavigator()
//    }
//
//    override fun onBackPressed() {
//        supportFragmentManager.fragments.forEach {
//            if(it is BackButtonListener && it.backPressed()){
//                return
//            }
//        }
//        presenter.backClicked()
//    }
//}
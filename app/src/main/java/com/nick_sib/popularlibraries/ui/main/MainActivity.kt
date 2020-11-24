package com.nick_sib.popularlibraries.ui.main

import android.content.Intent
import android.os.Bundle
import com.nick_sib.popularlibraries.App
import com.nick_sib.popularlibraries.R
import com.nick_sib.popularlibraries.deleteme.Activity4
import com.nick_sib.popularlibraries.navigation.BackButtonListener
import com.nick_sib.popularlibraries.mvp.presenters.MainPresenter
import com.nick_sib.popularlibraries.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator


class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigatorHolder = App.instance.navigatorHolder
    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter(App.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: Современем удалить
        bLesson4.setOnClickListener {
            startActivity(Intent(this, Activity4::class.java))
        }

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
package com.nick_sib.popularlibraries


import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val buttonsCount = 3

    private val buttons by lazy {
        listOf(btnCounter1, btnCounter2, btnCounter3)
    }

    private val presenter by moxyPresenter { MainPresenter(CountersModel(buttonsCount)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onResume() {
        super.onResume()
        buttons.forEach { button ->
            val index = buttons.indexOf(button)
            button.setOnClickListener {
                presenter.counterClick(index)
            }
           button.text = presenter.fillButton(index)
        }

    }


    override fun setButtonText(buttonIndex: Int, text: String) {
        buttons[buttonIndex].text = text
    }
}
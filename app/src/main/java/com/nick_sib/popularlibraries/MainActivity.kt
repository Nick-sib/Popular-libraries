package com.nick_sib.popularlibraries


import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val buttons by lazy {
        listOf(btnCounter1, btnCounter2, btnCounter3)
    }

    private val presenter by moxyPresenter { MainPresenter(CountersModel(buttons.size)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCounter1.setOnClickListener { presenter.counterOneClick() }
        btnCounter2.setOnClickListener { presenter.counterTwoClick() }
        btnCounter3.setOnClickListener { presenter.counterThreeClick() }
    }




    override fun setButtonOneText(text: String) {
        btnCounter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        btnCounter2.text = text
    }

    override fun setButtonThreeText(text: String) {
        btnCounter3.text = text
    }

//    private val presenter by lazy {
//        MainPresenter(this, buttons.size)
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    override fun onResume() {
//        super.onResume()
//
//        buttons.forEach { button ->
//            val index = buttons.indexOf(button)
//            button.setOnClickListener {
//                presenter.counterClick(index)
//            }
//            button.text = presenter.fillButton(index)
//        }
//    }
//
//    override fun setButtonText(index: Int, text: String) {
//        buttons[index].text = text
//    }
}
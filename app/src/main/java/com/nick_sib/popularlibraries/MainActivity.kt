package com.nick_sib.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView  {

    private val buttons by lazy {
        listOf(btnCounter1, btnCounter2, btnCounter3)
    }

    private val presenter by lazy {
        MainPresenter(this, buttons.size)
    }

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

    override fun setButtonText(index: Int, text: String) {
        buttons[index].text = text
    }
}
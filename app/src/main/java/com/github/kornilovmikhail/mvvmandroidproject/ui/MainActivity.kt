package com.github.kornilovmikhail.mvvmandroidproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.kornilovmikhail.mvvmandroidproject.R
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist.NewsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, NewsListFragment())
            .commit()
    }
}

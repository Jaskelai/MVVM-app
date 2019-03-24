package com.github.kornilovmikhail.mvvmandroidproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.kornilovmikhail.mvvmandroidproject.App
import com.github.kornilovmikhail.mvvmandroidproject.R
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.component.DaggerNewsComponent
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataDBModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.DataNetModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.NewsModule
import com.github.kornilovmikhail.mvvmandroidproject.di.screens.module.ViewModelModule
import com.github.kornilovmikhail.mvvmandroidproject.ui.fragment.newslist.NewsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, NewsListFragment.newInstance())
            .commit()
    }
}

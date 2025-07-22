package com.hzz.legacyandroidviews.playground

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hzz.legacyandroidviews.R

class FragmentStarterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_starter)
        if (savedInstanceState == null) {
            val fragmentClassName = intent.getStringExtra(EXTRA_FRAGMENT_CLASS_NAME)
            val args = intent.getBundleExtra(EXTRA_FRAGMENT_ARGS)

            if (fragmentClassName != null) {
                val fragment = Class.forName(fragmentClassName)
                    .asSubclass(Fragment::class.java)
                    .newInstance()
                    .apply {
                        arguments = args
                    }

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()
            } else {
                throw IllegalArgumentException("Fragment class name is missing in Intent")
            }
        }
    }

    companion object {
        private const val EXTRA_FRAGMENT_CLASS_NAME = "extra_fragment_class_name"
        private const val EXTRA_FRAGMENT_ARGS = "extra_fragment_args"

        fun Context.startFragment(fragmentClass: Class<out Fragment>, args: Bundle? = null) {
            val intent = Intent(this, FragmentStarterActivity::class.java).apply {
                putExtra(EXTRA_FRAGMENT_CLASS_NAME, fragmentClass.name)
                putExtra(EXTRA_FRAGMENT_ARGS, args)
            }
            startActivity(intent)
        }
    }

}
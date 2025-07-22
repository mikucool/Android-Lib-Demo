package com.hzz.legacyandroidviews.playground

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hzz.legacyandroidviews.R
import com.hzz.legacyandroidviews.playground.component.DialView

class DialFragment : Fragment(R.layout.fragment_dial) {
    lateinit var mDialContainer: DialView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDialContainer = requireView().findViewById(R.id.dialView)
        mDialContainer.post {
            mDialContainer.setupDialItems(listOf("100", "200", "谢谢参与", "再来一次", "500", "0"))
        }
        requireView().findViewById<Button>(R.id.startButton).apply {
            setOnClickListener {
                mDialContainer.rotateDial(targetIndex = 3, itemCount = 6) {
                    Toast.makeText(requireContext(), "落在 index=3", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}


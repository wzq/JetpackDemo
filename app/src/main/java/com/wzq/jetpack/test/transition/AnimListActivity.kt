package com.wzq.jetpack.test.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewGroupCompat
import androidx.core.view.doOnNextLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.wzq.jetpack.R
import com.wzq.jetpack.databinding.ItemTestAnimBinding
import com.wzq.jetpack.test.transition.util.SpringAddItemAnimator
import com.wzq.jetpack.ui.transcation.Stagger
import kotlinx.coroutines.delay

/**
 * create by wzq on 2020/12/30
 *
 */
class AnimListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_anim_list)
        val list = findViewById<RecyclerView>(R.id.list)
        list.itemAnimator = object : DefaultItemAnimator() {
            override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
                dispatchAddFinished(holder)
                dispatchAddStarting(holder)
                return false
            }
        }
        val stagger = Stagger()
        val adapter = AnimListAdapter()
        list.itemAnimator = SpringAddItemAnimator()
        list.adapter = adapter
        lifecycleScope.launchWhenStarted {
//            delay(500)
//            TransitionManager.beginDelayedTransition(list, stagger)
//            adapter.submitList(fakeData())
        }
        list.doOnNextLayout {
            adapter.submitList(fakeData())
        }
    }


    private fun fakeData(): List<String> {
        return mutableListOf<String>().apply {
            (0..50).forEach {
                add("item $it")
            }
        }
    }
}


class AnimListAdapter : ListAdapter<String, AnimListAdapter.Holder>(Diff()) {

    class Diff : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            ViewGroupCompat.setTransitionGroup(itemView as ViewGroup, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        return Holder(ItemTestAnimBinding.inflate(LayoutInflater.from(parent.context), parent, false).root)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
    }
}
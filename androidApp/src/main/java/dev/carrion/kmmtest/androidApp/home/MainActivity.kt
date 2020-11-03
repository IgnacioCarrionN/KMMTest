package dev.carrion.kmmtest.androidApp.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.carrion.kmmtest.androidApp.R
import dev.carrion.kmmtest.androidApp.detail.DETAIL_FACT_ID
import dev.carrion.kmmtest.androidApp.detail.DetailActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class MainActivity : AppCompatActivity(), DIAware {

    override val di by di(this)

    private val viewModelFactory: MainViewModel.Factory by instance()

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MainAdapter(emptyList()) {
            viewModel.goToFactDetail(it)
        }
        val recyclerView: RecyclerView = findViewById(R.id.recycler)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            this.adapter = adapter
        }

        viewModel.state.observe(this, {
            adapter.updateData(it.map { fact -> fact.text })
        })

        viewModel.navigate.onEach {
            if (it != 0L) {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DETAIL_FACT_ID, it)

                startActivity(intent)
            }
        }.launchIn(lifecycleScope)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            viewModel.fetchNewFact()
        }
    }
}

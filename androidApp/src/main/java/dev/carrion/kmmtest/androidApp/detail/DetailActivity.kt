package dev.carrion.kmmtest.androidApp.detail

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.carrion.kmmtest.androidApp.R
import dev.carrion.repository.FactRepository
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

const val DETAIL_FACT_ID = "detailFactId"

class DetailActivity : AppCompatActivity(), DIAware {

    override val di by di(this)

    private var factId: Long = 0

    private val repository: FactRepository by instance()

    private val viewModel: DetailViewModel by viewModels{
        DetailViewModel.Factory(factId, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        factId = intent.getLongExtra(DETAIL_FACT_ID, 0)

        val idLabel = findViewById<TextView>(R.id.detail_id)
        val textLabel = findViewById<TextView>(R.id.detail_text)
        val createdLabel = findViewById<TextView>(R.id.detail_created)
        val updatedLabel = findViewById<TextView>(R.id.detail_updated)

        viewModel.state.observe(this, {
            idLabel.text = it.id
            textLabel.text = it.text
            createdLabel.text = it.createdAt
            updatedLabel.text = it.updatedAt
        })
    }
}
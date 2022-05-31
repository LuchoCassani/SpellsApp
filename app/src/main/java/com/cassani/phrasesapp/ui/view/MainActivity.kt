package com.cassani.phrasesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.cassani.phrasesapp.databinding.ActivityMainBinding
import com.cassani.phrasesapp.ui.viewModel.SpellViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val spellViewModel : SpellViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spellViewModel.onCreate()


        spellViewModel.spellModel.observe(this, Observer { currentSpell ->
            binding.tvQuote.text = currentSpell.use
            binding.tvAutor.text = currentSpell.spell
        })
        spellViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        binding.viewContainer.setOnClickListener { spellViewModel.randomSpell() }

    }
}
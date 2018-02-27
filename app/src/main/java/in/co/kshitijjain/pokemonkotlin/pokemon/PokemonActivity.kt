package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.R
import `in`.co.kshitijjain.pokemonkotlin.common.base.BaseActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class PokemonActivity : BaseActivity() {

    @Inject lateinit var presenter: PokemonPresenter

    override fun initView() {
        AndroidInjection.inject(this)
        presenter.startPresenting()
    }

    override val layout: Int = R.layout.activity_main

    override fun onDestroy() {
        presenter.stopPresenting()
        super.onDestroy()
    }
}

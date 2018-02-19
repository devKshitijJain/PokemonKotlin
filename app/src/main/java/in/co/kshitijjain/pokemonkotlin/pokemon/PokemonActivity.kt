package `in`.co.kshitijjain.pokemonkotlin.pokemon

import `in`.co.kshitijjain.pokemonkotlin.R
import `in`.co.kshitijjain.pokemonkotlin.common.base.BaseActivity
import javax.inject.Inject

class PokemonActivity : BaseActivity() {

    @Inject lateinit var presenter: PokemonPresenter

    override fun initView() {
        presenter.startPresenting()
    }

    override val layout: Int = R.layout.activity_main

    override fun onDestroy() {
        presenter.stopPresenting()
        super.onDestroy()
    }
}

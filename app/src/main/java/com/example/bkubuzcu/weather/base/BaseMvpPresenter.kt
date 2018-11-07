package com.example.bkubuzcu.weather.base

/**
 * Created by bkubuzcu on 06/11/18.
 * this is BaseMvpPresenter.
 * BasePresenter reproduces from this
 */
interface BaseMvpPresenter<in T : BaseView> {
    /**
     * presenter attach here
     */
    fun attach(baseView: T)

    /**
     * presenter detach here
     */
    fun detach()
}
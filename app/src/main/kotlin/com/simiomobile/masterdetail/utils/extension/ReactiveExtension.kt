package com.simiomobile.masterdetail.utils.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(composite: CompositeDisposable): Boolean = composite.add(this)
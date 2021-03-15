package digital.wup.cardsummary.extensions

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.alsoAddTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> T?.toSingleOrError(): Single<T> {
    return this?.let { Single.just(this) } ?: Single.error(Exception())
}

fun <T> Observable<List<T>>.takeElements(number: Int): Observable<List<T>> {
    return switchMap { list -> Observable.just(list.subList(0, number)) }
}
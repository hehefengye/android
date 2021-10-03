package com.example.hiotmvp.ui.base;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BasePresenter<V extends BaseView> {
    protected V baseView;

    public void setView(V baseView) {
        this.baseView = baseView;
    }

    public void detach() {
        if (this.baseView != null) {
            this.baseView = null;
        }
    }

    public boolean isDetach() {
        return this.baseView == null;
    }

    public <T> void subscribe(Observable<T> observable, final CallBackResonse<T> callBackResponse) {

        observable.unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        callBackResponse.onNext(t);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public abstract class CallBackResonse<T> {

        public abstract void onNext(T t);

    }


}

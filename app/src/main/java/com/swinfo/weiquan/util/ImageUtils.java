package com.swinfo.weiquan.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by redbird on 2017/5/19.
 */

public class ImageUtils {

    /**
     * 压缩单张图片 Listener 方式
     */
    public static void compressWithLs(final Context context, final ImageView imageView, String imagePath) {
        Luban.get(context)
                .load(new File(imagePath))
                .putGear(Luban.THIRD_GEAR)
                .setFilename(System.currentTimeMillis() + "")
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Toast.makeText(context, "I'm start", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i("path", file.getAbsolutePath());
                        imageView.setImageURI(Uri.fromFile(file));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "I'm onError", Toast.LENGTH_SHORT).show();
                    }
                }).launch();
    }

    /**
     * 压缩单张图片 RxJava 方式
     */
    public static void compressWithRx(final Context context,final ImageView imageView, String imagePath) {
        Luban.get(context)
                .load(new File(imagePath))
                .putGear(Luban.THIRD_GEAR)
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends File>>() {
                    @Override
                    public Observable<? extends File> call(Throwable throwable) {
                        return Observable.empty();
                    }
                })
                .subscribe(new Action1<File>() {
                    @Override
                    public void call(File file) {
                        imageView.setImageURI(Uri.fromFile(file));
                    }
                });
    }
}

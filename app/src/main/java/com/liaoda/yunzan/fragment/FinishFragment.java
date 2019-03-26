package com.liaoda.yunzan.fragment;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liaoda.yunzan.Glide4Engine;
import com.liaoda.yunzan.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FinishFragment extends Fragment {

    private View cv;
    private static final int REQUEST_CODE_CHOOSE = 23;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        cv = inflater.inflate(R.layout.finish_fragment, container, false);
        return cv;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cv.findViewById(R.id.tv_add_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Boolean aBoolean) {
                                if (aBoolean) {

                                    Matisse.from(getActivity())
                                            .choose(MimeType.ofVideo(), false)
                                            .countable(true)
                                            .capture(true)
                                            .captureStrategy(
                                                    new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                            .thumbnailScale(0.85f)
                                            .imageEngine(new Glide4Engine())    // for glide-V4
                                            .setOnSelectedListener(new OnSelectedListener() {
                                                @Override
                                                public void onSelected(
                                                        @NonNull List<Uri> uriList, @NonNull List<String> pathList) {

                                                }
                                            })
                                            .forResult(REQUEST_CODE_CHOOSE);
                                } else {
                                    Toast.makeText(getActivity(), "暂无权限", Toast.LENGTH_LONG)
                                            .show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
}

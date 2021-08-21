package com.example.epidemicbigdata.ui.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.epidemicbigdata.R;
import com.example.epidemicbigdata.util.LoadingUtil;
import com.example.epidemicbigdata.util.WebUtil;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import org.jetbrains.annotations.NotNull;

public class NewsOnBrowserFragment extends Fragment {

    private WebView newsBrowser;
    private Button returnNewsList;
    private SwipeRefreshLayout newsBrowserSwipeRefreshLayout;
    private LoadingDialog ld;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_newsonbrowser, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ld = new LoadingUtil().initLoadingObj(getContext());
        returnNewsList = view.findViewById(R.id.returnNewsList);
        newsBrowser = view.findViewById(R.id.newsBrowser);

        newsBrowserSwipeRefreshLayout = view.findViewById(R.id.newsBrowserSwiperefreshlayout);
        newsBrowserSwipeRefreshLayout.setColorSchemeResources(R.color.AppBlue);
        newsBrowser.getSettings().setJavaScriptEnabled(true);

        if (getArguments() != null) {
            if(new WebUtil().isConnected(getContext())) {
                ld.loadFailed();
                Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
            } else {
                newsBrowser.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        ld.loadSuccess();
                    }
                });
            }
            newsBrowser.loadUrl(getArguments().getString("newsUrl"));
            returnNewsList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.newsTarget, NewsFragment.class, null).commit();
                }
            });
        }
        newsBrowserSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsBrowser.loadUrl(getArguments().getString("newsUrl"));
                newsBrowserSwipeRefreshLayout.setRefreshing(false);
                if(new WebUtil().isConnected(getContext())) {
                    Toast.makeText(getContext(), "网络似乎没有连接...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "刷新完毕！", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ld.close();
    }
}

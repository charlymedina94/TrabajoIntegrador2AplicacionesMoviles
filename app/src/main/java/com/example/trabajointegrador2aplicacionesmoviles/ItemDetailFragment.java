package com.example.trabajointegrador2aplicacionesmoviles;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.example.trabajointegrador2aplicacionesmoviles.dummy.DummyContent;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;




public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private DummyContent.DummyItem mItem;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            Activity activity = this.getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (mItem != null) {
            WebView webview= ((WebView) rootView.findViewById(R.id.item_detail));
            webview.setWebViewClient(new WebViewClient());
            webview.loadUrl(mItem.website_url);
        }
        return rootView;
    }
}
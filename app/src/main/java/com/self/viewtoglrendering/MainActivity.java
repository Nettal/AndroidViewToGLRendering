package com.self.viewtoglrendering;

import android.opengl.GLSurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.self.viewtoglrendering.cuberenerer.CubeGLRenderer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_main);

        //created a CanvasBuffer(Framebuffer) and a ogl surface
        ViewToGLRenderer glRenderer = new CubeGLRenderer(this);

        //rending ogl surface
        GLSurfaceView mGLSurfaceView = findViewById(R.id.gl_surface_view);
        mGLSurfaceView.setEGLContextClientVersion(2);
        mGLSurfaceView.setRenderer(glRenderer);

        //let viewgroup rendering to CanvasBuffer(Framebuffer)
        GLLinearLayout mGLLinearLayout = findViewById(R.id.gl_layout);
        //set rending target
        mGLLinearLayout.setViewToGLRenderer(glRenderer);

        WebView mWebView = findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                Toast.makeText(MainActivity.this, "onPageFinished,Scroll to show", Toast.LENGTH_SHORT).show();
                super.onPageFinished(view, url);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("https://www.android.com/");
    }
}

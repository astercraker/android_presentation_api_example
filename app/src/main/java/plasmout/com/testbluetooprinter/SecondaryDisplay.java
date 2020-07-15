package plasmout.com.testbluetooprinter;

import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class SecondaryDisplay extends Presentation {
    public SecondaryDisplay(Context outerContext, Display display, Uri video, Uri secondVideo) {
        super(outerContext, display);
        mContext = outerContext;
        mVideo = video;
        mSecondVideo = secondVideo;
    }

    VideoView mVideoView;
    Context mContext;
    Uri mVideo;
    Uri mSecondVideo;
    boolean mVideoCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("VideoPresentation", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_display);
        // Get the resources for the context of the presentation.
        // Notice that we are getting the resources from the context of the presentation.
        Resources r = getContext().getResources();

        // Inflate the layout.
        setContentView(R.layout.secondary_display);

        mVideoView = (VideoView) findViewById(R.id.videoView1);
        mVideoView.setVideoURI(mVideo);


        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(!mVideoCompleted){
                    updateVideo(mSecondVideo);
                }
                mVideoCompleted = true;
                mVideoView.start();
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });

    }

    private void increaseVolume() {
        AudioManager audioManager = ((AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE));
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currentVolume > 0) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,  currentVolume + 1, 0);
        }
    }

    @Override
    protected void onStart() {
        Log.e("VideoPresentation", "onStart");
        // Be sure to call the super class.
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.e("VideoPresentation", "onStop");
        // Be sure to call the super class.
        super.onStop();
    }

    @Override
    public void onDetachedFromWindow() {
        Log.e("VideoPresentation", "onDetachedFromWindow");
        // Be sure to call the super class.
        super.onDetachedFromWindow();
    }

    public void stopVideoView() {
        mVideoView.stopPlayback();
    }

    public void updateVideo(Uri video) {
        mVideo = video;
        mVideoView.requestFocus();
        mVideoView.setVideoURI(mVideo);
    }

    public VideoView getVideoView() {
        return mVideoView;
    }
}

package iot.b19060630.mybill;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private MusicPlayerService musicPlayerService;
    private boolean isMusicPlayerServiceBound = false;
    private static final String TAG = "MusicPlayerActivity";


    private ImageView albumImageView;
    private TextView songTextView;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

//        bindMusicPlayerService();
        albumImageView = findViewById(R.id.album_image);
        songTextView = findViewById(R.id.song_text);
        playButton = findViewById(R.id.play_button);
        pauseButton = findViewById(R.id.pause_button);
        stopButton = findViewById(R.id.stop_button);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bindMusicPlayerService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindMusicPlayerService();
    }

    private void bindMusicPlayerService() {
        Intent intent = new Intent(this, MusicPlayerService.class);
        bindService(intent, musicPlayerServiceConnection, Context.BIND_AUTO_CREATE);
        System.out.println("bindMusicPlayerService我被调用了");
    }

    private void unbindMusicPlayerService() {
        if (isMusicPlayerServiceBound) {
            unbindService(musicPlayerServiceConnection);
            isMusicPlayerServiceBound = false;
        }
    }

    private ServiceConnection musicPlayerServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicPlayerService.MusicPlayerBinder binder = (MusicPlayerService.MusicPlayerBinder) iBinder;
            musicPlayerService = binder.getService();
            isMusicPlayerServiceBound = true;

            System.out.println("onServiceConnected被调用了");
            Log.d(TAG, "Service connected. musicPlayerService is not null: " + (musicPlayerService != null));
        }


        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isMusicPlayerServiceBound = false;
        }
    };

    @Override
    public void onClick(View view) {

        System.out.println(isMusicPlayerServiceBound);
        switch (view.getId()) {
            case R.id.play_button:
                if (isMusicPlayerServiceBound && musicPlayerService != null) {
                    musicPlayerService.playMusic();
                }
                break;
            case R.id.pause_button:
                if (isMusicPlayerServiceBound && musicPlayerService != null) {
                    musicPlayerService.pauseMusic();
                }
                break;
            case R.id.stop_button:
                if (isMusicPlayerServiceBound && musicPlayerService != null) {
                    musicPlayerService.stopMusic();
                }
                break;
        }

    }
}

package remmcal_apps.repavicii;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private Button b;
    private MediaPlayer mp;
    private int pause;
    private SoundPool sp1, sp2, sp3;
    private int uno, dos, tres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crearSoundPool();



    //b= (Button) findViewById(R.id.button);
    //mp= MediaPlayer.create(this, R.raw.song);

     /*   b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying())
                {
                    mp.pause();
                    b.setBackgroundResource(R.drawable.play);
                    Toast.makeText(MainActivity.this, "Pausa", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mp.start();
                    b.setBackgroundResource(R.drawable.pause);
                    Toast.makeText(MainActivity.this, "Play", Toast.LENGTH_SHORT).show();
                }
            }
        });*/



    }



    public void Play(View v)
    {
        if(mp==null)
        {
            mp= MediaPlayer.create(this,R.raw.song);
            mp.start();
            Toast.makeText(this,"Play",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(!mp.isPlaying())
            {
                mp.seekTo(pause);
                mp.start();
                Toast.makeText(this, "Reanudando", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void Pausa(View v)
    {
        if(mp!=null)
        {
            mp.pause();
            pause= mp.getCurrentPosition();
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }

    }


    public void Stop(View v)
    {
        mp.stop();
        mp=null;
        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
    }






    ///codigo para los efectos

    public  void uno(View view)
    {
        sp1.play(uno,1,1,0,0,1);
    }

    public void dos(View view)
    {
        sp2.play(dos,1,1,0,0,1);
    }

    public void tres(View view)
    {
        sp3.play(tres,1,1,0,0,1);
    }



    public void crearSoundPool()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //Crear la nueva version de soundpool
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sp1 = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
            sp2 = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
            sp3 = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();

            cargarSoundPool();
        } else {
               // CREAR la antigua version de soundpool
            sp1=new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            sp2=new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            sp3=new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
            cargarSoundPool();
        }
        }
    public void cargarSoundPool()
    {
        uno=sp1.load(this, R.raw.efecto1, 0);
        dos=sp2.load(this, R.raw.efecto2, 0);
        tres=sp3.load(this, R.raw.efecto3, 0);
    }
}

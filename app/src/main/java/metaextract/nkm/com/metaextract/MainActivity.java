package metaextract.nkm.com.metaextract;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class MainActivity extends Activity {
    ImageView album_art;
    TextView album, artist, genre;
    MediaMetadataRetriever metaRetriver;
    byte[] art;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInit();
        // Ablum_art reterival code //

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "A");
        String a = file.getPath()+"/a.mp3";
        metaRetriver = new MediaMetadataRetriever();
        metaRetriver.setDataSource(a);
        try {
            art = metaRetriver.getEmbeddedPicture();
            Bitmap songImage = BitmapFactory.decodeByteArray(art, 0, art.length);
            album_art.setImageBitmap(songImage);
            album.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
            artist.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
            genre.setText(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE));
        } catch (Exception e) {
            album_art.setBackgroundColor(Color.GRAY);
            album.setText("Unknown Album");
            artist.setText("Unknown Artist");
            genre.setText("Unknown Genre");
        }
    }
    // Fetch Id's form xml
    public void getInit() {
        album_art = (ImageView) findViewById(R.id.album_art);
        album = (TextView) findViewById(R.id.Album);
        artist = (TextView) findViewById(R.id.artist_name);
        genre = (TextView) findViewById(R.id.genre);
    }
}



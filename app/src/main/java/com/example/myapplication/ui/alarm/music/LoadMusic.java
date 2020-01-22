package com.example.myapplication.ui.alarm.music;

import java.util.ArrayList;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;


public class LoadMusic {

    private Context                 context;
    private ArrayList<Music>         musicList;

    public LoadMusic(Context context){

        this.context = context;
        musicList = new ArrayList<Music>();
        getMusicInfo();
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void getMusicInfo(){
        String[] proj = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.MIME_TYPE
        };

        Cursor musicCursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

        if (musicCursor != null && musicCursor.moveToFirst()){
            String musicID;
            String albumID;
            String musicTitle;
            String singer;
            String mime;

            int musicIDCol = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int albumIDCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
            int musicTitleCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int singerCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int mimeCol = musicCursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE);
            do {


                musicID = musicCursor.getString(musicIDCol);
                albumID = musicCursor.getString(albumIDCol);
                musicTitle = musicCursor.getString(musicTitleCol);
                singer = musicCursor.getString(singerCol);
                mime = musicCursor.getString(mimeCol);

                if(mime.equals("audio/mpeg")){

                    Music music = new Music();
                    String uri = getAlbumArt(Integer.parseInt(albumID)).toString();
                    music.setAlbumUri(uri);

                    Uri musicURI = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, "" + musicID);

                    music.setMusicUri(musicURI);
                    music.setMusicTitle(musicTitle);
                    music.setSinger(singer);
                    musicList.add(music);
                }

            }while (musicCursor.moveToNext());
        }
        musicCursor.close();
        return;
    }

    public Uri getAlbumArt(long albumId)
    {
        Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri uri = ContentUris.withAppendedId(artworkUri, albumId);
        return uri;
    }
}


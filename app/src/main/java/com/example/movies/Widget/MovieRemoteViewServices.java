package com.example.movies.Widget;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Binder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.movies.AppExecutor;
import com.example.movies.Database.Database_movies;
import com.example.movies.Database.Favourit_movies;
import com.example.movies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRemoteViewServices extends RemoteViewsService {
   public Database_movies db;
    MoviewRemoteViewsFactory moviewRemoteViewsFactory;
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {


//        db = Database_movies.getInstance(getApplicationContext());
//
//        AppExecutor.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                final List<Favourit_movies> list =   db.taskDao().loadAllFavourite();
//                moviewRemoteViewsFactory=new MoviewRemoteViewsFactory(getApplicationContext(),list);
//            }
//        });
       return new MoviewRemoteViewsFactory(getApplicationContext());
    }

   public  class MoviewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{
        Context mcontext;
       List<Favourit_movies> data;
       List<Favourit_movies> list;
       String Tag="vvb";
       String Tag2="bbn";
       public MoviewRemoteViewsFactory(Context mcontext) {
           this.mcontext = mcontext;
          // this.data=taskEntries;
       }

       @Override
        public void onCreate() {
           db = Database_movies.getInstance(getApplicationContext());

           AppExecutor.getInstance().diskIO().execute(new Runnable() {
               @Override
               public void run() {
                   data =   db.taskDao().loadAllFavourite();

               }
           });
        }

        @Override
        public void onDataSetChanged() {
            final long identityToken = Binder.clearCallingIdentity();
            Binder.restoreCallingIdentity(identityToken);

        }
        @Override
        public void onDestroy() {

          data.clear();
        }
        @Override
        public int getCount() {
            if (data == null) {
                return 0;
            }else {
                return data.size(); }
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (data == null || data.size() == 0)
            {
                Log.d(Tag,"data is empty");
                return null;
            }else {
                Log.d(Tag2,data.toString());
                RemoteViews views = new RemoteViews(mcontext.getPackageName(), R.layout.widget_text);
                views.setTextViewText(R.id.WidgetTitle, data.get(position).getTitle());
                return views;
            }
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }


}

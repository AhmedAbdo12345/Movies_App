package com.example.movies.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.movies.Activities.MainActivity;
import com.example.movies.Adapters.FavouritAdapter;
import com.example.movies.AppExecutor;
import com.example.movies.Database.Database_movies;
import com.example.movies.Database.Favourit_movies;
import com.example.movies.R;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Implementation of App Widget functionality.
 */
public class MovieWidgetProvider extends AppWidgetProvider {
    static Database_movies db,db2;
    RemoteViews rv;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        // Construct the RemoteViews object
         final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.movie_widget_provider);

//        db = Database_movies.getInstance(context);
//
//        AppExecutor.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                final List<Favourit_movies> list =   db.taskDao().loadAllFavourite();
//                views.setTextViewText(R.id.WidgetTitle, list.get(0).getTitle());
//
//            }
//        });
//
//        /*------------My work- to Open App when Click to Widget----------*/
//        Intent intent=new Intent(context, MainActivity.class);
//        PendingIntent pendingIntent=PendingIntent.getActivity(context,0, intent,0);
//        views.setOnClickPendingIntent(R.id.WidgetTitle,pendingIntent);
//        /*-------------------------------------------*/

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
           // updateAppWidget(context, appWidgetManager, appWidgetId);

            Intent intent = new Intent(context, MovieRemoteViewServices.class);
            // Add the app widget ID to the intent extras.

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);

            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            // Instantiate the RemoteViews object for the app widget layout.
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
         final    RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.movie_widget_provider);


            rv.setRemoteAdapter(appWidgetId, R.id.appwidget_list, intent);

            // Trigger listview item click
            Intent clicked = new Intent(context, MainActivity.class);
            clicked.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, clicked, 0);
            // the user Click on Widget container to open App
            rv.setOnClickPendingIntent(R.id.container, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}



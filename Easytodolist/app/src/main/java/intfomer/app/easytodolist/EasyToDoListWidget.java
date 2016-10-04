package intfomer.app.easytodolist;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.RemoteViews;
import android.widget.Toast;


public class EasyToDoListWidget extends AppWidgetProvider {

    public static final String TOAST_ACTION = "com.example.android.stackwidget.TOAST_ACTION";
    public static final String EXTRA_ITEM = "com.example.android.stackwidget.EXTRA_ITEM";

    private EasyToDoListAdapter mListAdapter;
    private RecyclerView mToDoList;


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            Intent intent = new Intent(context, EasyToDoListWidgetService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.easytodolistwidget);
            remoteViews.setRemoteAdapter(appWidgetId, R.id.listview, intent);
            remoteViews.setEmptyView(R.id.listview, R.id.empty_view);

            Intent inputIntent = new Intent(context, EasyToDoListWidget.class);
            inputIntent.setAction(EasyToDoListConstant.ACTION_INPUT_TODO);
            inputIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent inputPendingIntent = PendingIntent.getBroadcast(context, 0, inputIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.inputbutton, inputPendingIntent);

            Intent toastIntent = new Intent(context, EasyToDoListWidget.class);
            toastIntent.setAction(EasyToDoListWidget.TOAST_ACTION);
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.listview, toastPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
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

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);

        String action = intent.getAction();
        if (action.equals(TOAST_ACTION)){
            int appWidget = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
            Toast.makeText(context, "Touched View1 " + viewIndex, Toast.LENGTH_LONG).show();
        }else if (action.equals(EasyToDoListConstant.ACTION_CHECK)){
            Toast.makeText(context, "Touched View ", Toast.LENGTH_LONG).show();
        }else if (action.equals(EasyToDoListConstant.ACTION_PRIORITY)){
            Toast.makeText(context, "Touched View ", Toast.LENGTH_LONG).show();
        }else if (action.equals(EasyToDoListConstant.ACTION_DELETE)){
            Toast.makeText(context, "Touched View ", Toast.LENGTH_LONG).show();
        }else if (action.equals(EasyToDoListConstant.ACTION_INPUT_TODO)){
            Toast.makeText(context, "Touched View11 ", Toast.LENGTH_LONG).show();
        }
        int appWidget = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        int viewIndex = intent.getIntExtra(EXTRA_ITEM, 0);
        mgr.notifyAppWidgetViewDataChanged(appWidget, viewIndex);
        super.onReceive(context, intent);
    }
}


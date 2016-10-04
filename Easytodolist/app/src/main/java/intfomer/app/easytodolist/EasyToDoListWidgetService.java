package intfomer.app.easytodolist;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;


public class EasyToDoListWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new EasyToDoListViewsFactory(this.getApplicationContext(), intent);
    }
}

class EasyToDoListViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private static final int mCount = 10;
    private Context mContext;
    private int mAppWidgetId;
    private List<EasyToDoListWidgetItem> mListItems = new ArrayList<>();

    public EasyToDoListViewsFactory(Context context, Intent intent){
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        for(int i = 0 ; i < mCount; i++){
            mListItems.add(new EasyToDoListWidgetItem(i + "todo"));
        }
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.todolistview);
        remoteViews.setTextViewText(R.id.todocontents, mListItems.get(position).todo);

        remoteViews.setImageViewResource(R.id.checktodo, R.drawable.checked);

        Bundle extras = new Bundle();
        extras.putInt(EasyToDoListWidget.EXTRA_ITEM, position);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(extras);
        remoteViews.setOnClickFillInIntent(R.id.todocontents, fillIntent);
        remoteViews.setOnClickFillInIntent(R.id.checktodo, fillIntent);
        remoteViews.setOnClickFillInIntent(R.id.prioritytodo, fillIntent);
        remoteViews.setOnClickFillInIntent(R.id.deletetodo, fillIntent);

        return remoteViews;
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public void onDestroy() {
        mListItems.clear();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
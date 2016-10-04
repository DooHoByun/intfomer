package intfomer.app.easytodolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class EasyToDoListActivity extends AppCompatActivity {


    private EasyToDoListAdapter mListAdapter;
    private RecyclerView mToDoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

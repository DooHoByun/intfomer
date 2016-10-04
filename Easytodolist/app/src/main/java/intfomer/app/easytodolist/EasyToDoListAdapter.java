package intfomer.app.easytodolist;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EasyToDoListAdapter extends RecyclerView.Adapter<EasyToDoListAdapter.EasyToDoListViewHolder>{

    private Context mContext;

    public EasyToDoListAdapter(Context context){
        mContext = context;
    }

    @Override
    public EasyToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todolistview, null);

        return new EasyToDoListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EasyToDoListViewHolder holder, int position) {
        holder.mCheckToDo.setChecked(true);
        holder.mToDo.setText("ToDo List");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class EasyToDoListViewHolder extends RecyclerView.ViewHolder{

        public CheckBox mCheckToDo;
        public TextView mToDo;
        public Button mDeleteToDo;
        public Button mPriorityToDo;

        public EasyToDoListViewHolder(View itemView) {
            super(itemView);

            mCheckToDo = (CheckBox) itemView.findViewById(R.id.checktodo);
            mToDo = (TextView) itemView.findViewById(R.id.checktodo);
            mDeleteToDo = (Button) itemView.findViewById(R.id.deletetodo);
            mPriorityToDo = (Button) itemView.findViewById(R.id.prioritytodo);
        }
    }

}

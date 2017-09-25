package com.nimbus.ejuris.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nimbus.ejuris.ProcessDetailActiviy;
import com.nimbus.ejuris.R;
import com.nimbus.ejuris.model.Process;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by nimbus on 22/09/17.
 */
public class ProcessAdapter extends RealmBaseAdapter<Process> implements ListAdapter{
    Context context;
    RealmResults<Process> processes;
    Realm realm;

    public ProcessAdapter(Context context, Realm realm, RealmResults<Process> realmResults){
        super(realmResults);
        this.realm = realm;
    }
    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        CustomViewHolder holder;

        if( convertView == null ){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proccess, parent, false);
            holder = new CustomViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.proccess_name);
            holder.ProcessCompleted = (ImageView) convertView.findViewById(R.id.proccess_completed);

            convertView.setTag( holder );
        }
        else{
            holder = (CustomViewHolder) convertView.getTag();
        }

        final Process p = adapterData.get(position);
        holder.Name.setText(p.getName());
        if(p.isProcess_completed()){
            holder.ProcessCompleted.setImageResource(R.drawable.check_yes);
        } else {
            holder.ProcessCompleted.setImageResource(R.drawable.check_false);
        }


        holder.Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(parent.getContext(), ProcessDetailActiviy.class);
                it.putExtra(Process.ID, p.getId());
                parent.getContext().startActivity(it);
            }
        });
        return convertView;
    }


    private static class CustomViewHolder{
        TextView Name;
        ImageView ProcessCompleted;
    }
}

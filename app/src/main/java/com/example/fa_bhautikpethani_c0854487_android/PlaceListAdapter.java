package com.example.fa_bhautikpethani_c0854487_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fa_bhautikpethani_c0854487_android.Model.Place;
import com.example.fa_bhautikpethani_c0854487_android.services.DBHelper;

import java.util.List;

public class PlaceListAdapter extends BaseAdapter {
    Context context;
    List<Place> places;
    LayoutInflater inflater;
    DBHelper dbHelper;

    public PlaceListAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
        inflater = (LayoutInflater.from(context));
        dbHelper = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return places.get(position).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null)
        {
            view=inflater.inflate(R.layout.place_list_adapter,null);
            holder=new ViewHolder();
            holder.btnCheck=view.findViewById(R.id.btnCheck);
            holder.txtPlaceAddress=view.findViewById(R.id.lblPlaceAddress);
            holder.btnView=view.findViewById(R.id.btnViewPlace);
            holder.btnEdit=view.findViewById(R.id.btnUpdatePlace);
            holder.btnRemove=view.findViewById(R.id.btnDeletePlace);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtPlaceAddress.setText(places.get(i).getPlaceAddress());
        if(places.get(i).getStatus() == 1){
            holder.btnCheck.setChecked(true);
        }else{
            holder.btnCheck.setChecked(false);
        }

        holder.btnCheck.setOnClickListener(v -> {
            Place place = places.get(i);
            place.setStatus((places.get(i).getStatus() == 1) ? 0 : 1);
            if(dbHelper.updatePlace(place)){
                Toast toast = Toast.makeText(context,
                        place.getPlaceAddress()+" has been updated to place list.",
                        Toast.LENGTH_LONG);

                toast.show();
                Intent intent=new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }else{
                Toast toast = Toast.makeText(context,
                        place.getPlaceAddress()+" couldn't updated to place list.",
                        Toast.LENGTH_LONG);

                toast.show();
            }
        });

        holder.btnRemove.setOnClickListener(v -> {
            //taskRoomDB.taskDAO().delete(tasks.get(i));
            Intent intent=new Intent(context,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            context.startActivity(intent);
        });

        holder.btnView.setOnClickListener(v -> {
//            Intent intent=new Intent(context, ViewTaskActivity.class);
//            intent.putExtra("taskId", tasks.get(i).getId());
//            context.startActivity(intent);
        });

        holder.btnEdit.setOnClickListener(v -> {
//            Intent intent=new Intent(context, ViewTaskActivity.class);
//            intent.putExtra("taskId", tasks.get(i).getId());
//            context.startActivity(intent);
        });


        return view;
    }

    static class ViewHolder{
        private ImageButton btnView, btnEdit, btnRemove;
        private CheckBox btnCheck;
        private TextView txtPlaceAddress;
    }
}
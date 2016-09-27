package com.mangodevelopers.events;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by AmanTugnawat on 24-09-16.
 */
public class CustomListAdapter extends BaseAdapter {

    ArrayList<Event> eventsList;
    static LayoutInflater inflater=null;
    //ImageLoader imageLoader;

    public CustomListAdapter(Context context, ArrayList<Event> eventsList) {
        this.eventsList =eventsList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //imageLoader=new ImageLoader(context);
    }

    public int getCount() {
        return eventsList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        View vi=convertView;

        if(convertView==null&&inflater!=null)
            vi = inflater.inflate(R.layout.list_item_event, null);
        final Event event= eventsList.get(position);
        TextView cat=(TextView)vi.findViewById(R.id.eventCat);
        TextView name=(TextView)vi.findViewById(R.id.eventName);
        final CheckBox fav=(CheckBox) vi.findViewById(R.id.favCheckBox);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.isChecked()){
                    try{
                    fav.setText("Added to favourites");
                    }catch (Exception e){
                        Log.e("Database Error",e.toString());
                    }

                }else
                    try{
                        fav.setText("Add to favourites");
                    }catch (Exception e){
                        Log.e("Database Error",e.toString());
                    }
            }
        });
        ImageButton imageButton= (ImageButton) vi.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventDetails=new Intent(parent.getContext(),EventDetailsActivity.class);
                eventDetails.putExtra("id",event.getId());
                eventDetails.putExtra("name",event.getName());
                eventDetails.putExtra("cat",event.getName());
                eventDetails.putExtra("image",event.getImage());
                eventDetails.putExtra("des",event.getDescription());
                eventDetails.putExtra("exp",event.getExperience());
                parent.getContext().startActivity(eventDetails);
            }
        });
        ImageView image=(ImageView)vi.findViewById(R.id.eventImage);
        name.setText(event.getName());
        cat.setText(event.getCategory());
        //imageLoader.DisplayImage(event.getImage(), image);
        return vi;
    }
}
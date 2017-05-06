package com.example.harishram.fars;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by swati on 5/1/2017.
 */

public class AdvertisementAdapter  extends ArrayAdapter<Advertisement>  {
    private final Context context;
    private ArrayList<Advertisement> advertisements;
    private String date;
    public AdvertisementAdapter(Context context, int textViewResourceId, ArrayList<Advertisement> advertisements) {
        super(context, textViewResourceId, advertisements);
        this.context = context;
        this.advertisements = advertisements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        //Student student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_view_advertisements_list_template,null);
        }
        Advertisement advertisement = advertisements.get(position);
        if(advertisement != null) {
            // Lookup view for data population
            final TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView adType = (TextView) convertView.findViewById(R.id.adType);
            TextView apType = (TextView) convertView.findViewById(R.id.apType);
            TextView location = (TextView) convertView.findViewById(R.id.location);
            TextView gender = (TextView) convertView.findViewById(R.id.gender);
            TextView utilities = (TextView) convertView.findViewById(R.id.utilities);
            TextView availability = (TextView) convertView.findViewById(R.id.availability);
            TextView description = (TextView) convertView.findViewById(R.id.description);
            TextView reason = (TextView) convertView.findViewById(R.id.reason);
            TextView comment = (TextView) convertView.findViewById(R.id.comment);
            final Button block = (Button) convertView.findViewById(R.id.block);
            final Button unBlock = (Button) convertView.findViewById(R.id.unBlock);
            block.setTag(position);
            unBlock.setTag(position);
            // Populate the data into the template view using the data object
            name.setText("Name: "+advertisement.Name);
            adType.setText("AdType: "+advertisement.AdType);
            apType.setText("ApType: "+advertisement.ApType);
            location.setText("Location: "+advertisement.Location);
            gender.setText("Gender: "+advertisement.Gender);
            utilities.setText("Utilities: "+advertisement.Utilities);
            availability.setText("Availability: "+advertisement.Availability);
            description.setText("Description: "+advertisement.Description);
            reason.setText("Reason: "+advertisement.reason);
            comment.setText("Description: "+advertisement.comment);


            block.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    Advertisement advertisement = getItem(position);
                    DatabaseReference updateAdvertisementStatus = FirebaseDatabase.getInstance().getReference("Advertisements/"+advertisement.Date+"/"+advertisement.AdvertisementID);
                    Map<String, Object> updateStatus = new HashMap<String, Object>();
                    updateStatus.put("status","Blocked");
                    updateAdvertisementStatus.updateChildren(updateStatus);
                    Intent i = new Intent(getContext(),admin_view_report.class);
                    context.startActivity(i);
                }
            });

            unBlock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    Advertisement advertisement = getItem(position);
                    DatabaseReference updateAdvertisementStatus = FirebaseDatabase.getInstance().getReference("Advertisements/"+advertisement.Date+"/"+advertisement.AdvertisementID);
                    Map<String, Object> updateStatus = new HashMap<String, Object>();
                    updateStatus.put("status","Active");
                    updateAdvertisementStatus.updateChildren(updateStatus);
                    Intent i = new Intent(getContext(),admin_view_report.class);
                    context.startActivity(i);
                }
            });
        }
        // Return the completed view to render on screen
        return convertView;
    }
}

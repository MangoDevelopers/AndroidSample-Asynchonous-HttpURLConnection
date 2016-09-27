package com.mangodevelopers.events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by AmanTugnawat on 24-09-16.
 */
public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView name,description,ctc,exp;
    ImageView image;
    Button link,stat;
    FloatingActionButton share;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails);
        name=(TextView)findViewById(R.id.name);
        description=(TextView)findViewById(R.id.description);
        ctc=(TextView)findViewById(R.id.ctc);
        exp=(TextView)findViewById(R.id.exp);
        Intent intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("des"));
        exp.setText(intent.getStringExtra("exp"));

        link=(Button)findViewById(R.id.linkbutton);
        stat=(Button)findViewById(R.id.statButton);
        share=(FloatingActionButton) findViewById(R.id.shareButton);
        share.setOnClickListener(this);
        stat.setOnClickListener(this);
        link.setOnClickListener(this);

        image=(ImageView) findViewById(R.id.image);
        //ImageLoader loader=new ImageLoader(getApplicationContext());
        //loader.DisplayImage(intent.getStringExtra("image"),image);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linkbutton:
                Uri uri = Uri.parse(getIntent().getStringExtra("image")); // Open Image link as no other link provided
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.shareButton:
                try { Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, getIntent().getStringExtra("name"));
                    String msg=getIntent().getStringExtra("des");//Other data can be added as per use
                    i.putExtra(Intent.EXTRA_TEXT, msg);
                    startActivity(Intent.createChooser(i, "Share this event."));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }
}

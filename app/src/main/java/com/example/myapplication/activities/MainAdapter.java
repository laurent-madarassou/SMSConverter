package activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.appcompat.*;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import re.devboxx.smsconverter.R;


import tools.ImageProcessing;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ThreadViewHolder> implements Filterable {
    // Load Settings

    Cursor threadCursor ;
    Cursor          threadCursorSearch ;
    public MatrixCursor cursorFiltered;


    // AppAdapter variables
    private Context context;
    private Bitmap bitmap;


    public MainAdapter(Cursor threadcursor, Context context) {

        this.threadCursor = threadcursor;

        this.context = context;


    }

    @Override
    public int getItemCount() {
        return threadCursor.getCount();
    }

    public void clear() {
        //threadCursor..clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ThreadViewHolder ThreadViewHolder, int i) {
        final int position = i;
        //DatabaseUtils.dumpCursor(threadCursor);
        threadCursor.moveToPosition(position);
        String nom = threadCursor.getString(threadCursor.getColumnIndex("nom"));
        String tel = threadCursor.getString(threadCursor.getColumnIndex("tel"));
        if (nom.length() > 20) nom = nom.substring(0,19);
        ThreadViewHolder.nom.setText(nom);
        ThreadViewHolder.tel.setText(tel);

        String totalsms = context.getResources().getString(R.string.total_sms);
        ThreadViewHolder.total.setText(threadCursor.getString(threadCursor.getColumnIndex("total"))+ " " + totalsms );

        ImageProcessing imageProcessing = new ImageProcessing();
        String imageUri = threadCursor.getString(threadCursor.getColumnIndex("photo"));
        try {
            bitmap = MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(), Uri.parse(imageUri));
            ThreadViewHolder.photo.setImageBitmap(bitmap);
            imageProcessing.scaleImageRelative(ThreadViewHolder.photo,this.context);
        } catch (Exception e) {
            ThreadViewHolder.photo.setImageResource(R.drawable.nouser);
            imageProcessing.scaleImageRelative(ThreadViewHolder.photo, this.context);
        }
        setButtonEvents(ThreadViewHolder, threadCursor, position);
    }

    private void setButtonEvents(ThreadViewHolder ThreadViewHolder, final Cursor ThreadthreadCursor,final int position) {


        final ImageView photo = ThreadViewHolder.photo;
        final CardView cardView = ThreadViewHolder.vCard;



        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) context;
                threadCursor.moveToPosition(position);
                String imageUri = threadCursor.getString(threadCursor.getColumnIndex("photo"));
                ImageView imageViewTransition = (ImageView) ((View) view.getParent()).findViewById(R.id.photo);

                String tel = threadCursor.getString(threadCursor.getColumnIndex("tel"));

                Intent intent = new Intent(context, ThreadActivity.class);
                intent.putExtra("ID",  threadCursor.getString(threadCursor.getColumnIndex("thread_id")));
                intent.putExtra("nom",  threadCursor.getString(threadCursor.getColumnIndex("nom")));
                intent.putExtra("tel", tel );
                intent.putExtra("photo",  threadCursor.getString(threadCursor.getColumnIndex("photo")));
                intent.putExtra("total",  threadCursor.getString(threadCursor.getColumnIndex("total")));
                //intent.putExtra("bitmap", bitmap);//TODO risque d'étre de valeur nulle
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    String transitionName = context.getResources().getString(R.string.transition_app_icon);

                    ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, imageViewTransition, transitionName); //TODO ????????????
                    context.startActivity(intent, transitionActivityOptions.toBundle());
                } else {
                    context.startActivity(intent);
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.fade_back);
                }
            }
        });

    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final Cursor results ;//TODO ????? = new Cursor();

                cursorFiltered  = new MatrixCursor(new String[]{"_id", "thread_id", "total", "nom", "tel", "photo"});
                String nom = "";
                String tel = "";

                if (threadCursorSearch  == null) {
                    threadCursorSearch = threadCursor;
                }


                if (charSequence != null) {
                    if (threadCursorSearch != null && threadCursorSearch.getCount() > 0) {

                        threadCursorSearch.moveToFirst();

                        while (!threadCursorSearch.isLast()) {
                            nom = threadCursorSearch.getString(threadCursorSearch.getColumnIndex("nom")).toLowerCase();
                            tel = threadCursorSearch.getString(threadCursorSearch.getColumnIndex("tel")).toLowerCase();

                            if (nom.contains(charSequence.toString()) ||
                                    tel.contains(charSequence.toString())
                                    ) {
                                String[] resultString = new String []{
                                        threadCursorSearch.getString(threadCursorSearch.getColumnIndexOrThrow("_id")),
                                        threadCursorSearch.getString(threadCursorSearch.getColumnIndexOrThrow("thread_id")),
                                        threadCursorSearch.getString(threadCursorSearch.getColumnIndexOrThrow("total")),
                                        threadCursorSearch.getString(threadCursorSearch.getColumnIndexOrThrow("nom")),
                                        threadCursorSearch.getString(threadCursorSearch.getColumnIndexOrThrow("tel")),
                                        threadCursorSearch.getString(threadCursorSearch.getColumnIndexOrThrow("photo"))
                                };
                                cursorFiltered.addRow(resultString);

                            }
                            threadCursorSearch.moveToNext();
                        }
                    }
                    oReturn.values = cursorFiltered;
                    oReturn.count = cursorFiltered.getCount();
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults.count > 0) {
                    MainActivity.setResultsMessage(false);
                } else {
                    MainActivity.setResultsMessage(true);
                }
                threadCursor = (Cursor) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public ThreadViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View ThreadAdapterView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.thread_layout, viewGroup, false);
        return new ThreadViewHolder(ThreadAdapterView);
    }
    public static class ThreadViewHolder extends RecyclerView.ViewHolder {
        protected TextView nom;
        protected TextView tel;
        protected TextView total;
        protected ImageView photo;
        protected Button btnzip;
        protected Button btnenvoi;
        protected CardView vCard;
        public ThreadViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.nom);
            tel = (TextView) v.findViewById(R.id.tel);
            photo = (ImageView) v.findViewById(R.id.photo);
            total = (TextView) v.findViewById(R.id.total);
            //btnzip = (Button) v.findViewById(R.id.btnZip);
            //btnenvoi = (Button) v.findViewById(R.id.btnEnvoi);
            vCard = (CardView) v.findViewById(R.id.thread_card);

        }
    }
}

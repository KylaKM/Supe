package com.example.dininghall;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Tab23Fragment extends Fragment {
    private static final String TAG = "Week";
    TextView txt3;
    private String week = "";
    private LinkedHashMap<String, String> menu = new LinkedHashMap<>();

    public void readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }

    public void readMessagesArray(JsonReader reader) throws IOException {
        int count = 0;
        reader.beginArray();
        while (reader.hasNext()) {
            readMessage(reader, count);
            count++;
        }
        reader.endArray();
    }

    public void readMessage(JsonReader reader, int count) throws IOException {
        String day,food;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("Monday") || name.equals("Tuesday") || name.equals("Wednesday")
                    || name.equals("Thursday") || name.equals("Friday")
                    || name.equals("Saturday") || name.equals("Sunday")) {
                day = name;
                food = reader.nextString();
                if (count == 0) {
                    menu.put(day, food);
                } else if (count == 1) {
                    menu.put(day, menu.get(day) + "\n" + food);
                } else {
                    menu.put(day, menu.get(day) + "\n" + food);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            InputStream in = getActivity().getAssets().open("Final Week McNair.json");
            readJsonStream(in);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        setWeek();
        super.onCreate(savedInstanceState);
    }

    public void setWeek() {
        for(String str : menu.keySet()) {
            week += str + ":" + "\n\n" + menu.get(str) + "\n";

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_2fragment, container, false);
        txt3 = (TextView)view.findViewById(R.id.text2Tab2);
        txt3.setText(week);
        setHasOptionsMenu(true);
        return view;
    }
}

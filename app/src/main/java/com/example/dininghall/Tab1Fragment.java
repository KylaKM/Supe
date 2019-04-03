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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Today";
    TextView txt1;
    private String today;
    private List<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
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
                    menu.put(day, menu.get(day) + food);
                } else {
                    menu.put(day, menu.get(day) + food);
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
            InputStream in = getActivity().getAssets().open("Week11.json");
            readJsonStream(in);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        setDay();
        super.onCreate(savedInstanceState);
    }

    public void setDay() {
        Calendar c= Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("EEEE");
        String dayofweek = sd.format(c.getTime());
        switch(dayofweek) {
                case "Sunday":
                    today = menu.get("Sunday");
                    break;
                case "Monday":
                    today = menu.get("Monday");
                    break;
                case "Tuesday":
                    today = menu.get("Tuesday");
                    break;
                case "Wednesday":
                    today = menu.get("Wednesday");
                    break;
                case "Thursday":
                    today = menu.get("Thursday");
                    break;
                case "Friday":
                    today = menu.get("Friday");
                    break;
                case "Saturday":
                    today = menu.get("Saturday");
                    break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        txt1 = (TextView) view.findViewById(R.id.textTab1);
        txt1.setText(today);
        setHasOptionsMenu(true);
        return view;
    }
}
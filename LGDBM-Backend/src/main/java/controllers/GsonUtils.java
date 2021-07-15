/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author guilherme.moura
 */
public class GsonUtils {

//    public static Gson getInstanceWithDateAdapter() {
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(Date.class, new DateDeserializer());
//        builder.registerTypeAdapter(Date.class, new DateSerializer());
//        return builder.create();
//    }
    public static Gson getInstanceWithStringDateAdapter() {
        GsonBuilder builder = new GsonBuilder();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
//        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
        builder.registerTypeAdapter(Date.class, new DateDeserializer(sdf));
        builder.registerTypeAdapter(Date.class, new DateSerializer(sdf));
        builder.registerTypeAdapter(Time.class, new TimeDeserializer(sdfTime));
        builder.registerTypeAdapter(Time.class, new TimeSerializer(sdfTime));
        builder.registerTypeAdapter(java.sql.Date.class, new DateSerializer(sdf));
        return builder.create();
    }

    public static Gson getInstanceWithStringDateAdapter(Map<Class, TypeAdapter> adapters) {
        GsonBuilder builder = new GsonBuilder();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//        final SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
        builder.registerTypeAdapter(Date.class, new DateDeserializer(sdf));
        builder.registerTypeAdapter(Date.class, new DateSerializer(sdf));
        if (adapters != null) {
            for (Map.Entry<Class, TypeAdapter> entry : adapters.entrySet()) {
                builder.registerTypeAdapter(entry.getKey(), entry.getValue());
            }
        }
        return builder.create();
    }

    public static Gson getSimpleInstance() {
        return new Gson();
    }

    static class DateSerializer implements JsonSerializer<Date> {

        protected SimpleDateFormat sdf;
        List<SimpleDateFormat> knownPatterns;

        public DateSerializer(SimpleDateFormat sdf) {
            this.sdf = sdf;
            knownPatterns = new ArrayList<>();
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm.ss'Z'"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"));
            knownPatterns.add(new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a"));
            knownPatterns.add(new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a"));
            knownPatterns.add(new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
            knownPatterns.add(new SimpleDateFormat("MMM dd, yyyy"));
            knownPatterns.add(new SimpleDateFormat("MMM d, yyyy"));
            knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
            knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
        }

        @Override
        public JsonElement serialize(Date t, Type type, JsonSerializationContext jsc) {
            if (t == null) {
                return new JsonPrimitive((String) null);
            }
//            for (SimpleDateFormat pattern : knownPatterns) {
//                 return new JsonPrimitive(pattern.format(t));
//            }

            return new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(t));
        }
    }

    static class DateDeserializer implements JsonDeserializer<Date> {

        protected SimpleDateFormat sdf;
        List<SimpleDateFormat> knownPatterns;

        public DateDeserializer(SimpleDateFormat sdf) {
            this.sdf = sdf;
            knownPatterns = new ArrayList<>();
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm.ss'Z'"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss"));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"));
            knownPatterns.add(new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a"));
            knownPatterns.add(new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a"));
            knownPatterns.add(new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z '('z')'", Locale.ENGLISH));
            knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
            knownPatterns.add(new SimpleDateFormat("MMM dd, yyyy"));
            knownPatterns.add(new SimpleDateFormat("MMM d, yyyy"));
            knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
            knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
        }

        @Override
        public Date deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            String data_str = "";
            Date data = null;
            data_str = je.getAsJsonPrimitive().getAsString();
            for (SimpleDateFormat pattern : knownPatterns) {
                try {
                    return new Date(pattern.parse(data_str).getTime());
                } catch (ParseException pe) {
                    try {
                        return new Date(Long.parseLong(data_str));
                    } catch (Exception e) {
                    }
                }
            }
            return data;
        }
    }

    static class TimeDeserializer implements JsonDeserializer<Time> {

        protected SimpleDateFormat sdf;
        List<SimpleDateFormat> knownPatterns;

        public TimeDeserializer(SimpleDateFormat sdf) {
            this.sdf = sdf;
            knownPatterns = new ArrayList<>();
            knownPatterns.add(new SimpleDateFormat("HH:mm"));
            knownPatterns.add(new SimpleDateFormat("HH:mm:ss"));
        }

        @Override
        public Time deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
            String data_str = "";
            Date data = null;
            data_str = je.getAsJsonPrimitive().getAsString();
            for (SimpleDateFormat pattern : knownPatterns) {
                try {
                    return new Time(pattern.parse(data_str).getTime());
                } catch (ParseException pe) {
                    try {
                        return Time.valueOf(data_str + ":00");
                    } catch (Exception e) {
                    }
                }
            }
            return null;
        }
    }
    
    static class TimeSerializer implements JsonSerializer<Date> {

        protected SimpleDateFormat sdf;
        List<SimpleDateFormat> knownPatterns;

        public TimeSerializer(SimpleDateFormat sdf) {
            this.sdf = sdf;
            knownPatterns = new ArrayList<>();
            knownPatterns.add(new SimpleDateFormat("HH:mm"));
            knownPatterns.add(new SimpleDateFormat("HH:mm:ss"));
        }

        @Override
        public JsonElement serialize(Date t, Type type, JsonSerializationContext jsc) {
            if (t == null) {
                return new JsonPrimitive((String) null);
            }
//            for (SimpleDateFormat pattern : knownPatterns) {
//                 return new JsonPrimitive(pattern.format(t));
//            }

            return new JsonPrimitive(new SimpleDateFormat("HH:mm").format(t));
        }
    }


}

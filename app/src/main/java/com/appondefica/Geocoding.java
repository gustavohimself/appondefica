package com.appondefica;

import java.util.ArrayList;

public class Geocoding {
    public ArrayList<Result> results;
    public String status;

    public class AddressComponent{
        public String long_name;
        public String short_name;
        public ArrayList<String> types;
    }

    public class Geometry{
        public Location location;
        public String location_type;
        public Viewport viewport;
    }

    public class Location{
        public double lat;
        public double lng;
    }

    public class Northeast{
        public double lat;
        public double lng;
    }

    public class PlusCode{
        public String compound_code;
        public String global_code;
    }

    public class Result{
        public ArrayList<AddressComponent> address_components;
        public String formatted_address;
        public Geometry geometry;
        public boolean partial_match;
        public String place_id;
        public PlusCode plus_code;
        public ArrayList<String> types;
    }

    public class Southwest{
        public double lat;
        public double lng;
    }

    public class Viewport{
        public Northeast northeast;
        public Southwest southwest;
    }
}

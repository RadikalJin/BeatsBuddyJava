package hello;

import java.lang.Double;
import java.lang.String;

public class Prices {

    private final String id;
    private final Double bid;
    private final Double offer;

    public Prices(String id, Double bid, Double offer) {
        this.id = id;
        this.bid = bid;
        this.offer = offer;
    }


    public String getId() {
        return id;
    }

    public Double getBid() {
        return bid;
    }

    public Double getOffer() {
        return offer;
    }
}

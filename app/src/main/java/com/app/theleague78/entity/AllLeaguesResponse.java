package com.app.theleague78.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class AllLeaguesResponse implements Serializable {

    public ArrayList<LeagueResponse> getData() {
        return data;
    }

    public void setData(ArrayList<LeagueResponse> data) {
        this.data = data;
    }

    @SerializedName("respObject")
    private ArrayList<LeagueResponse> data;

    public class LeagueResponse implements Serializable{

        @SerializedName("leagueId")
        private String leagueId;

        @SerializedName("leagueRoot")
        private String leagueRoot;

        @SerializedName("leagueName")
        private String leagueName;

        @SerializedName("price")
        private Double price;

        @SerializedName("discount")
        private Double discount;

        @SerializedName("finalPrice")
        private Double finalPrice;

        public String getLeagueId() {
            return leagueId;
        }

        public void setLeagueId(String leagueId) {
            this.leagueId = leagueId;
        }

        public String getLeagueRoot() {
            return leagueRoot;
        }

        public void setLeagueRoot(String leagueRoot) {
            this.leagueRoot = leagueRoot;
        }

        public String getLeagueName() {
            return leagueName;
        }

        public void setLeagueName(String leagueName) {
            this.leagueName = leagueName;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getDiscount() {
            return discount;
        }

        public void setDiscount(Double discount) {
            this.discount = discount;
        }

        public Double getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(Double finalPrice) {
            this.finalPrice = finalPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @SerializedName("status")
        private String status;




    }
}

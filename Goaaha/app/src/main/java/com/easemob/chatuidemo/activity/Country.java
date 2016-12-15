package com.easemob.chatuidemo.activity;

public class Country {
    private Long id;
    private String Name;
    private String Currency;
    private int url;

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public Country(Long id, String name, String currency, int url) {
        this.id = id;
        Name = name;
        Currency = currency;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}

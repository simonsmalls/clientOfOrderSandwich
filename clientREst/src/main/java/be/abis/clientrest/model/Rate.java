package be.abis.clientrest.model;

import java.time.LocalDate;

public class Rate {
    private boolean success;
    private boolean historical;
    private LocalDate date;
    private double result;
    private Motd motd;
    private Query query;

    private Info info;
    private String bljbdkfjb;

    public String getBljbdkfjb() {
        return bljbdkfjb;
    }

    public void setBljbdkfjb(String bljbdkfjb) {
        this.bljbdkfjb = bljbdkfjb;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isHistorical() {
        return historical;
    }

    public void setHistorical(boolean historical) {
        this.historical = historical;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public Motd getMotd() {
        return motd;
    }

    public void setMotd(Motd motd) {
        this.motd = motd;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }


}

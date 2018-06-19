package com.octalsoftaware.buguroo.Utils;

public class UserData {
    private String username = "", session = "", company = "", bugurooEndpoint = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBugurooEndpoint() {
        return bugurooEndpoint;
    }

    public void setBugurooEndpoint(String bugurooEndpoint) {
        this.bugurooEndpoint = bugurooEndpoint;
    }
}
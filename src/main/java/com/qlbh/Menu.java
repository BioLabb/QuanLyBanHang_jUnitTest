package com.qlbh;

import javafx.scene.control.TableView;

import java.util.Date;

public class Menu {
    private Date Dateta;
    private TableView<product> tbvp;

    public Menu() {
    }

    public Menu(Date dateta, TableView<product> tbvp) {
        Dateta = dateta;
        this.tbvp = tbvp;
    }

    public Date getDateta() {
        return Dateta;
    }

    public void setDateta(Date dateta) {
        Dateta = dateta;
    }

    public TableView<product> getTbvp() {
        return tbvp;
    }

    public void setTbvp(TableView<product> tbvp) {
        this.tbvp = tbvp;
    }
}


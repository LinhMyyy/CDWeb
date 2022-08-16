package com.cdweb.api.web.output;

import com.cdweb.dto.ShoppingCartDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartOutput {
    private double total;
    private List<ShoppingCartDTO> list = new ArrayList<>();


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(total) + " VND";
    }

    public List<ShoppingCartDTO> getList() {
        return list;
    }

    public void setList(List<ShoppingCartDTO> list) {
        this.list = list;
    }

    public String pay() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(total + 25000) + " VND";
    }

}

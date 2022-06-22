package com.academiapp.models;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

public class UniModel implements Serializable {
  private List<? extends Tenants> tenants;

  private Integer count;

  public List<? extends Tenants> getTenants() {
    return this.tenants;
  }

  public void setTenants(List<? extends Tenants> tenants) {
    this.tenants = tenants;
  }

  public Integer getCount() {
    return this.count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public static class Tenants implements Serializable {
    private String url_back;

    private Integer id_tenant;

    private String url_front;

    private String name;

    private String icon;

    private Boolean status;

    public String getUrl_back() {
      return this.url_back;
    }

    public void setUrl_back(String url_back) {
      this.url_back = url_back;
    }

    public Integer getId_tenant() {
      return this.id_tenant;
    }

    public void setId_tenant(Integer id_tenant) {
      this.id_tenant = id_tenant;
    }

    public String getUrl_front() {
      return this.url_front;
    }

    public void setUrl_front(String url_front) {
      this.url_front = url_front;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getIcon() {
      return this.icon;
    }

    public void setIcon(String icon) {
      this.icon = icon;
    }

    public Boolean getStatus() {
      return this.status;
    }

    public void setStatus(Boolean status) {
      this.status = status;
    }
  }
}

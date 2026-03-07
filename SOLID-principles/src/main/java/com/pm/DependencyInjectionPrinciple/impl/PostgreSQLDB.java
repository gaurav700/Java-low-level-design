package com.pm.DependencyInjectionPrinciple.impl;

import com.pm.DependencyInjectionPrinciple.Database;

public class PostgreSQLDB implements Database {
    @Override
    public void insert(String table, String data) {
        System.out.println("PostGreSQL: Inserting into " + table + " -> " + data);
    }

    @Override
    public String query(String table, String id) {
        System.out.println("PostGreSQL: Querying " + table + " for id " + id);
        return "{ id: " + id + ", item: 'Widget' }";
    }
}

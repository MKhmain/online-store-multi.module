package org.example.storage.impl;

import org.example.entities.Purchase;
import org.example.storage.StorageServiceOrder;

import java.io.*;
import java.util.List;

public class OrderStorage implements StorageServiceOrder {
    public static final String RESOURCES_FOLDER="resource";
    public static final String ORDER_DATA ="order.data";
    private static OrderStorage instance;
    public static OrderStorage getInstance(){
        if(instance==null)
            instance=new OrderStorage();
        return instance;
    }
    @Override
    public void saveOrder(List<Purchase> purchases) {
        try(var f=new FileOutputStream(new File(RESOURCES_FOLDER+File.separator+ ORDER_DATA));
            var of=new ObjectOutputStream(f)) {
            of.writeObject(purchases);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Purchase> loadOrder() {
        File file = new File(RESOURCES_FOLDER + File.separator + ORDER_DATA);
        if (file.length() == 0) {
            return null;  // File is empty, return null or handle it accordingly
        }
        try (var oi = new ObjectInputStream(new FileInputStream(RESOURCES_FOLDER+File.separator+ORDER_DATA))) {
            Object obj = oi.readObject();
            if (obj != null)
                return (List<Purchase>) obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {

    private static final String MAIN_PACKAGE = "com.example.jdurieu.testbookatiger";


    public static void main(String args[]) {
        int version = 1;
        Schema schema = new Schema(version,MAIN_PACKAGE+".model");
        schema.setDefaultJavaPackageDao(MAIN_PACKAGE + ".model.dao");

        Entity entityJob = schema.addEntity("Job");
        //entityJob.addIdProperty().autoincrement();
        entityJob.addStringProperty("__status");
        entityJob.addStringProperty("customer_name");
        entityJob.addStringProperty("distance");
        entityJob.addDateProperty("job_date");
        entityJob.addStringProperty("extras");
        entityJob.addStringProperty("order_duration");
        entityJob.addStringProperty("order_id").primaryKey();
        entityJob.addStringProperty("order_time");
        entityJob.addStringProperty("payment_method");
        entityJob.addStringProperty("price");
        entityJob.addIntProperty("recurrency");
        entityJob.addStringProperty("job_city");
        entityJob.addStringProperty("job_latitude");
        entityJob.addStringProperty("job_longitude");
        entityJob.addIntProperty("job_postalcode");
        entityJob.addStringProperty("job_street");
        entityJob.addStringProperty("status");

        DaoGenerator daoGenerator = null;
        try {
            daoGenerator = new DaoGenerator();
            daoGenerator.generateAll(schema,"../TestBookATiger/src-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

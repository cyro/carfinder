package com.autoscout24.carfinder;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.autoscout24.carfinder.arch.data.database.VehicleDatabase;
import com.autoscout24.carfinder.arch.data.database.VehicleEntry;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class VehicleDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private static final VehicleEntry VEHICLE = new VehicleEntry(7,
            "Mercedes",
            "A 45 ",
            "Gas",
            150000,
            1248598676,
            "AMG",
            "05-2018",
            "white",
            "Very fast car",
            new ArrayList<Images>(),
            new Seller("+123456789", "Dealership", "Cape Town"));

    private VehicleDatabase vehicleDatabase;

    @Before
    public void initDb() throws Exception {
        vehicleDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                VehicleDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception {
        vehicleDatabase.close();
    }

    @Test
    public void insertAndGetVehicle() {
        vehicleDatabase.vehicleDao().bulkInsert(VEHICLE);

        LiveData<List<VehicleEntry>> databaseData = vehicleDatabase.vehicleDao().getAllVehicles();
        databaseData.observeForever(databaseDataFromDb -> {
                   assert(databaseDataFromDb.get(0).getId() == VEHICLE.getId());
            });
        }


}

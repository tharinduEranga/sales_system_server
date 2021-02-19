package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.Vehicle;
import com.icbt.ap.sales.repository.main.CrudRepository;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
public interface VehicleRepository extends CrudRepository<String, Vehicle> {
    Vehicle findByRegNo(String regNo);
}

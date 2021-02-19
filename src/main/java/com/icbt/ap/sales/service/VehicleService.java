package com.icbt.ap.sales.service;

import com.icbt.ap.sales.entity.Vehicle;
import com.icbt.ap.sales.service.main.CrudService;
import org.springframework.stereotype.Service;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
@Service
public interface VehicleService extends CrudService<String, Vehicle> {

}

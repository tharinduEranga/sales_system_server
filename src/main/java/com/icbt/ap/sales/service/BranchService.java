package com.icbt.ap.sales.service;

import com.icbt.ap.sales.entity.Branch;
import com.icbt.ap.sales.service.main.CrudService;
import org.springframework.stereotype.Service;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Service
public interface BranchService extends CrudService<String, Branch> {

}

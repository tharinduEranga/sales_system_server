package com.icbt.ap.sales.repository;

import com.icbt.ap.sales.entity.Branch;
import com.icbt.ap.sales.repository.main.CrudRepository;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
public interface BranchRepository extends CrudRepository<String, Branch> {

    Branch findByName(String name);

    Branch findByTel(String tel);
}

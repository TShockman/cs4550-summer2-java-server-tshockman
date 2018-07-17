package com.shockman.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shockman.webdev.models.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

}

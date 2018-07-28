package com.shockman.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shockman.webdev.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer>{

}

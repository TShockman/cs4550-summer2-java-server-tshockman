package com.shockman.webdev.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shockman.webdev.models.Lesson;
import com.shockman.webdev.models.Module;
import com.shockman.webdev.models.Widget;
import com.shockman.webdev.repositories.LessonRepository;
import com.shockman.webdev.repositories.ModuleRepository;
import com.shockman.webdev.repositories.WidgetRepository;

@CrossOrigin(origins="*")
@RestController
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> getAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{wid}")
	public Optional<Widget> getWidgetById(@PathVariable("wid") int wid) {
		return widgetRepository.findById(wid);
	}
	
	@GetMapping("/api/lesson/{lid}/widget")
	public List<Widget> getAllWidgetsForLesson(@PathVariable("lid") int lid) {
		Optional<Lesson> opt = lessonRepository.findById(lid);
		if (opt.isPresent()) {
			Lesson lesson = opt.get();
			return lesson.getWidgets();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lid}/widget")
	public Widget createWidget(@PathVariable("lid") int lid, @RequestBody Widget newWidget) {
		Optional<Lesson> opt = lessonRepository.findById(lid);
		if (opt.isPresent()) {
			Lesson lesson = opt.get();
			newWidget.setLesson(lesson);
			return widgetRepository.save(newWidget);
		}
		return null;
	}
	
	@PutMapping("/api/widget/{wid}")
	public Widget updateWidget(@PathVariable("wid") int wid, @RequestBody Widget newWidget) {
		Optional<Widget> opt = widgetRepository.findById(wid);
		if (opt.isPresent()) {
			Widget widget = opt.get();
			widget.setClassName(newWidget.getClassName());
			widget.setHeight(newWidget.getHeight());
			widget.setHref(newWidget.getHref());
			widget.setListItems(newWidget.getListItems());
			widget.setListType(newWidget.getListType());
			widget.setName(newWidget.getName());
			widget.setOrdering(newWidget.getOrdering());
			widget.setSize(newWidget.getSize());
			widget.setSrc(newWidget.getSrc());
			widget.setStyle(newWidget.getStyle());
			widget.setText(newWidget.getText());
			widget.setType(newWidget.getType());
			widget.setWidth(newWidget.getWidth());
			return widgetRepository.save(widget);
		}
		return null;
	}
	
	@DeleteMapping("/api/widget/{wid}") 
	public void deleteWidget(@PathVariable("wid") int wid) {
		widgetRepository.deleteById(wid);
	}

}

package com.stadium.app.controller;

import com.stadium.app.model.dto.EventDto;
import com.stadium.app.model.entity.Event;
import com.stadium.app.model.requestBody.EventRequestBody;
import com.stadium.app.service.EventService;
import com.stadium.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin-dashboard.html";
	}

	@GetMapping("/edit")
	public String showEditPage(Model model) {
		model.addAttribute("events", eventService.getAllEvents());
		return "edit-events.html";
	}

	@PostMapping("/saveEvent")
	public String saveEmployee(@ModelAttribute("event") EventRequestBody event) {
		eventService.updateEvent(event);
		return "redirect:/admin/edit";
	}

	@GetMapping("/create")
	public String adminPanel(Model model) {
		model.addAttribute("events", eventService.getAllEvents());
		return "create-event.html";
	}

	@PostMapping("/create")
	public String createEvent(EventRequestBody eventRequestBody, RedirectAttributes ra) {
		EventDto eventDto = eventService.createEvent(eventRequestBody.getName(), eventRequestBody.getDate());
		ra.addFlashAttribute("success", String.format("Event with name %s successful created.", eventDto.getName()));
		return "redirect:/admin/create";
	}

	@GetMapping("/update/{id}")
	public String showEventEditPage(@PathVariable("id") Long id, Model model) {
		Event event = eventService.getEvent(id);
		model.addAttribute("event", event);
		return "update-event.html";
	}

	@GetMapping("/cancel/{id}")
	public String cancelEvent(@PathVariable("id") Long id, RedirectAttributes ra) {
		eventService.cancelEvent(id);
		ra.addFlashAttribute("message","Event successful canceled. Check log for more info.");
		return "redirect:/admin/edit";
	}

	@GetMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id") Long id, RedirectAttributes ra) {
		eventService.deleteEvent(id);
		ra.addFlashAttribute("message","Event successful deleted. Check log for more info.");
		return "redirect:/admin/edit";
	}

}

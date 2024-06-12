package com.vd.restaurant.restaurant_commission_calculator.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vd.restaurant.restaurant_commission_calculator.entities.MenuItem;
import com.vd.restaurant.restaurant_commission_calculator.exceptions.MenuItemNotFoundException;
import com.vd.restaurant.restaurant_commission_calculator.services.MenuItemService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/add")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem createdMenuItem = menuItemService.addMenuItem(menuItem);
        return new ResponseEntity<>(createdMenuItem, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemService.getAllMenuItems();
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

	@GetMapping("/{id}")
	public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
		Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
		if (menuItem.isPresent()) {
			return new ResponseEntity<>(menuItem.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        try {
            menuItemService.deleteMenuItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MenuItemNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

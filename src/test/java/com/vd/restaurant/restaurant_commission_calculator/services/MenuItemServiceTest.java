package com.vd.restaurant.restaurant_commission_calculator.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vd.restaurant.restaurant_commission_calculator.entities.MenuItem;
import com.vd.restaurant.restaurant_commission_calculator.exceptions.MenuItemNotFoundException;
import com.vd.restaurant.restaurant_commission_calculator.repositories.MenuItemRepository;

public class MenuItemServiceTest {

    @InjectMocks
    private MenuItemService menuItemService;

    @Mock
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMenuItem() {
        MenuItem menuItem = new MenuItem();
        when(menuItemRepository.save(menuItem)).thenReturn(menuItem);

        MenuItem result = menuItemService.addMenuItem(menuItem);
        assertEquals(menuItem, result);
    }

    @Test
    public void testGetAllMenuItems() {
        List<MenuItem> menuItems = Arrays.asList(new MenuItem(), new MenuItem());
        when(menuItemRepository.findAll()).thenReturn(menuItems);

        List<MenuItem> result = menuItemService.getAllMenuItems();
        assertEquals(menuItems, result);
    }

    @Test
    public void testGetMenuItemById() {
        MenuItem menuItem = new MenuItem();
        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(menuItem));

        Optional<MenuItem> result = menuItemService.getMenuItemById(1L);
        assertTrue(result.isPresent());
        assertEquals(menuItem, result.get());
    }

    @Test
    public void testDeleteMenuItem() {
        when(menuItemRepository.existsById(1L)).thenReturn(true);
        doNothing().when(menuItemRepository).deleteById(1L);

        assertDoesNotThrow(() -> menuItemService.deleteMenuItem(1L));
    }

    @Test
    public void testDeleteMenuItemNotFound() {
        when(menuItemRepository.existsById(1L)).thenReturn(false);

        assertThrows(MenuItemNotFoundException.class, () -> menuItemService.deleteMenuItem(1L));
    }
}
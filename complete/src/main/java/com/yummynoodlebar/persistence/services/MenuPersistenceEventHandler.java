package com.yummynoodlebar.persistence.services;

import com.yummynoodlebar.config.PersistenceConfig;
import com.yummynoodlebar.events.menu.*;
import com.yummynoodlebar.persistence.domain.MenuItem;
import com.yummynoodlebar.persistence.repository.MenuItemRepository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuPersistenceEventHandler implements MenuPersistenceService {

  private static final Logger LOG = LoggerFactory.getLogger(MenuPersistenceEventHandler.class);
	
  private MenuItemRepository menuItemRepository;

  public MenuPersistenceEventHandler(MenuItemRepository menuItemRepository) {
    this.menuItemRepository = menuItemRepository;
  }

  @Override
  public AllMenuItemsEvent requestAllMenuItems(RequestAllMenuItemsEvent requestAllMenuItemsEvent) {
	Iterable<MenuItem> menuItems = menuItemRepository.findAll();
    
	List<MenuItemDetails> details = new ArrayList<MenuItemDetails>();

    for(MenuItem item: menuItems) {
      details.add(item.toStatusDetails());
    }

    return new AllMenuItemsEvent(details);
  }

  @Override
  public MenuItemDetailsEvent requestMenuItemDetails(RequestMenuItemDetailsEvent requestMenuItemDetailsEvent) {
    MenuItem item = menuItemRepository.findOne(requestMenuItemDetailsEvent.getId());

    if (item == null) {
      return MenuItemDetailsEvent.notFound(requestMenuItemDetailsEvent.getId());
    }

    return new MenuItemDetailsEvent(
        requestMenuItemDetailsEvent.getId(),
        item.toStatusDetails());
  }

  @Override
  public MenuItemDetailsEvent createMenuItem(CreateMenuItemEvent createMenuItemEvent) {
    MenuItem item = menuItemRepository.save(
        MenuItem.fromStatusDetails(createMenuItemEvent.getDetails()));

    return new MenuItemDetailsEvent(
        item.getId(),
        item.toStatusDetails());
  }
}

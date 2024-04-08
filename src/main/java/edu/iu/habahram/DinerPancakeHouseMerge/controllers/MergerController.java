package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItemRecord;
import edu.iu.habahram.DinerPancakeHouseMerge.model.SignupRequest;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.MergerRepository;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/merger")
public class MergerController {
    MergerRepository mergerRepository;

    public MergerController(MergerRepository mergerRepository) {
        this.mergerRepository = mergerRepository;
    }

    @GetMapping
    public List<MenuItemRecord> get() {
        List<MenuItemRecord> items = mergerRepository.getTheMenuItems();
        return items;
    }

    @GetMapping("/vegetarian")
    public List<MenuItem> getVegetarianItems() {
        List<MenuItemRecord> items = mergerRepository.getTheMenuItems();
        List<MenuItem> vegetarianItems = new ArrayList<>();
        for (MenuItemRecord record : items) {
            if (record.vegetarian()) {
                vegetarianItems.add(new MenuItem(record.name(), record.description(), record.vegetarian(), record.price()));
            }
        }
        return vegetarianItems;
    }

    @GetMapping("/breakfast")
    public List<MenuItem> getBreakfastItems() {
        List<MenuItemRecord> items = mergerRepository.getTheMenuItems();
        List<MenuItem> breakfastItems = new ArrayList<>();
        for (MenuItemRecord record : items) {
            if (record.description().contains("Breakfast")) {
                breakfastItems.add(new MenuItem(record.name(), record.description(), record.vegetarian(), record.price()));
            }
        }
        return breakfastItems;
    }

    @GetMapping("/lunch")
    public List<MenuItem> getLunchItems() {
        List<MenuItemRecord> items = mergerRepository.getTheMenuItems();
        List<MenuItem> lunchItems = new ArrayList<>();
        for (MenuItemRecord record : items) {
            if (record.description().contains("Lunch")) {
                lunchItems.add(new MenuItem(record.name(), record.description(), record.vegetarian(), record.price()));
            }
        }
        return lunchItems;
    }

    @GetMapping("/supper")
    public List<MenuItem> getSupperItems() {
        List<MenuItemRecord> items = mergerRepository.getTheMenuItems();
        List<MenuItem> supperItems = new ArrayList<>();
        for (MenuItemRecord record : items) {
            if (record.description().contains("Dinner")) {
                supperItems.add(new MenuItem(record.name(), record.description(), record.vegetarian(), record.price()));
            }
        }
        return supperItems;
    }

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        String password = signupRequest.getPassword();
        String email = signupRequest.getEmail();

        try (FileWriter writer = new FileWriter("data/customers.txt", true)) {
            writer.write(username + "," + password + "," + email + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
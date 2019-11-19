package com.example.demo;

import com.example.demo.persistence.Item;
import com.example.demo.persistence.categories.Brand;
import com.example.demo.services.BrandService;
import com.example.demo.services.ItemService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author idobre
 * @since 17/11/2019
 */
@Component
public class Runner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Runner.class);

    final List<String> brands = Arrays.asList(new String[]{
            "Christian Dior",
            "Chanel",
            "Gucci",
            "Dolce & Gabbana",
            "Prada",
            "Versace",
            "Bvlgari",
            "Yves Saint Laurent"
    });

    @Autowired
    private BrandService brandService;

    @Autowired
    private ItemService itemService;

    private final int NUMBER_ITEMS = 10000;

    @Override
    public void run(ApplicationArguments args) {
        for (String brandLabel : brands) {
            final Brand brand = new Brand();
            brand.setLabel(brandLabel);
            brandService.save(brand);
        }

        final List<Brand> savedBrands = brandService.findAll();

        final List<Item> items = new ArrayList<>();
        final Random random = new Random();

        for (int i = 0; i < NUMBER_ITEMS; i++) {
            final Item item = new Item();
            item.setBrand(savedBrands.get(random.nextInt(savedBrands.size())));
            item.setCurrentPrice(random.nextDouble() * 10);
            item.setDescription(RandomStringUtils.random(19, true, true));

            items.add(item);
        }

        itemService.saveAll(items);
    }
}

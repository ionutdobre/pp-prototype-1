package com.example.demo.policy.core;

import com.example.demo.persistence.Item;
import com.example.demo.persistence.categories.Brand;
import com.example.demo.policy.Column;
import com.example.demo.policy.Entity;
import com.example.demo.policy.SqlPolicyColumn;
import com.example.demo.policy.cell.StringCell;
import com.example.demo.policy.ppimplementation.PPItemEntity;
import com.example.demo.services.ItemService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author idobre
 * @since 24/11/2019
 */
class PolicyCalculatorTest {
    private static final Logger logger = LoggerFactory.getLogger(PolicyCalculatorTest.class);

    @Test
    void calculatePolicy() throws Exception {
        final PolicyCalculator policyCalculator = new PolicyCalculatorImpl(new PolicySchema(), getItemService());
        final PolicySpecification policySpecification = createSpecification();
        final GeneratedPolicy generatedPolicy = policyCalculator.calculate(policySpecification);



        final SqlPolicyColumn<StringCell> description =
                (SqlPolicyColumn<StringCell>) policySpecification.getColumns().stream().findFirst().get();
        final List test = description.calculateValues();





        logger.info(">>> generation time: " + generatedPolicy.deltaTime() + " seconds");
    }

    private PolicySpecification createSpecification() {
        final PolicySpecification policySpecification = new PolicySpecificationImpl("test specification");

        // ====================================================================================================

        final Entity<Item> itemEntity = new PPItemEntity(EntityType.ITEM, "item", "Items Entity");

        // ====================================================================================================

        final Column<StringCell> description = new SqlPolicyColumn<>(itemEntity, new ArrayList<>(), Item::getDescription);

        policySpecification.getColumns().add(description);

        // ====================================================================================================

        return policySpecification;
    }

    private ItemService getItemService() {
        final ItemService itemService = mock(ItemService.class);

        // ====================================================================================================
        // generate random data....

        final List<Brand> savedBrands = new ArrayList<>();
        for (String brandLabel : brands) {
            final Brand brand = new Brand();
            brand.setLabel(brandLabel);
            savedBrands.add(brand);
        }

        final List<Item> items = new ArrayList<>();
        final Random random = new Random();

        for (int i = 0; i < NUMBER_ITEMS; i++) {
            final Item item = new Item();
            final double currentPrice = random.nextDouble();

            item.setBrand(savedBrands.get(random.nextInt(savedBrands.size())));
            item.setCurrentPrice(Math.round(currentPrice * 1000.0) / 100.0);
            item.setDescription(RandomStringUtils.random(16, true, true));

            items.add(item);
        }

        // ====================================================================================================

        when(itemService.findAll()).thenReturn(items);

        return itemService;
    }


    private final List<String> brands = Arrays.asList(new String[]{
            "Christian Dior",
            "Chanel",
            "Gucci",
            "Dolce & Gabbana",
            "Prada",
            "Versace",
            "Bvlgari",
            "Yves Saint Laurent"
    });

    private final int NUMBER_ITEMS = 10000;
}
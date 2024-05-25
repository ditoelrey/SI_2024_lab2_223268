package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    private List<Item> items(Item... elems){
        return new ArrayList<>(Arrays.asList(elems));
    }

    @Test
    void EveryBranchTest(){
        Item item_invalid_barcode = SILab2.createItem("Shower Gel", "ssdesxas", 100, 0);
        Item item_no_barcode = SILab2.createItem("Shower Gel", null, 100, 0);
        Item item_valid = SILab2.createItem("Shower Gel", "123123123", 100, 0);
        Item item_high_price_discount_starting_with_zero = SILab2.createItem("Shampoo", "0123456789", 500, 0.1f);

        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(item_invalid_barcode), 0));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items(item_no_barcode), 0));
        assertTrue(ex.getMessage().contains("No barcode!"));

        assertEquals(true, SILab2.checkCart(items(item_valid), 300));
        assertEquals(false, SILab2.checkCart(items(item_valid), 50));

        assertTrue(SILab2.checkCart(items(item_high_price_discount_starting_with_zero), 1000));
    }

    @Test
    void MultipleConditionTest(){
        //(item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')

        // T && T && T
        Item TTT = SILab2.createItem(null,"0003",500,0.1f);

        assertTrue(TTT.getPrice() > 300);
        assertTrue(TTT.getDiscount() > 0);
        assertEquals('0', TTT.getBarcode().charAt(0));

        // T && T && F
        Item TTF = SILab2.createItem(null,"5000",550,0.1f);

        assertTrue(TTF.getPrice() > 300);
        assertTrue(TTF.getDiscount() > 0);
        assertNotEquals('0', TTF.getBarcode().charAt(0));

        // T && F...
        Item TF = SILab2.createItem(null,"2223",550,0);

        assertTrue(TF.getPrice() > 300);
        assertFalse(TF.getDiscount() > 0);

        // F ...
        Item F = SILab2.createItem(null,"2223",5,0);

        assertFalse(F.getPrice() > 300);
    }

}
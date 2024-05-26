# Втора лабораториска вежба по Софтверско инженерство
## Димитар Ахтаров, бр. на индекс 223268


### Control Flow Graph


![Screenshot 2024-05-25 164702](https://github.com/ditoelrey/SI_2024_lab2_223268/assets/138317237/c8ad0434-08bd-47ba-a5dd-226d24fb6137)


#### Цикломатска комплексност - 10

##### E - N + 2 = 31 - 23 + 2 = 10

##### 23 - јазли
##### 31 - ребра

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



### Објаснување на напишаните unit tests

#### EveryBranchTest:

Невалидни влезни параметри: Тестира дали функцијата се однесува соодветно при проследување на невалидни влезни параметри, како null за листа на артикли или артикл со невалиден баркод.
Валидни сценарија: Ги тестира различните можни комбинации на влезните параметри, како што се валиден артикл со баркод, цена и попуст.


##### MultipleConditionTest:

Ги тестира различните комбинации на услови што се проверуваат во функцијата checkCart. Секој тест креира специфичен артикл и проверува дали функцијата враќа соодветен резултат во зависност од состојбата на артиклот.
Овие тестови се напишани за да гарантираат дека функцијата checkCart работи како што треба и дека се правилно постапува со сите можни сценарија. Тие се корисни за откривање на грешки и за осигурување на квалитет на кодот пред и по воведување на промени.





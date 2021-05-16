package com.caniksea.adp3.collection;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
class AppTest {

    private Collection<App> generics;
    private List<App> appList;
    private Map<Integer, App> appMap;
    private Set<App> appSet;
    private int stringLength;

    @BeforeAll
    void setUp() {
        this.generics = new PriorityQueue<>();
        this.appList = new ArrayList<>();
        this.appMap = new HashMap<>();
        this.appSet = new HashSet<>();
        this.stringLength = 5;
    }

    @Test void testAdd() {
        for (int i = 1; i <= 10; i++) {
            String name = RandomStringUtils.randomAlphabetic(this.stringLength);
            BigDecimal balance = new BigDecimal(Math.random());
            App app = new App(i, name, balance);

            this.generics.add(app); // add to generics
            this.appList.add(app); // add to list
            this.appMap.put(i, app); // add to map, using count as key.
            this.appSet.add(app); // add to set
        }
        // tests for add
        assertAll(
                () -> assertSame(10, this.generics.size()),
                () -> assertSame(10, this.appList.size()),
                () -> assertSame(10, this.appMap.size()),
                () -> assertSame(10, this.appSet.size())
        );
    }

    @Test void testFind() {
        App appInMap = this.appMap.get(3); // find for map;
        App appInList = this.appList.stream()
                .filter(app -> app.getId() == 3)
                .findAny().orElseThrow(NoSuchElementException::new); // find for list
        App appInGenerics = this.generics.stream().filter(app -> app.getId() == 3)
                .findAny().orElseThrow(NoSuchElementException::new); // find for generics
        App appInSet = this.appSet.stream().filter(app -> app.getId() == 3)
                .findAny().orElseThrow(NoSuchElementException::new); // find for set
        // test for find
        assertAll(
                () -> assertSame(appInMap, appInList),
                () -> assertSame(appInList, appInGenerics),
                () -> assertSame(appInGenerics, appInSet),
                () -> assertSame(appInSet, appInMap)
        );
    }

    @Test void testRemove() {
        App appToRemove = this.appMap.get(10);
        this.appMap.remove(10); // remove from map
        this.appList.remove(appToRemove); // remove from list
        this.appSet.remove(appToRemove); // remove from set
        this.generics.remove(appToRemove); // remove from generics

        // test for remove
        assertAll(
                () -> assertFalse(this.appMap.containsKey(10)),
                () -> assertFalse(this.appList.contains(appToRemove)),
                () -> assertFalse(this.appSet.contains(appToRemove)),
                () -> assertFalse(this.generics.contains(appToRemove))
        );
    }
}
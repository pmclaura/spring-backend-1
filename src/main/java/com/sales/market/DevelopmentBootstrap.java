/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.model.*;
import com.sales.market.repository.BuyRepository;
import com.sales.market.repository.EmployeeRepository;
import com.sales.market.service.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final BuyRepository buyRepository;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ItemService itemService;
    private final ItemInstanceService itemInstanceService;
    private EmployeeRepository employeeRepository;
    private UserService userService;
    private RoleService roleService;
    private ItemInventoryService itemInventoryService;

    SubCategory beverageSubCat = null;

    // injeccion evita hacer instancia   = new Clase();
    // bean pueden tener muchos campos y otros beans asociados


    public DevelopmentBootstrap(BuyRepository buyRepository, CategoryService categoryService,
                                SubCategoryService subCategoryService, ItemService itemService, ItemInstanceService itemInstanceService,
                                EmployeeRepository employeeRepository, UserService userService, RoleService roleService, ItemInventoryService itemInventoryService) {
        this.buyRepository = buyRepository;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.itemService = itemService;
        this.itemInstanceService = itemInstanceService;
        this.employeeRepository = employeeRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.itemInventoryService = itemInventoryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
        /*   duplicacion de codigo
        Buy buy = new Buy();
        buy.setValue(BigDecimal.TEN);
        buyRespository.save(buy);
        Buy buy2 = new Buy();
        buy2.setValue(BigDecimal.ONE);
        buyRespository.save(buy);*/

        persistBuy(BigDecimal.TEN);
        persistBuy(BigDecimal.ONE);
        persistCategoriesAndSubCategories();
        Item maltinItem = persistItems(beverageSubCat,"B-MALTIN", "MALTIN");
        persistItemInstances(maltinItem);
        persistsItemsAndItemInventory();
        initializeRoles();
        initializeEmployees();
    }

    private void initializeRoles() {
        createRole(RoleType.ADMIN.getId(), RoleType.ADMIN.getType());
        createRole(RoleType.GENERAL.getId(), RoleType.GENERAL.getType());
        createRole(RoleType.SUPERVISOR.getId(), RoleType.SUPERVISOR.getType());
    }

    private Role createRole(long id, String roleName) {
        Role role = new Role();
        role.setId(id);
        role.setName(roleName);
        roleService.save(role);
        return role;
    }

    private void initializeEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            createEmployee("Mariel", "Lima", "pmclaura@gmail.com", false, 3L);
            createEmployee("Ariel", "Terceros", "ariel@gmail.com", false, 1L);
            createEmployee("Susana", "Lopes", "edson@gmail.com", true, 2L);
        }
    }

    private void createEmployee(String firstName, String lastName, String email, boolean system, Long idRole) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employeeRepository.save(employee);
        createUser(email, employee, system, idRole);
    }

    private void createUser(String email, Employee employee, boolean system, Long idRole) {
        User user = new User();
        Role role = new Role();
        HashSet<Role> roles = new HashSet<>();

        user.setEmail(email);
        user.setEnabled(true);
        user.setSystem(system);
        user.setPassword("$2a$10$XURPShQNCsLjp1ESc2laoObo9QZDhxz73hJPaEv7/cBha4pk0AgP.");
        user.setEmployee(employee);

        role.setId(idRole);
        roles.add(role);
        user.setRoles(roles);
        userService.save(user);
    }


    private void persistItemInstances(Item maltinItem) {
        ItemInstance maltinItem1 = createItem(maltinItem, "SKU-77721106006158", new BigDecimal(10));
        ItemInstance maltinItem2 = createItem(maltinItem, "SKU-77721106006159", new BigDecimal(10));
        ItemInstance maltinItem3 = createItem(maltinItem, "SKU-77721106006160", new BigDecimal(10));
        ItemInstance maltinItem4 = createItem(maltinItem, "SKU-77721106006161", new BigDecimal(10));
        itemInstanceService.save(maltinItem1);
        itemInstanceService.save(maltinItem2);
        itemInstanceService.save(maltinItem3);
        itemInstanceService.save(maltinItem4);
    }

    private ItemInstance createItem(Item maltinItem, String sku, BigDecimal price) {
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setItem(maltinItem);
        itemInstance.setFeatured(true);
        itemInstance.setPrice(price);
        itemInstance.setIdentifier(sku);
        itemInstance.setItemInstanceStatus(ItemInstanceStatus.AVAILABLE);
        return itemInstance;
    }

    private void persistsItemsAndItemInventory(){
        Item item1 = persistItems(beverageSubCat, "B-MALTIN", "MALTIN");
        persistItemInventory(item1, new BigDecimal(2), new BigDecimal(10), new BigDecimal(50));
        Item item2 = persistItems(beverageSubCat, "B-PEPSI", "PEPSI");
        persistItemInventory(item2, new BigDecimal(25), new BigDecimal(10), new BigDecimal(50));
        Item item3 = persistItems(beverageSubCat, "B-7UP", "7UP");
        persistItemInventory(item3, new BigDecimal(10), new BigDecimal(10), new BigDecimal(50));
        Item item4 = persistItems(beverageSubCat, "B-COKE", "COKE");
        persistItemInventory(item4, new BigDecimal(40), new BigDecimal(4), new BigDecimal(50));
    }
    private Item persistItems(SubCategory subCategory, String code, String name) {
        Item item = new Item();
        item.setCode(code);
        item.setName(name);
        item.setSubCategory(subCategory);
        /*try {
            item.setImage(ImageUtils.inputStreamToByteArray(getResourceAsStream("/images/maltin.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return itemService.save(item);
    }

    private ItemInventory persistItemInventory(Item item, BigDecimal stockQuantity,
                                               BigDecimal lowerBound, BigDecimal upperBound){

        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setStockQuantity(stockQuantity);
        itemInventory.setLowerBoundThreshold(lowerBound);
        itemInventory.setUpperBoundThreshold(upperBound);
        return itemInventoryService.save(itemInventory);
    }

    private String getResourceAsString(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private InputStream getResourceAsStream(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void persistCategoriesAndSubCategories() {
        Category category = persistCategory();
        persistSubCategory("SUBCAT1-NAME", "SUBCAT1-CODE", category);
        beverageSubCat = persistSubCategory("BEVERAGE", "BEVERAGE-CODE", category);
    }

    private Category persistCategory() {
        Category category = new Category();
        category.setName("CAT1-NAME");
        category.setCode("CAT1-CODE");
        return categoryService.save(category);
    }

    private SubCategory persistSubCategory(String name, String code, Category category) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCode(code);
        subCategory.setCategory(category);
        return subCategoryService.save(subCategory);
    }

    private void persistBuy(BigDecimal value) {
        Buy buy = new Buy();
        buy.setValue(value);
        buyRepository.save(buy);
    }
}

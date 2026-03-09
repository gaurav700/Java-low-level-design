package com.pm;


import com.pm.DependencyInjectionPrinciple.OrderService;
import com.pm.DependencyInjectionPrinciple.impl.MYSqlDB;
import com.pm.DependencyInjectionPrinciple.impl.PostgreSQLDB;
import com.pm.InterfaceSegregationPrinciple.entity.Role;
import com.pm.InterfaceSegregationPrinciple.impl.AdminUserService;
import com.pm.InterfaceSegregationPrinciple.impl.BasicUserService;
import com.pm.InterfaceSegregationPrinciple.impl.FullUserService;
import com.pm.LiskovSubstitutionPrinciple.Shape;
import com.pm.LiskovSubstitutionPrinciple.impl.Rectangle;
import com.pm.LiskovSubstitutionPrinciple.impl.Square;
import com.pm.OpenAndClosedPrinciple.Impl.ExpressShipping;
import com.pm.OpenAndClosedPrinciple.ShippingCostCalulator;
import com.pm.SingleResponsbilityPrinciple.InventoryManger;
import com.pm.SingleResponsbilityPrinciple.NotificationService;
import com.pm.SingleResponsbilityPrinciple.OrderProcessor;

public class Main {
    public static void main(String[] args) {

        System.out.println("------------------Single responsbility principle------------------------");
        InventoryManger inventory = new InventoryManger();
        NotificationService notifications = new NotificationService();
        OrderProcessor processor = new OrderProcessor(inventory, notifications);
        processor.placeOrder("LAPTOP", 1, "alice@example.com");
        System.out.println("------------------------------------------------------------------------");

        System.out.println();

        System.out.println("------------------Open/closed principle---------------------------------");
        ShippingCostCalulator shippingCostCalulator = new ShippingCostCalulator(new ExpressShipping());
        System.out.println("Shipping cost: "+shippingCostCalulator.calculate(800));
        System.out.println("------------------------------------------------------------------------");

        System.out.println();

        System.out.println("------------------Liskov substitution principle-------------------------");
        Shape rectangle = new Rectangle(5, 10);
        Shape square = new Square(5);
        System.out.println("Rectangle area: " + (int) rectangle.getArea());
        System.out.println("Square area: " + (int) square.getArea());
        System.out.println("------------------------------------------------------------------------");

        System.out.println();

        System.out.println("------------------Interface segregation principle-----------------------");
        BasicUserService basicUserService = new BasicUserService();
        AdminUserService adminUserService = new AdminUserService(basicUserService);
        FullUserService fullUserService = new FullUserService(adminUserService, basicUserService);

        System.out.println(basicUserService.CreateUser("Gaurav", "gauravbtech9589@gmail.com", Role.NORMAL_USER));


        System.out.println("------------------------------------------------------------------------");

        System.out.println();

        System.out.println("------------------Dependency Injection principle------------------------");
        OrderService service = new OrderService(new PostgreSQLDB());
        service.placeOrder("ORD-001", "{ item: 'Widget', qty: 3 }");
        String order = service.getOrder("ORD-001");
        System.out.println("Order: " + order);
        System.out.println("------------------------------------------------------------------------");
    }
}
package org.example;

import javax.rmi.CORBA.Stub;
import javax.rmi.CORBA.StubDelegate;
import javax.sound.sampled.Port;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Program
{
    private static ProductDAO productDAO;
    public static void main(String[] args )
    {
        productDAO = new ProductDAO(args[0], args[1], args[2]);
        Scanner sc = new Scanner(System.in);
        int id, quantity;
        float price;
        String name;
        System.out.println("==================== Program Management To Product ====================");
        System.out.println("1. Read Product List");
        System.out.println("2. Read A Product By Input ID");
        System.out.println("3. Add A New Product");
        System.out.println("4. Update A Product");
        System.out.println("5. Delete A Product By ID");
        System.out.println("6. Exit");
        System.out.println("========================================================================");
        System.out.println("Enter Choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("List Product: ");
                readALlProduct();
                break;
            case 2:
                System.out.println("Enter Product ID: ");
                id = sc.nextInt();
                findProductByID(id);
                break;
            case 3:
                System.out.println("Enter Product ID: ");
                id = sc.nextInt();
                System.out.println("Enter Product Name: ");
                name = sc.next();
                System.out.println("Enter Product Price: ");
                price = sc.nextFloat();
                System.out.println("Enter Product Quantity: ");
                quantity = sc.nextInt();
                addProduct(new Product(id,name, price, quantity));
                break;
            case 4:
                System.out.println("Enter Product ID: ");
                id = sc.nextInt();
                System.out.println("Enter Product Name: ");
                name = sc.nextLine();
                System.out.println("Enter Product Price: ");
                price = sc.nextFloat();
                System.out.println("Enter Product Quantity: ");
                quantity = sc.nextInt();
                updateProduct(new Product(id,name, price, quantity));
                break;
            case 5:
                System.out.println("Enter Product ID: ");
                id = sc.nextInt();
                deleteProduct(id);
                break;
            default:
                break;
        }
    }

    public static void readALlProduct() {
        List<Product> productArrayList = productDAO.readAll();
        for (Product product: productArrayList){
            System.out.println(product.toString()) ;
        }
    }

    public static void addProduct(Product product) {
        productDAO.add(product);
    }

    public static void  findProductByID(Integer id){
        Product product = productDAO.read(id);
        System.out.println(product.toString());
    }

    public static void deleteProduct(Integer id) {
        productDAO.delete(id);
    }

    public static void updateProduct(Product product){
        productDAO.update(product);
    }
}

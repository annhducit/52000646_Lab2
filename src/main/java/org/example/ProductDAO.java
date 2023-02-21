package org.example;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {
    private String url;
    private String username;
    private String pass;

    public ProductDAO(String url, String username, String pass) {
        this.url = url;
        this.username = username;
        this.pass = pass;
    }

    public Integer add(Product item) {
        try{
            Connection connection= MySQLConection.getConnection(url, username, pass);
            Statement st=connection.createStatement();
            String sql="INSERT INTO product(id, name, price, quantity)" + "VALUES ('" + item.getId() + "' , '" + item.getName() + "' , " + item.getPrice() + " , " + item.getQuantity() +")";
            int ketqua =st.executeUpdate(sql);
            System.out.println("Result: "+ketqua );
            MySQLConection.closeConnection(connection);
        } catch (SQLException e) {
        e.printStackTrace();

    }
        return 0;
    }

    public List<Product> readAll() {
        List<Product> products = new ArrayList<Product>();
        Connection connection= MySQLConection.getConnection(url, username, pass);
        try {
            Statement st=connection.createStatement();

            String str = "SELECT * FROM Product";
            ResultSet result = st.executeQuery(str);
            System.out.println("List Product: ");
            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                float price = result.getFloat("price");
                int quantity = result.getInt("quantity");
                Product s = new Product(id, name, price, quantity);
                products.add(s);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return products;
    }


    public Product read(Integer id) {
        Connection connection = MySQLConection.getConnection(url, username, pass);
        Product s = null;
        try {
            Statement statement = connection.createStatement();
            String str = "SELECT * FROM Product"+" WHERE id = "+id;
            ResultSet resultSet = statement.executeQuery(str);

            while(resultSet.next()) {
                int idProduct = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price =resultSet.getFloat("price");
                int quantity = resultSet.getInt("quantity");
                s = new Product(idProduct, name, price, quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return s;
    }


    public boolean update(Product item) {
        Connection connection = MySQLConection.getConnection(url, username, pass);
        try {
            String str = "UPDATE Product SET name=?, price=?, quantity=? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setFloat(2, item.getPrice());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.setInt(4, item.getId());
            preparedStatement.executeUpdate();
            System.out.print("Update Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public boolean delete(Integer id) {
        Connection connection = MySQLConection.getConnection(url, username, pass);
        try {
            String str = "DELETE FROM Product"+" WHERE id = "+id;
            Statement statement  =   connection.createStatement();
            statement.executeUpdate(str);
//            ResultSet resultSet = statement.executeQuery(str);
            System.out.print("Delete Product Successfully: ");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.curso.modelo.dao;

import com.ipn.mx.curso.modelo.dto.Producto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Carlo Barranco
 */
public class ProductoDAO {

    private static final String SQL_INSERT = "call spCreate(?, ? , ?, ?);";
    private static final String SQL_UPDATE = "call spUpdate(?, ?, ?, ?, ?);";
    private static final String SQL_DELETE = "call spDelete(?);";
    
    private Connection con;
    
    public void create(Producto p) throws SQLException{
        obtenerConexion();
        CallableStatement cs;
        cs = con.prepareCall(SQL_INSERT);
        cs.setString(1, p.getNombre());
        cs.setString(2, p.getDesc());
        cs.setDouble(3, p.getPrecio());
        cs.setInt(4, p.getExistencia());
        cs.executeUpdate();
    }
    
    public void update(Producto p) throws SQLException{
        obtenerConexion();
        CallableStatement cs;
        cs = con.prepareCall(SQL_INSERT);
        cs.setInt(1, p.getIdProducto());
        cs.setString(2, p.getNombre());
        cs.setString(3, p.getDesc());
        cs.setDouble(4, p.getPrecio());
        cs.setInt(5, p.getExistencia());
        cs.executeUpdate();
    }
    
    public void delete(Producto p) throws SQLException{
        obtenerConexion();
        CallableStatement cs;
        cs = con.prepareCall(SQL_DELETE);
        cs.setInt(1, p.getIdProducto());
        cs.executeUpdate();
    }
    
    private void obtenerConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Curso", "Carlo", "cbarranco03");
        } catch (ClassNotFoundException | SQLException ex) {
            
        }
    }
    public static void main(String[] args) {
        try {
            Producto p = new Producto();
            ProductoDAO dao = new ProductoDAO();
            p.setNombre("Galaxy S6");
            p.setDesc("Color plateado 128 GB");
            p.setPrecio(18500.00);
            p.setExistencia(100);
            p.setIdProducto(1);
            dao.
            //dao.create(p);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}


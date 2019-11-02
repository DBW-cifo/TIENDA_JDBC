package PRODUCTOS_PROVEEDORES;
import java.util.Scanner;
import java.sql.*;
public class MANT_PROD_PROOV {

public static void main(String[] args) {
String cadConexion = "jdbc:mysql://localhost:3306/"; 
String bd = "tienda";
String usuario = "root";
String pass = "";

int tab1, tab2;
try {
String tabla="";
Scanner qtabla = new Scanner(System.in);
do {
System.out.println("Indica donde realizar mantenimiento:");
System.out.println("   1.- PRODUCTOS");
System.out.println("   2.- PROVEEDORES");
System.out.println("   0.- Salir");
tab1 = Integer.parseInt(qtabla.nextLine());
switch (tab1) { 
case 1:
    tabla = "producto";
    break;
case 2:
    tabla = "proveedor";
    break;
}
int sw = 0;
while ((tab1 == 1 || tab1 == 2) & sw == 0) {
try {
Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
Connection con = DriverManager.getConnection(cadConexion + bd, usuario, pass);
/*Statement stmt;*/
PreparedStatement pstmt;
ResultSet rs;
Scanner entrada = new Scanner(System.in);
int id;
String nombre;
String direccion;
int idproveedor;
String nif;

do {
System.out.println("Escoje opción:");
System.out.println("   1.- Consultar un registro");
System.out.println("   2.- Crear un nuevo registro");
System.out.println("   3.- Modificar un registro");
System.out.println("   4.- Eliminar un registro");
System.out.println("   0.- Salir");
tab2 = Integer.parseInt(entrada.nextLine());

switch (tab2) { 
case 1:
	System.out.println("Introduzca el ID del "+tabla+" que quiere consultar: ");
    id = Integer.parseInt(entrada.nextLine());
    pstmt = con.prepareStatement("select * from "+tabla+" where id"+tabla+"=?");
    pstmt.setInt(1, id);
    rs = pstmt.executeQuery();
    if (rs.next()) {
    	System.out.println("ID:" + rs.getString("ID"+tabla+"") + " | Nombre: " + rs.getString("nombre"));
    } else {
        System.out.println("No encontrado");
    }
    break;
case 2:
	System.out.println("Introduzca el nombre del "+tabla+": ");
	nombre = entrada.nextLine();
	if (tab1==1) {
		System.out.println("Introduzca ID del proveedor: ");
		idproveedor = entrada.nextInt();
		pstmt = con.prepareStatement("insert into "+tabla+" (nombre, idproveedor) values('"+nombre+"', "+idproveedor+")");
		pstmt.execute();
	}
	if (tab1==2) {
	    System.out.println("Introduzca el nif del "+tabla+": ");
	    nif = entrada.nextLine();
	    System.out.println("Introduzca dirección del "+tabla+": ");
	    direccion = entrada.nextLine();
	    pstmt = con.prepareStatement("insert into "+tabla+" (nombre, nif, direccion) values('"+nombre+"', '"+nif+"', '"+direccion+"')");
	    pstmt.execute();
	}
	System.out.println(tabla+" insertado"); 
	break;    
case 3:
    System.out.println("Introduzca el ID del "+tabla+" que quiere modificar: ");
    id = Integer.parseInt(entrada.nextLine());
    System.out.println("Introduzca el nuevo nombre del "+tabla+": ");
    nombre = entrada.nextLine();
    if (tab1==1) {
		System.out.println("Introduzca nuevo ID del proveedor: ");
		idproveedor = entrada.nextInt();
		pstmt = con.prepareStatement("update "+tabla+" set nombre='"+nombre+"' , idproveedor="+idproveedor+" where id"+tabla+"=?");
		pstmt.setInt(1, id);
		pstmt.execute();
	}
	if (tab1==2) {
	   System.out.println("Introduzca el nuevo nif del "+tabla+": ");
	   nif = entrada.nextLine();
	   System.out.println("Introduzca nueva dirección del "+tabla+": ");
	   direccion = entrada.nextLine();
	   pstmt = con.prepareStatement("update "+tabla+" set nombre='"+nombre+"' , nif='"+nif+"' , direccion='"+direccion+"' where id"+tabla+"=?");
	   pstmt.setInt(1, id);
	   pstmt.execute();
	}
    System.out.println(tabla+" modificado"); 
    break;
case 4:
    System.out.println("Introduzca el ID del "+tabla+" que quiere eliminar: ");
    id = Integer.parseInt(entrada.nextLine());
    pstmt = con.prepareStatement("delete from "+tabla+" where id"+tabla+"=?");
    pstmt.setInt(1, id);
    pstmt.execute();
    System.out.println(tabla+" eliminado"); 
    break;
    }
}
while (tab2 != 0 & tab2 < 5);
con.close();
entrada.close();
sw = 1;
tab1 = 0;
}
catch (Exception e) {
    /*System.out.println(e);*/ 
  }
}
}
while (tab1 != 0 & tab1 < 3);
qtabla.close();
}
catch (Exception e) {
    System.out.println(e);   
}
/* FINAL */
} 
}
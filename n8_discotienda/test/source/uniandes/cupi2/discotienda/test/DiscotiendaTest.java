package uniandes.cupi2.discotienda.test;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import uniandes.cupi2.discotienda.mundo.Disco;
import uniandes.cupi2.discotienda.mundo.Discotienda;
import uniandes.cupi2.discotienda.mundo.Cancion;
import uniandes.cupi2.discotienda.mundo.PersistenciaException;

public class DiscotiendaTest {
	private Discotienda discotienda;

	@BeforeEach
	public void setUp() throws Exception {
		try {
			 discotienda = new Discotienda("data/discotienda.discos");
			 //System.out.println("X");
		}
		catch (Exception e){
			System.out.print("ha ocurrido una excepción al crear la discotienda");
		}
	}
	
	

	@Test
	public  void testDarDisco() {
		try {
			
			assertEquals("Is This It", discotienda.darDisco("Is This It").darNombreDisco(), " fallo en el metodo dar disco");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public  void testAgregarDisco() {
		try {
			//assertEquals(true, discotienda., " fallo en el metodo salvar discotienda");
			discotienda.agregarDisco("a", "a", "a","./data/imagenes/elephant.jpg");
			String nombreDisco= discotienda.darDisco("a").darNombreDisco();
		
			
			assertEquals("a", nombreDisco, " fallo en el metodo agregar disco");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	
	@Test
	public  void testAgregarCancionADisco() {
		try {
			 discotienda.agregarCancionADisco("Is This It", "in the end", 1, 1, 1, 1, 1);
			assertEquals("in the end",discotienda.darDisco("Is This It").darCancion("in the end").darNombre(), " fallo en el metodo agregar cancion a disco");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Comprueba que el email se aigual al indicado
	//por supuesto, en el camino comprueba que la cancion exista y que la factura haya sido creada, entre otras cosas.
	@Test
	public  void testVenderCancion() {
		try {
			Disco d= discotienda.darDisco("Push the Button");
			Cancion c = d.darCancion("Galvanize");
			String ruta_Archivo= "./data/facturas/" +  discotienda.venderCancion(d, c, "example@gmail.com", "./data/facturas");
			String email = "email nulo";
			
			try {
	            // Crear el archivo
	            File file = new File(ruta_Archivo);

	            // Comprobar si el archivo existe
	            if (!file.exists()) {
	                file.createNewFile();
	                System.out.println("Archivo creado exitosamente.");
	            }

	            // Leer el archivo
	            FileReader fileReader = new FileReader(file);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            // Leer la tercera línea
	            String line;
	            int lineNumber = 1;
	            int targetLineNumber = 3;
	            
	            while ((line = bufferedReader.readLine()) != null) {
	                if (lineNumber == targetLineNumber) {
	                    // Eliminar espacios y tabulaciones
	                    String cleanLine = line.replaceAll("[\\s\\t]", "");
	                    
	                    // Separar por el caracter ":"
	                    String[] parts = cleanLine.split(":");
	                    email=  parts[1];
	                    
	                    
	                    break;
	                }

	                lineNumber++;
	            }

	            // Cerrar el lector
	            bufferedReader.close();
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    
			 
			assertEquals("example@gmail.com", email, " fallo el metodo vender cancion");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public  void testDarDiscos() {
		try {
			 ArrayList listaDiscos= discotienda.darDiscos();
			 ArrayList listEsperada= new ArrayList();
			 listEsperada.add("Confessions on a Dance Floor");
			 listEsperada.add("Elephant");
			 listEsperada.add("Is This It");
			 listEsperada.add("Push the Button");
			assertEquals(listaDiscos, listEsperada, " fallo en el metodo dar discos");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Comprueba que el email se aigual al indicado
	//por supuesto, al mismo tiempo comprueba que la cancion exista, que la factura haya sido creada. 
	//ESTO ÚLTIMO TAMBIÉN PRUEBA EL MÉTODO GENERAR FACTURA POR LO QUE NO HACE FALTA VOLVER A PROBARLO
		
	@Test
		public  void testVenderListaCancionesyGenerarFactura() {
			
			try {
				File archivoPedido= new File("./data/pedido.txt");
				
				String ruta_Archivo= "./data/facturas/" +  discotienda.venderListaCanciones(archivoPedido, "./data/facturas/");
				String email = "email nulo";
				
				try {
		            // Crear el archivo
		            File file = new File(ruta_Archivo);

		            // Comprobar si el archivo existe
		            if (!file.exists()) {
		                file.createNewFile();
		                System.out.println("Archivo creado exitosamente.");
		            }

		            // Leer el archivo
		            FileReader fileReader = new FileReader(file);
		            BufferedReader bufferedReader = new BufferedReader(fileReader);

		            // Leer la tercera línea
		            String line;
		            int lineNumber = 1;
		            int targetLineNumber = 3;
		            
		            while ((line = bufferedReader.readLine()) != null) {
		                if (lineNumber == targetLineNumber) {
		                    // Eliminar espacios y tabulaciones
		                    String cleanLine = line.replaceAll("[\\s\\t]", "");
		                    
		                    // Separar por el caracter ":"
		                    String[] parts = cleanLine.split(":");
		                    email=  parts[1];
		                    
		                    
		                    break;
		                }

		                lineNumber++;
		            }

		            // Cerrar el lector
		            bufferedReader.close();
		            
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    
				 
				assertEquals("pedro@abc.edu", email, " fallo el metodo vender lista canciones");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	

	@Test
	public  void testValidarEmail() {
		try {
			
			assertEquals(true, discotienda.validarEmail("hola@gmail.com"), " fallo en el metodo validar email con email válido");
			assertNotEquals(true, discotienda.validarEmail("emailFALSO >:)"), " fallo en el metodo validar email con imal inválido");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//CREA UNA NUEVA CANCIÓN Y SALVA LA DISCOTIENDA, BORRA LA DISCOTIENDA Y LA VUELVE A CREAR
	//LA NUEVA DISCOTIENDA SE CREA CON EL ARCHIVO SALVADO, POR LO TANTO SI SE SALVÓ CORRECTAMENTE,
	//EL NOMBRE DE LA ÚLTIMA CANCIÓN ES IGUAL A LA CREADA EN LA PRUEBA
	
	//ADVERTENCIA: ESTO SOLO FUNCIONA UNA VEZ CON LA MISMA CANCIÓN, PARA MÁS PRUEBAS SE DEBEN HACER
	//MÁS CANCIONES YA QUE EL SISTEMA NO DEJA CREAR 2 CANCIONES IGUALES
	
	@Test
	public  void testSalvarDiscotienda() {
		try {
			discotienda.agregarCancionADisco("Elephant", "ultimaCancionPrueba0", 1, 1, 1, 1, 1);
			discotienda.salvarDiscotienda();
			discotienda= new Discotienda("data/discotienda.discos");
			String ultimoNombreCancion= discotienda.darDisco("Elephant").darCancion("ultimaCancionPrueba0").darNombre();
			assertEquals("ultimaCancionPrueba0", ultimoNombreCancion, " fallo en el metodo salvar discotienda");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		}
	

	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	@DisplayName("Métodos simples")
	public  void test1() {
		try {
			
			assertEquals("Lista orgenada", discotienda.metodo1(), " fallo en el metodo 1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void test2() {
		try {
			
			assertEquals("respuesta 2", discotienda.metodo2(), " fallo en el metodo 2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void test3() {
		try {
			
			assertEquals("respuesta 3", discotienda.metodo3(), " fallo en el metodo 3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void test4() {
		try {
			
			assertEquals("respuesta 4", discotienda.metodo4(), " fallo en el metodo 4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void test5() {
		try {
			
			assertEquals("respuesta 5", discotienda.metodo5(), " fallo en el metodo 5");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public  void test6() {
		try {
			
			assertEquals("respuesta 6", discotienda.metodo6(), " fallo en el metodo 6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

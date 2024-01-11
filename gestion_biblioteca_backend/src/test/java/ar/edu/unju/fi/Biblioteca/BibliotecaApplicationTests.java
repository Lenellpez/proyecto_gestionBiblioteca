package ar.edu.unju.fi.Biblioteca;

import org.springframework.boot.test.context.SpringBootTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@SpringBootTest 
@Suite
@SelectClasses({LibroTest.class,LectorTest.class,PrestamoTest.class})
class BibliotecaApplicationTests {
}

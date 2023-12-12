package alumno.unlam.edu.ar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test {

	@org.junit.Test
	public void queSePuedaExtraerDineroDeUnaCuentaSueldo() {
		Long cbu = 566777888235675638L;
		Double saldo = 100000.00;
		
		Banco rio = new Banco();
		Cuenta cuentaSueldo = new CuentaSueldo(cbu, saldo);
		
		try {
			rio.registrarCuenta(cuentaSueldo);
		} catch (CuentaRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rio.extraerDinero(cbu, 10000.00);
		} catch (CuentaInexistenteException | SaldoInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double saldoEsperado = 90000.00;
		Double saldoObtenido = cuentaSueldo.getSaldo();
		
		assertEquals(saldoEsperado, saldoObtenido);
	}
	
	@org.junit.Test(expected = CuentaInexistenteException.class)
	public void queNoSePuedaExtraerDineroDeUnaCuentaInexistente() throws CuentaInexistenteException {
		Long cbu = 566777888235675638L;
		Double saldo = 100000.00;
		
		Banco rio = new Banco();
		Cuenta cuentaSueldo = new CuentaSueldo(cbu, saldo);
		
		try {
			rio.registrarCuenta(cuentaSueldo);
		} catch (CuentaRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rio.extraerDinero(7249238479238L, 10000.00);
		} catch (CuentaInexistenteException | SaldoInsuficienteException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			throw new CuentaInexistenteException();
		}
		
		Double saldoEsperado = 90000.00;
		Double saldoObtenido = cuentaSueldo.getSaldo();
		
		assertEquals(saldoEsperado, saldoObtenido);
	}
	
	@org.junit.Test
	public void queAlExtraerDineroDeUnaCajaDeAhorroCobreUnAdicional() {
		Long cbu = 566777888235675638L;
		Double saldo = 100000.00;
		
		Banco rio = new Banco();
		Cuenta cajaDeAhorro = new CajaDeAhorro(cbu, saldo);
		
		try {
			rio.registrarCuenta(cajaDeAhorro);
		} catch (CuentaRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rio.extraerDinero(cbu, 10000.00);
			rio.extraerDinero(cbu, 10000.00);
			rio.extraerDinero(cbu, 10000.00);
			rio.extraerDinero(cbu, 10000.00);
			rio.extraerDinero(cbu, 10000.00);
			rio.extraerDinero(cbu, 10000.00);
		} catch (CuentaInexistenteException | SaldoInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double saldoEsperado = 39994.00;
		Double saldoObtenido = cajaDeAhorro.getSaldo();
		
		assertEquals(saldoEsperado, saldoObtenido);
	}
	
	@org.junit.Test
	public void queAlExtraerDineroDeUnaCuentaCorrienteCobreUnInteresPorUsarElDescubierto() {
		Long cbu = 566777888235675638L;
		Double saldo = 100000.00, descubierto = 10000.00;
		
		Banco rio = new Banco();
		Cuenta cuentaCorriente = new CuentaCorriente(cbu, saldo, descubierto);
		
		try {
			rio.registrarCuenta(cuentaCorriente);
		} catch (CuentaRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 5000.00);
		} catch (CuentaInexistenteException | SaldoInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double saldoEsperado = 250.00;
		Double saldoObtenido = ((CuentaCorriente)cuentaCorriente).getComisionAPagar();
		
		assertEquals(saldoEsperado, saldoObtenido);
	}
	
	@org.junit.Test
	public void queSePuedaObtenerUnaListaOrdenadaDeTransacciones() {
		Long cbu = 566777888235675638L;
		Double saldo = 100000.00, descubierto = 10000.00;
		
		Banco rio = new Banco();
		Cuenta cuentaCorriente = new CuentaCorriente(cbu, saldo, descubierto);
		
		try {
			rio.registrarCuenta(cuentaCorriente);
		} catch (CuentaRegistradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 20000.00);
			rio.extraerDinero(cbu, 5000.00);
		} catch (CuentaInexistenteException | SaldoInsuficienteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Transaccion> transaccionesOrdenadas = new ArrayList<>(rio.getTransacciones());
		System.out.println(rio.getTransacciones());
		Double saldoEsperado = 250.00;
		Double saldoObtenido = ((CuentaCorriente)cuentaCorriente).getComisionAPagar();
		
		assertEquals(transaccionesOrdenadas.get(0).getId(), (Integer)1);
		assertEquals(transaccionesOrdenadas.get(1).getId(), (Integer)2);
		assertEquals(transaccionesOrdenadas.get(2).getId(), (Integer)3);
		assertEquals(transaccionesOrdenadas.get(3).getId(), (Integer)4);
		assertEquals(transaccionesOrdenadas.get(4).getId(), (Integer)5);
		assertEquals(transaccionesOrdenadas.get(5).getId(), (Integer)6);
	}

}

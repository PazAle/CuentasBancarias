package alumno.unlam.edu.ar;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Banco {
	
	private Set<Cuenta> cuentasRegistradas;
	private Set<Transaccion> transacciones;
	
	public Banco() {
		this.cuentasRegistradas = new HashSet<>();
		this.transacciones = new TreeSet<>();
	}
	
	public Set<Cuenta> getCuentasRegistradas() {
		return cuentasRegistradas;
	}

	public void setCuentasRegistradas(Set<Cuenta> cuentasRegistradas) {
		this.cuentasRegistradas = cuentasRegistradas;
	}

	public Set<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public void registrarCuenta(Cuenta cuenta) throws CuentaRegistradaException {
		Boolean cuentaRegistrada = existeCuenta(cuenta);
		if(cuentaRegistrada) {
			throw new CuentaRegistradaException("La cuenta que intenta registrar ya existe en el sistema");
		} else {
			this.agregarCuenta(cuenta);
		}
	}

	private void agregarCuenta(Cuenta cuenta) {
		this.getCuentasRegistradas().add(cuenta);
		
	}

	private Boolean existeCuenta(Cuenta cuenta) {
		Boolean existe = false;
		if(this.getCuentasRegistradas().contains(cuenta)) {
			existe = true;
		}
		return existe;
	}

	public void extraerDinero(Long cbu, Double monto) throws CuentaInexistenteException, SaldoInsuficienteException {
		Cuenta cuenta = buscarCuentaPorCbu(cbu);
		cuenta.extraerDinero(monto);
		Integer idTransaccion = this.getTransacciones().size();
		idTransaccion++;
		registrarTransaccion(idTransaccion, Motivo.EXTRACCION, cuenta, monto);
		
	}

	private void registrarTransaccion(Integer id, Motivo motivo, Cuenta cuenta, Double monto) {
		this.transacciones.add(new Transaccion(id, motivo, cuenta, null, monto));
	}

	private Cuenta buscarCuentaPorCbu(Long cbu) throws CuentaInexistenteException {
		Cuenta cuentaBuscada = null;
		for(Cuenta cuenta: this.getCuentasRegistradas()) {
			if(cuenta.getCbu().equals(cbu)) {
				cuentaBuscada = cuenta;
			}
		}
		
		if(cuentaBuscada == null) {
			throw new CuentaInexistenteException("No existe una cuenta con el CBU ingresado");
		}
		
		return cuentaBuscada;
	}

}

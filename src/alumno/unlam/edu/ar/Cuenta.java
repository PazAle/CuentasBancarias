package alumno.unlam.edu.ar;

public class Cuenta {

	private Long cbu;
	private Double saldo;
	
	public Cuenta(Long cbu, Double saldo) {
		this.cbu = cbu;
		this.saldo = saldo;
	}

	public Long getCbu() {
		return cbu;
	}

	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Boolean extraerDinero(Double monto) throws SaldoInsuficienteException {
		Boolean puedeExtraer = tieneSaldo(monto);
		Double saldoActual = this.getSaldo();
		if(puedeExtraer) {
			saldoActual -= monto;
			this.setSaldo(saldoActual);
		}
		return puedeExtraer;
		
	}

	private Boolean tieneSaldo(Double monto) throws SaldoInsuficienteException {
		Double saldoActual = this.getSaldo();
		Boolean sePudoExtraer = false;
		if(saldoActual >= monto) {
			sePudoExtraer = true;
		} else {
			throw new SaldoInsuficienteException("No dispone de saldo suficiente para realizar esta operación");
		}
		return sePudoExtraer;
	}

	@Override
	public String toString() {
		return "Cuenta [cbu=" + cbu + "]";
	}

	public void transferir(Cuenta cuentaOrigen, Cuenta cuentaDestino, Double monto) throws SaldoInsuficienteException {
		Double saldoCuentaOrigen = cuentaOrigen.getSaldo();
		Double saldoCuentaDestino = cuentaDestino.getSaldo();
		if(saldoCuentaOrigen >= monto) {
			saldoCuentaDestino += monto;
			saldoCuentaOrigen -= monto;
			cuentaDestino.setSaldo(saldoCuentaDestino);
			cuentaOrigen.setSaldo(saldoCuentaOrigen);
		}
	}

	public void ingresarDinero(Double monto) {
		Double montoActual = this.getSaldo();
		montoActual += monto;
		this.setSaldo(montoActual);
		
	}
	
	 
}

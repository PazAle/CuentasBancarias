package alumno.unlam.edu.ar;

public class CajaDeAhorro extends Cuenta {

	private Integer cantidadDeExtracciones;
	private Double costoAdicional;
	
	public CajaDeAhorro(Long cbu, Double saldo) {
		super(cbu, saldo);
		this.cantidadDeExtracciones = 0;
		this.costoAdicional = 6.0;
	}
	
	public Integer getCantidadDeExtracciones() {
		return cantidadDeExtracciones;
	}

	public void setCantidadDeExtracciones(Integer cantidadDeExtracciones) {
		this.cantidadDeExtracciones = cantidadDeExtracciones;
	}

	public Double getCostoAdicional() {
		return costoAdicional;
	}

	public void setCostoAdicional(Double costoAdicional) {
		this.costoAdicional = costoAdicional;
	}

	@Override
	public Boolean extraerDinero(Double monto) throws SaldoInsuficienteException {
		Double saldoActual = this.getSaldo();
		Integer cantidadDeExtracciones = this.cantidadDeExtracciones;
		Double montoConAdicional = monto + this.getCostoAdicional();
		Boolean sePudo = false;
		if(cantidadDeExtracciones >= 5) {
			if(saldoActual >= montoConAdicional) {
				saldoActual -=montoConAdicional;
				cantidadDeExtracciones++;
				sePudo = true;
			} else {
				throw new SaldoInsuficienteException("No dispone de saldo suficiente para realizar esta operación");
			}
		}
		if(cantidadDeExtracciones <5) {
			if(saldoActual >= monto) {
				saldoActual -=monto;
				cantidadDeExtracciones++;
				sePudo = true;
			} else {
				throw new SaldoInsuficienteException("No dispone de saldo suficiente para realizar esta operación");
			}
		}
		
		this.setCantidadDeExtracciones(cantidadDeExtracciones);
		this.setSaldo(saldoActual);
		
		return sePudo;
	}

}

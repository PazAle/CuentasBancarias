package alumno.unlam.edu.ar;

public class Transaccion implements Comparable<Transaccion>{

	private Integer id;
	private Motivo motivo;
	private Cuenta cuentaOrigen;
	private Cuenta cuentaDestino;
	private Double monto;
	
	public Transaccion(Integer id, Motivo motivo, Cuenta cuentaOrigen, Cuenta cuentaDestino, Double monto) {
		super();
		this.id = id;
		this.motivo = motivo;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Motivo getMotivo() {
		return motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	@Override
	public int compareTo(Transaccion o) {
		// TODO Auto-generated method stub
		return this.getId().compareTo(o.getId());
	}
	
	
}

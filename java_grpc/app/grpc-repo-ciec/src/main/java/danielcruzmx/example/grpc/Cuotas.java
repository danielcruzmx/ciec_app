package danielcruzmx.example.grpc;

import java.util.Date;

public class Cuotas {

	private Integer id;
	private String tipo;
	private String condomino;
	private Date fecha;
	private String descripcion;
	private Double cargo;
	private Double abono;
	private Double saldo;

	public Double getCargo() {
		return cargo;
	}
	public void setCargo(Double cargo) {
		this.cargo = cargo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCondomino() {
		return condomino;
	}
	public void setCondomino(String condomino) {
		this.condomino = condomino;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getAbono() {
		return abono;
	}
	public void setAbono(Double abono) {
		this.abono = abono;
	}
	public String getDescripcion() {
		return getTipo().trim() + " " + descripcion.trim();
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}


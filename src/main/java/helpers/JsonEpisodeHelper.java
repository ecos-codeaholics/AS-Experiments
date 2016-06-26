package helpers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by snaphuman on 6/8/16.
 */
public class JsonEpisodeHelper {

	@SerializedName("episodeID")
	private int id;
	private int cedula;
	private String fecha;
	private String hora;
	private int nivelDolor;
	private String medicamento;
	private String actividad;

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getNivelDolor() {
		return nivelDolor;
	}

	public void setNivelDolor(int nivelDolor) {
		this.nivelDolor = nivelDolor;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

}

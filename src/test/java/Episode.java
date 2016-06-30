

import org.bson.types.ObjectId;

//import org.jongo.marshall.jackson.oid.MongoId;
//import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {

	private ObjectId _id;

	private long cedula;
	private String fecha;
	private String hora;
	private int nivelDolor;
	private int intensidad;

	public Episode() {
	}

	/**
	 * @return the cedula
	 */
	public long getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * @return the nivelDolor
	 */
	public int getNivelDolor() {
		return nivelDolor;
	}

	/**
	 * @param nivelDolor the nivelDolor to set
	 */
	public void setNivelDolor(int nivelDolor) {
		this.nivelDolor = nivelDolor;
	}

	/**
	 * @return the intensidad
	 */
	public int getIntensidad() {
		return intensidad;
	}

	/**
	 * @param intensidad the intensidad to set
	 */
	public void setIntensidad(int intensidad) {
		this.intensidad = intensidad;
	}

	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	
	
}

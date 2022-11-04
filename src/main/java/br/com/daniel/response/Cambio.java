package br.com.daniel.response;

import java.io.Serializable;
import java.util.Objects;

public class Cambio implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String from;
	private String to;
	private Double convertionFactor;
	private Double convertedValue;
	private String environment;

	public Cambio() {
		super();
	}

	public Cambio(Long id, String from, String to, Double convertionFactor, Double convertedValue,
			String environment) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.convertionFactor = convertionFactor;
		this.convertedValue = convertedValue;
		this.environment = environment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getConvertionFactor() {
		return convertionFactor;
	}

	public void setConvertionFactor(Double convertionFactor) {
		this.convertionFactor = convertionFactor;
	}

	public Double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cambio other = (Cambio) obj;
		return Objects.equals(id, other.id);
	}

}

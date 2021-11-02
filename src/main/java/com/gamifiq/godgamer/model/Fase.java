package com.gamifiq.godgamer.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Fase {
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime datafim;

	public Fase(Long id, String descricao, LocalDateTime dataInicio, LocalDateTime datafim) {
		this.id = id;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.datafim = datafim;
	}
	
	public Fase(String descricao, LocalDateTime dataInicio, LocalDateTime datafim) {
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.datafim = datafim;
	}

	public Fase() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDatafim() {
		return datafim;
	}

	public void setDatafim(LocalDateTime datafim) {
		this.datafim = datafim;
	}
	@Override
	public String toString() {
		return "Fase [id=" + id + ", descricao=" + descricao + ", datainIcio=" + dataInicio + ", datafim=" + datafim
				+ "]";
	}	

}

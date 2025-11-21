package com.example.sistemalavarapido.model;
public class Veiculo {
    private int id;
    private String modelo;
    private String placa;

    public Veiculo() {}

    public Veiculo(int id, String modelo, String placa) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    @Override
    public String toString() {
        return "ID: " + id;
    }
}

package models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Collection;

@Entity
@Cacheable(false)
@Table(name = "empleados", schema = "lab2marco")
public class EmpleadosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_empleado")
    private int idEmpleado;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "fechacontratacion")
    private Date fechacontratacion;
    @Basic
    @Column(name = "salario")
    private BigDecimal salario;
    @OneToMany(mappedBy = "empleadosByIdEmpleado")
    private Collection<VentasEntity> ventasByIdEmpleado;

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechacontratacion() {
        return fechacontratacion;
    }

    public void setFechacontratacion(Date fechacontratacion) {
        this.fechacontratacion = fechacontratacion;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadosEntity that = (EmpleadosEntity) o;

        if (idEmpleado != that.idEmpleado) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechacontratacion != null ? !fechacontratacion.equals(that.fechacontratacion) : that.fechacontratacion != null)
            return false;
        if (salario != null ? !salario.equals(that.salario) : that.salario != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmpleado;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechacontratacion != null ? fechacontratacion.hashCode() : 0);
        result = 31 * result + (salario != null ? salario.hashCode() : 0);
        return result;
    }

    public Collection<VentasEntity> getVentasByIdEmpleado() {
        return ventasByIdEmpleado;
    }

    public void setVentasByIdEmpleado(Collection<VentasEntity> ventasByIdEmpleado) {
        this.ventasByIdEmpleado = ventasByIdEmpleado;
    }

    public String getFechaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fechacontratacion);
    }
}

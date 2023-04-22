package models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ventas", schema = "lab2marco")
public class VentasEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_venta")
    private int idVenta;
    @Basic
    @Column(name = "fechaventa")
    private Date fechaventa;
    @Basic
    @Column(name = "totalventa")
    private BigDecimal totalventa;
    @Basic
    @Column(name = "id_empleado")
    private int idEmpleado;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private EmpleadosEntity empleadosByIdEmpleado;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(Date fechaventa) {
        this.fechaventa = fechaventa;
    }

    public BigDecimal getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(BigDecimal totalventa) {
        this.totalventa = totalventa;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VentasEntity that = (VentasEntity) o;

        if (idVenta != that.idVenta) return false;
        if (idEmpleado != that.idEmpleado) return false;
        if (fechaventa != null ? !fechaventa.equals(that.fechaventa) : that.fechaventa != null) return false;
        if (totalventa != null ? !totalventa.equals(that.totalventa) : that.totalventa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idVenta;
        result = 31 * result + (fechaventa != null ? fechaventa.hashCode() : 0);
        result = 31 * result + (totalventa != null ? totalventa.hashCode() : 0);
        result = 31 * result + idEmpleado;
        return result;
    }

    public EmpleadosEntity getEmpleadosByIdEmpleado() {
        return empleadosByIdEmpleado;
    }

    public void setEmpleadosByIdEmpleado(EmpleadosEntity empleadosByIdEmpleado) {
        this.empleadosByIdEmpleado = empleadosByIdEmpleado;
    }

    public String getFechaString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fechaventa);
    }
}

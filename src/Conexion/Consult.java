package Conexion;

import Models.TClientes;
import Models.TReportes_clientes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author porta
 */
public class Consult extends Conexion {

    private QueryRunner QR = new QueryRunner();

    public List<TClientes> clientes() {
        List<TClientes> cliente = new ArrayList();
        try {

            cliente = (List<TClientes>) QR.query(getConn(), "SELECT * FROM tclientes", new BeanListHandler(TClientes.class));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return cliente;
    }

    public List<TReportes_clientes> reportesClientes() {
        String where = "";
        List<TReportes_clientes> reportes = new ArrayList();
        String condicion = " tclientes.ID = treportes_clientes.IdCliente ";
        String campos = " tclientes.ID,tclientes.Nid,tclientes.Nombre,tclientes.Apellido,"
                + "treportes_clientes.IdReporte,treportes_clientes.DeudaActual,"
                + "treportes_clientes.FechaDeuda,treportes_clientes.UltimoPago,"
                + "treportes_clientes.FechaPago, treportes_clientes.Ticket,"
                + "treportes_clientes.FechaLimite";
        try {
            reportes = (List<TReportes_clientes>) QR.query(getConn(),
                    "SELECT" + campos + " FROM treportes_clientes Inner Join tclientes ON"
                    + condicion + where, new BeanListHandler(TReportes_clientes.class));
        } catch (SQLException ex) {
                System.out.println("Error: "+ex);
        }
        return reportes;
    }
}

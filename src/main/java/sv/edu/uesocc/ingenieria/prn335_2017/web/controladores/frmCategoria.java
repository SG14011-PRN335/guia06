/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.CategoriaFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Categoria;

/**
 *
 * @author juancarlos
 */
@Named(value = "frmCategoria")
@ViewScoped
public class frmCategoria implements Serializable {
        

    public frmCategoria() {
    }

    @EJB
    private CategoriaFacadeLocal cfl;
    private LazyDataModel<Categoria> modelo;
    private Categoria registro;
    private boolean btnadd = true;
    private boolean botones = false;
    private boolean seleccions = false;

    @PostConstruct
    private void inicio() {

        registro = new Categoria();

        try {
            this.modelo = new LazyDataModel<Categoria>() {
                @Override
                public Object getRowKey(Categoria object) {
                    if (object != null) {
                        return object.getIdCategoria();
                    }
                    return null;
                }

                @Override
                public Categoria getRowData(String rowKey) {
                    if (rowKey != null && !rowKey.isEmpty() && this.getWrappedData() != null) {
                        try {
                            Integer buscado = new Integer(rowKey);
                            for (Categoria reg : (List<Categoria>) getWrappedData()) {
                                if (reg.getIdCategoria().compareTo(buscado) == 0) {
                                    return reg;
                                }
                            }
                        } catch (Exception e) {
                            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                        }
                    }
                    return null;
                }

                @Override
                public List<Categoria> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Categoria> salida = new ArrayList();
                    try {
                        if (cfl != null) {
                            this.setRowCount(cfl.count());
                            salida = cfl.findRange(first, pageSize);
                            
                        }
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return salida;
                }

            };
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
                 
    }
    
    public void cancelar() {
        this.registro = new Categoria();
        this.botones=false;
        this.btnadd=true;
        
    }


    public void guardarRegistro() {
        try {
            if (this.registro != null && this.cfl != null) {
               if (this.cfl.create(registro)) {
                    System.out.println("AGREGADO");
                    inicio();
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }


    public void Eliminar() {
        try {

            if (this.registro != null && this.cfl != null) {
                if (this.cfl.remove(registro)) {
                    this.registro = new Categoria();
                    this.botones = false;
                    this.btnadd = true;
                    inicio();
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    

    public void Modificar() {
        try {
            if (this.registro != null && this.cfl != null) {
                if (this.cfl.edit(registro)) {
                    this.botones = false;
                    this.btnadd = true;
                    inicio();
                    
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }


    public void cambiarSeleccion() {
                this.botones = true;
                this.btnadd = false;
                  
    }
    

    public LazyDataModel<Categoria> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Categoria> modelo) {
        this.modelo = modelo;
    }

    public Categoria getRegistro() {
        return registro;
    }

    public void setRegistro(Categoria registro) {
        this.registro = registro;
    }

    public boolean isBtnadd() {
        return btnadd;
    }

    public void setBtnadd(boolean btnadd) {
        this.btnadd = btnadd;
    }

    public boolean isSeleccions() {
        return seleccions;
    }

    public void setSeleccions(boolean seleccions) {
        this.seleccions = seleccions;
    }

    
    public boolean isBotones() {
        return botones;
    }

    public void setBotones(boolean botones) {
        this.botones = botones;
    }
 
}


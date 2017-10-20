/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.util.List;
import javax.ejb.Local;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.PostSeccion;

/**
 *
 * @author juancarlos
 */
@Local
public interface PostSeccionFacadeLocal {

    boolean create(PostSeccion postSeccion);

    boolean edit(PostSeccion postSeccion);

    boolean remove(PostSeccion postSeccion);

    PostSeccion find(Object id);

    List<PostSeccion> findAll();

    List<PostSeccion> findRange(int desde, int hasta);

    int count();
    
}

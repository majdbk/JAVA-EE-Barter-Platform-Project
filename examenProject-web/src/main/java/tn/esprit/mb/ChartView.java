package tn.esprit.mb;
 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.PieChartModel;

import tn.esprit.interfaces.ForumServiceLocal;
 
@ManagedBean
public class ChartView implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    
    
    
    @EJB
	ForumServiceLocal service;
 
    @PostConstruct
    public void init() {
        createPieModels();
    }
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        return pieModel2;
    }
     
    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         Long a=service.NombreCategorieClaim("Arnaque");
         Long b=service.NombreCategorieClaim("Faux profil");
         Long c=service.NombreCategorieClaim("Contenu Indiserable");
        pieModel1.set("Arnaque", a);
        pieModel1.set("Faux profil", b);
        pieModel1.set("Contenu Indiserable", c);
       
         
        pieModel1.setTitle("Claim Cause statistics");
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
    }
     
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
        
        Long a=service.NombreCategoriForum("Temoignage");
        Long b=service.NombreCategoriForum("Question");
        Long c=service.NombreCategoriForum("Astuce");
         
        pieModel2.set("Temoignage", a);
        pieModel2.set("Question", b);
        pieModel2.set("Astuce", c);
       
         
        pieModel2.setTitle("Forum Category Statistics");
        pieModel2.setLegendPosition("e");
     
        pieModel2.setShowDataLabels(true);
     
    }
    
    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
}
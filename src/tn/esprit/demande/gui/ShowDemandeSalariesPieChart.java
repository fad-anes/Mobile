///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package tn.esprit.demande.gui;
//
//import com.codename1.charts.models.CategorySeries;
//import com.codename1.charts.renderers.DefaultRenderer;
//import com.codename1.charts.renderers.SimpleSeriesRenderer;
//import com.codename1.charts.views.PieChart;
//import com.codename1.ui.FontImage;
//import com.codename1.ui.Form;
//import com.codename1.ui.layouts.BorderLayout;
//import com.codename1.ui.layouts.BoxLayout;
//import java.util.Map;
//import tn.esprit.demande.services.ServiceDemande;
//
//public class ShowDemandeSalariesPieChart extends Form {
//    
//    public ShowDemandeSalariesPieChart (Form previous) {
//       
//        super("Demande Salaries", BoxLayout.y());
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
//            previous.showBack();
//        });
//        
//        Map<String, Double> salaries = ServiceDemande.getInstance().getDemandeSalaries();
//        CategorySeries series = new CategorySeries("Demande Salaries");
//        for (String language : salaries.keySet()) {
//            double salary = salaries.get(language);
//            series.add(language, salary);
//        }
//        
//        int[] colors = {0xffcc66, 0x99ccff, 0xcc99cc, 0x66cc99, 0xff9999}; // define some colors
//        PieChart chart = new PieChart(series, new DefaultRenderer());
//        DefaultRenderer renderer = (DefaultRenderer) chart.getRenderer();
//        for (int i = 0; i < series.getItemCount(); i++) {
//            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
//            r.setColor(colors[i % colors.length]);
//            renderer.addSeriesRenderer(r);
//        }
//        
//        setLayout(new BorderLayout());
//        addComponent(BorderLayout.CENTER, chart);
//    }
//
//    
//}
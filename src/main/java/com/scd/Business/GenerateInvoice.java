package com.scd.Business;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import com.scd.Models.InvoiceData;
import com.scd.Models.Item;
import com.scd.Models.Orders;
import com.scd.Models.Product;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class GenerateInvoice {
    Orders order;
    Map<String, Object> hm = new HashMap<>();

    public GenerateInvoice(Orders order) {
        this.order = order;
    }

    public boolean generate() {
        System.out.println("Invoice generated");
        hm.put("id", order.getId());
        hm.put("cname", order.getCustomer().getName());
        hm.put("date", order.getDate());
        hm.put("status", order.getStatus());
        hm.put("total", order.getTotal());
        int totalItems = 0;
        List<Item> items = order.getItems();
        List<InvoiceData> invoiceData = new ArrayList<>();
        for (Item item : items) {
            InvoiceData data = new InvoiceData();
            Product product = item.getProduct();
            data.setCode(product.getCode());
            data.setDescription(product.getDescription());
            data.setPrice(product.getPrice());
            data.setQuantity(item.getQuantityOrdered());
            data.setTotal(item.getPrice());
            invoiceData.add(data);
            totalItems += item.getQuantityOrdered();
        }
        hm.put("totalItems", totalItems);

        String fileNameJrxml = "src/main/resources/reports/invoice.jrxml";
        String fileNamePdf = "src/main/resources/reports/invoice" + "_" + order.getId() + ".pdf";

        // print this map data
        System.out.println("Map data:" + hm);

        System.out.println("Invoice data:" + invoiceData);

        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(invoiceData);

        try {
            System.out.println("Loading the .JRMXML file ....");
            JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
            System.out.println("Compiling the .JRMXML file to .JASPER file....");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            System.out.println("filling parameters to .JASPER file....");
            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperReport,
                    hm, beanColDataSource);
            System.out.println("exporting the JASPER file to PDF file....");
            JasperExportManager.exportReportToPdfFile(jprint, fileNamePdf);
            System.out.println("Successfully completed the export");
            return true;
        } catch (Exception e) {
            System.out.print("Exception:" + e);
            return false;
        }
    }

}
